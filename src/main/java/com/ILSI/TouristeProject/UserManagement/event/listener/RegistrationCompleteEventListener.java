package com.ILSI.TouristeProject.UserManagement.event.listener;

import com.ILSI.TouristeProject.UserManagement.event.RegistrationCompleteEvent;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import com.ILSI.TouristeProject.UserManagement.Service.ServiceImpl.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Random;


@RequiredArgsConstructor
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;
    private final JavaMailSender mailSender;

    private AppUser theUser;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        theUser = event.getUser();

        Random random = new Random();
        int verificationCode =  10000000 + random.nextInt(90000000);
        String verificationToken = String.valueOf(verificationCode);
        //String verificationToken = UUID.randomUUID().toString();
        userService.saveUserVerificationToken(theUser, verificationToken);
        String url = verificationToken;
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        // Email subject and sender details
        String subject = "Email Verification";
        String senderName = "Tourism Project Registration";
        // Create the email content (HTML format)
        String mailContent = "<h3>Hello " + theUser.getNameUser()+ ",</h3>" +
                "<p>Thank you for registering on our platform.</p>" +
                "<p>Please use the following verification code to complete your registration:</p>" +
                "<p>" + url + "</p>" +
                "<p>This code is valid for 1 hour.</p>"+
                "<br>" +
                "<p>Thank you,<br>The ILSI Team</p>";

        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("groupIlsi@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);

        // Send the email
        mailSender.send(message);
    }

}
