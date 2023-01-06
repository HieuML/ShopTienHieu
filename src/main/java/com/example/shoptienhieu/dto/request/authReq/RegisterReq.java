package com.example.shoptienhieu.dto.request.authReq;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class RegisterReq {
    @NotBlank
    @Size(min = 3, max = 45)
    private String username;

    @NotBlank
    private String password;


    @NotBlank
    @Size(max = 45)
    @Email
    private String email;

    @Size(max = 100)
    private String first_name;

    @Size(max = 100)
    private String last_name;

    private String gender;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyy")
    private Date birthday;

    @Size(max = 20)
    private String phone;

    private String avatar;

    @NotBlank
    private String role;
    











  

}
