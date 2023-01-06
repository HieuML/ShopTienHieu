package com.example.shoptienhieu.service.userService.impl;

import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.userReq.ChangePasswordReq;
import com.example.shoptienhieu.dto.response.BaseRes;
import com.example.shoptienhieu.dto.response.BaseUpdated;
import com.example.shoptienhieu.entities.ConfirmationOTP;
import com.example.shoptienhieu.entities.User;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.OTPRepository;
import com.example.shoptienhieu.repository.UserRepository;
import com.example.shoptienhieu.dto.request.UpdatePasswordRequest;
import com.example.shoptienhieu.security.jwt.JwtUtils;
import com.example.shoptienhieu.service.userService.UserService;
import com.example.shoptienhieu.utils.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository repository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private OTPRepository otpRepository;

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }


    @Override
    public User getById(int userId) {
        return repository.findById(userId).get();
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return repository.findUsersByFirstNameContaining(firstName);
    }

    @Async
    @Transactional
    @Override
    public boolean sendOTP(String username) {
        try{
            if(repository.findUserByUsername(username).isEmpty()){
                throw new IllegalStateException("Username does`t exist");
            }
            User user = repository.findUserByUsername(username).get();

            Optional<ConfirmationOTP> findOTPByUser = otpRepository.findConfirmationOTPByUserId(user.getId());
            ConfirmationOTP confirmationOTP = new ConfirmationOTP();
            if(findOTPByUser.isPresent()){
                confirmationOTP = findOTPByUser.get();

            } else {
                confirmationOTP.setUserId(user.getId());
            }
            confirmationOTP.setOtp(UUID.randomUUID().toString().substring(0,4));
            confirmationOTP.setCreatedAt(LocalDateTime.now());
            confirmationOTP.setExpiredAt(LocalDateTime.now().plusMinutes(15));
            confirmationOTP.setConfirmedAt(null);

            otpRepository.save(confirmationOTP);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(confirmationOTP.getOtp(), true);
            helper.setTo(user.getEmail());
            helper.setSubject("Your otp to get password");
            helper.setFrom("buiductien13111998@gmail.com");
            javaMailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e){
            throw new IllegalStateException("failed to send email");
        }
    }

    @Override
    public int confirmOTP(String otp) {
        if(otpRepository.findConfirmationOTPByOtp(otp).isEmpty()){
            throw new IllegalStateException("OTP does`t exist");
        }
        ConfirmationOTP confirmationOTP = otpRepository.findConfirmationOTPByOtp(otp).get();
        if(confirmationOTP.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new IllegalStateException("OTP is expired");
        }
        confirmationOTP.setConfirmedAt(LocalDateTime.now());
        otpRepository.save(confirmationOTP);
        return confirmationOTP.getUserId();
    }

    @Override
    public boolean updatePassword(UpdatePasswordRequest updatePasswordRequest){
        if(repository.findById(updatePasswordRequest.getUserId()).isEmpty()){
            throw new IllegalStateException("Not found user");
        }
        User userNeedUpdatePassword =  repository.findById(updatePasswordRequest.getUserId()).get();
        if(updatePasswordRequest.getNewPassword().length() <= 8){
            throw new IllegalStateException("Password is illegal");
        }
        userNeedUpdatePassword.setPassword(encoder.encode(updatePasswordRequest.getNewPassword()));
        userNeedUpdatePassword.setUpdatedAt(new Date().getTime());
        repository.save(userNeedUpdatePassword);
        return true;
    }

    @Override
    public BaseRes<BaseUpdated> changePassword(HttpServletRequest request, ChangePasswordReq changePasswordReq) {
        String username = CommonMethod.getUsernameFromJwt(request,jwtUtils);
        User user = userRepository.findByUsername(username).get();
        Integer userID = user.getId();
        String password = user.getPassword();

        if(!encoder.matches(changePasswordReq.getOldPassword(),password)) throw new ResourceNotFoundException(TextStatus.CHANGE_PASSWORD_ERROR);
        else {
            user.setPassword(encoder.encode(changePasswordReq.getNewPassword()));
            userRepository.save(user);
            return new BaseRes<>(HttpStatus.OK.value(), TextStatus.CHANGE_PASSWORD_SUCCESS, new BaseUpdated(userID, true ));
        }

    }
}
