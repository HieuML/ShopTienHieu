package com.example.shoptienhieu.security.authService;

import com.example.shoptienhieu.constants.RoleName;
import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.entities.*;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.RoleRepository;
import com.example.shoptienhieu.repository.ShopRepository;
import com.example.shoptienhieu.repository.UserRepository;
import com.example.shoptienhieu.repository.WalletRepository;
import com.example.shoptienhieu.security.authService.AuthService;
import com.example.shoptienhieu.security.authService.RefreshTokenService;
import com.example.shoptienhieu.security.authService.UserDetailsImpl;
import com.example.shoptienhieu.security.jwt.JwtUtils;
import com.example.shoptienhieu.dto.request.authReq.LogOutReq;
import com.example.shoptienhieu.dto.request.authReq.LoginReq;
import com.example.shoptienhieu.dto.request.authReq.RegisterReq;
import com.example.shoptienhieu.dto.request.authReq.TokenRefreshReq;
import com.example.shoptienhieu.dto.response.BaseRes;
import com.example.shoptienhieu.dto.response.authRes.GetInfoRes;
import com.example.shoptienhieu.dto.response.authRes.LoginRes;
import com.example.shoptienhieu.dto.response.authRes.TokenRefreshRes;
import com.example.shoptienhieu.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    private UserService userService;
//login 2
    public BaseRes<?> getMe(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        String jwt = null;
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            jwt = headerAuth.substring(7);
        } else throw new ResourceNotFoundException(TextStatus.INVALID_ACCESS_TOKEN);
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            User user = userRepository.findByUsername(username).get();
            GetInfoRes getInfoRes = new GetInfoRes(user);
            return new BaseRes<GetInfoRes>(HttpStatus.OK.value(), TextStatus.GET_INFO_SUCCESS, getInfoRes);
        } else return null;

    }
// signin
    @Override
    public BaseRes<?> authenticateUser(LoginReq registerRequest) {
        Optional<User> user = userRepository.findUserByUsername(registerRequest.getUsername());

        if (!user.isPresent()) throw new ResourceNotFoundException(TextStatus.LOGIN_ERROR);
        User userData = user.get();
        if (userData.getIsActive()) {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(registerRequest.getUsername(), registerRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String jwt = jwtUtils.generateJwtToken(userDetails);

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

            LoginRes loginRes = new LoginRes(
                    jwt, refreshToken.getToken());
            userRepository.save(userData);

            return new BaseRes<LoginRes>(HttpStatus.OK.value(), TextStatus.LOGIN_SUCCESS, loginRes);
        } else {
            return new BaseRes<>(HttpStatus.LOCKED.value(), TextStatus.ACCOUNT_LOCKED);
        }
    }
// signup
    @Override
    public BaseRes<?> registerUser(RegisterReq registerReq) {
        if (userRepository.existsByUsername(registerReq.getUsername())) {
            return new BaseRes<>(HttpStatus.FORBIDDEN.value(), TextStatus.USERNAME_EXIST);
        }

        if (userRepository.existsByEmail(registerReq.getEmail().toLowerCase())) {
            return new BaseRes<>(HttpStatus.FORBIDDEN.value(), TextStatus.EMAIL_EXIST);
        }

        User user = new User(registerReq.getUsername(),
                encoder.encode(registerReq.getPassword()), registerReq.getEmail().toLowerCase(), registerReq.getFirst_name(),
                registerReq.getLast_name(), registerReq.getGender(), registerReq.getBirthday(), registerReq.getPhone(), registerReq.getAvatar());

        String roleStr = registerReq.getRole();

        if (roleStr.equals(RoleName.SELLER)) {
            Role role = roleRepository.findByName(RoleName.SELLER);
            user.setRole(role);
            userRepository.save(user);
            shopRepository.save(new Shop(user));
        } else if (roleStr.equals(RoleName.CUSTOMER)) {
            Role role = roleRepository.findByName(RoleName.CUSTOMER);
            user.setRole(role);
            userRepository.save(user);
        } else if(roleStr.equals(RoleName.ADMIN)){
            Role role = roleRepository.findByName(RoleName.ADMIN);
            user.setRole(role);
            userRepository.save(user);
        }else {
            throw new ResourceNotFoundException(TextStatus.ROLE_NOT_FOUND);
        }
        walletRepository.save(new Wallet(user));
        return new BaseRes<>(HttpStatus.OK.value(), TextStatus.REGISTER_SUCCESS);
    }
// refresh token
    @Override
    public BaseRes<?> refreshtokenUser(TokenRefreshReq request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return new BaseRes(HttpStatus.OK.value(), TextStatus.REFRESH_TOKEN_SUCCESS, new TokenRefreshRes(token, requestRefreshToken));
                })
                .orElseThrow(() -> new ResourceNotFoundException(TextStatus.REFRESH_TOKEN_NOT_FOUND));
    }
// logout
    @Override
    public BaseRes<?> logoutUser(LogOutReq logOutReq) {
        Optional<User> userData = userRepository.findById(logOutReq.getUserId());
        if (!userData.isPresent())
            throw new ResourceNotFoundException(TextStatus.LOGOUT_ERROR);
        refreshTokenService.deleteByUserId(logOutReq.getUserId());
//      User userData = userRepository.findById(logOutRequest.getUserId()).get();
        return new BaseRes<>(HttpStatus.OK.value(), TextStatus.LOGOUT_SUCCESS);
    }
}
