package com.example.shoptienhieu.dto.response.authRes;

import com.example.shoptienhieu.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class GetInfoRes {

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String gender;

    private Date birthday;

    private String phone;

    private String role;

    private String avatar;

    private Boolean isActive;

    public GetInfoRes(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender();
        this.birthday = user.getBirthday();
        this.phone = user.getPhone();
        this.role = user.getRole().getName();
        this.avatar = user.getAvatar();
        this.isActive = user.getIsActive();
    }
}
