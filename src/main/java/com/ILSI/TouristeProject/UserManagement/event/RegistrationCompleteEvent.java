package com.ILSI.TouristeProject.UserManagement.event;

import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Setter
@Getter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private AppUser user;
    private String applicationUrl;

    public RegistrationCompleteEvent(AppUser user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
