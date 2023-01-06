package com.example.shoptienhieu.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@Entity

@Table(name = "user_inform", uniqueConstraints = {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Email
    @Column(length = 45)
    private String email;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    private String gender;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyy")
    private Date birthday;

    @Column(length = 20)
    private String phone;



    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createdAt = new Date().getTime();
        this.updatedAt = new Date().getTime();
        this.isActive = true;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    private String avatar;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    @Column(name = "is_active")
    private Boolean isActive;


    public User(String username, String password, String email, String firstName, String lastName, String gender, Date birthday, String phone, String avatar) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.avatar = avatar;
        this.createdAt = new Date().getTime();
        this.updatedAt = new Date().getTime();
        this.isActive = true;
    }


}
