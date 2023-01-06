package com.example.shoptienhieu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity()
@Table(name = "confirmation_otp")
public class ConfirmationOTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String otp;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;
    private int userId;
    public ConfirmationOTP(String otp,
                           LocalDateTime createdAt,
                           LocalDateTime expiredAt,
                           int userId) {
        this.otp = otp;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.userId = userId;
    }
}
