package haeun.kim.surveyproject.config.auth.dto;

import haeun.kim.surveyproject.domain.user.Users;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private int point;
    private String subject;

    public SessionUser(Users user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.point = user.getPoint();
        this.subject = user.getSubject();
    }
}
