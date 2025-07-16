package com.ILSI.TouristeProject.UserManagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    private static final int EXPIRATION_TIME= 60;

    public VerificationToken(String token, AppUser user) {
        super();
        this.token = token;
        this.user = user;
        this.expirationTime = this.getTokenExpirationTime();
    }

    public VerificationToken(String token) {
        super();
        this.token = token;
        this.expirationTime = this.getTokenExpirationTime();
    }

    public VerificationToken() {
    }

    public Date getTokenExpirationTime() {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(new Date().getTime());
      calendar.add(Calendar.MINUTE,EXPIRATION_TIME);
      return new Date(calendar.getTime().getTime());
    }

}
