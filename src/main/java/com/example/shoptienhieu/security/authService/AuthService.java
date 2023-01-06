package com.example.shoptienhieu.security.authService;

import com.example.shoptienhieu.dto.request.authReq.LogOutReq;
import com.example.shoptienhieu.dto.request.authReq.LoginReq;
import com.example.shoptienhieu.dto.request.authReq.RegisterReq;
import com.example.shoptienhieu.dto.request.authReq.TokenRefreshReq;
import com.example.shoptienhieu.dto.response.BaseRes;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface AuthService {
    BaseRes<?> getMe(HttpServletRequest request);

    BaseRes<?> authenticateUser(LoginReq loginReq);

    BaseRes<?> registerUser(RegisterReq registerReq);

    BaseRes<?> refreshtokenUser(TokenRefreshReq request);

    BaseRes<?> logoutUser(LogOutReq logOutReq);

}
