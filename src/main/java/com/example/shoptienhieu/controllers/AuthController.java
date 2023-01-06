package com.example.shoptienhieu.controllers;


import com.example.shoptienhieu.constants.Path;
import com.example.shoptienhieu.dto.request.UpdatePasswordRequest;
import com.example.shoptienhieu.dto.request.userReq.ChangePasswordReq;
import com.example.shoptienhieu.exception.DayException;
import com.example.shoptienhieu.exception.EmailException;
import com.example.shoptienhieu.exception.FullNameException;
import com.example.shoptienhieu.exception.PhoneException;
import com.example.shoptienhieu.security.authService.AuthService;
import com.example.shoptienhieu.dto.request.authReq.LogOutReq;
import com.example.shoptienhieu.dto.request.authReq.LoginReq;
import com.example.shoptienhieu.dto.request.authReq.RegisterReq;
import com.example.shoptienhieu.dto.request.authReq.TokenRefreshReq;
import com.example.shoptienhieu.service.userService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @PostMapping(Path.LOGIN)
    public ResponseEntity<?> login(@Valid @RequestBody LoginReq loginReq) {
        logger.info("Request to Login");
        return ResponseEntity.ok(authService.authenticateUser(loginReq));
    }
    @PostMapping(Path.REGISTER)
    public ResponseEntity<?> register(@Valid @RequestBody RegisterReq signUpRequest) throws PhoneException, DayException, FullNameException, EmailException {
        logger.info("Request to register");
        return ResponseEntity.ok(authService.registerUser(signUpRequest));
    }
    @GetMapping(Path.INFO)
    public ResponseEntity<?> getInfo(HttpServletRequest request) {
        logger.info("Request to info");
        return ResponseEntity.ok(authService.getMe(request));
    }
    @PostMapping(Path.REFRESH_TOKEN)
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshReq request) {
        logger.info("Request to refresh token");
        return ResponseEntity.ok(authService.refreshtokenUser(request));
    }
    @PostMapping(Path.LOGOUT)
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutReq logOutReq) {
        logger.info("Request to log out");
        return ResponseEntity.ok(authService.logoutUser(logOutReq));
    }
    @PostMapping(Path.SENDING_OTP)
    public ResponseEntity<Boolean> send(@RequestParam("username") String username) {
        logger.info("Request to sending otp");
        return ResponseEntity.ok(userService.sendOTP(username));
    }
    @PostMapping(Path.CONFIRM_OTP)
    public ResponseEntity<Integer> confirm(@RequestParam("otp") String otp) {
        logger.info("Request to confirm otp");
        return ResponseEntity.ok(userService.confirmOTP(otp));
    }
    @PostMapping(Path.UPDATE_NEW_PASSWORD)
    public ResponseEntity<Boolean> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        logger.info("Request to update new password");
        return ResponseEntity.ok(userService.updatePassword(updatePasswordRequest));
    }

    @PutMapping(Path.CHANGE_PASSWORD)
    public ResponseEntity<?> changePassword(HttpServletRequest request, @RequestBody ChangePasswordReq changePasswordReq) {

        return  ResponseEntity.ok(userService.changePassword(request, changePasswordReq));
    }

}
