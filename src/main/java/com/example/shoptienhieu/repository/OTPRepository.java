package com.example.shoptienhieu.repository;

import com.example.shoptienhieu.entities.ConfirmationOTP;
import com.example.shoptienhieu.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<ConfirmationOTP, Integer> {
    Optional<ConfirmationOTP> findConfirmationOTPByOtp(String otp);

    Optional<ConfirmationOTP> findConfirmationOTPByUserId(int userId);
}
