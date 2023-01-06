package com.example.shoptienhieu.security.authService;

import com.example.shoptienhieu.dto.request.authReq.LogOutReq;
import com.example.shoptienhieu.dto.request.authReq.LoginReq;
import com.example.shoptienhieu.dto.request.authReq.RegisterReq;
import com.example.shoptienhieu.dto.request.authReq.TokenRefreshReq;
import com.example.shoptienhieu.dto.response.BaseRes;
import com.example.shoptienhieu.exception.DayException;
import com.example.shoptienhieu.exception.EmailException;
import com.example.shoptienhieu.exception.FullNameException;
import com.example.shoptienhieu.exception.PhoneException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface AuthService {
    BaseRes<?> getMe(HttpServletRequest request);

    BaseRes<?> authenticateUser(LoginReq loginReq);

    BaseRes<?> registerUser(RegisterReq registerReq) throws EmailException, PhoneException, DayException, FullNameException;

    BaseRes<?> refreshtokenUser(TokenRefreshReq request);

    BaseRes<?> logoutUser(LogOutReq logOutReq);

}
