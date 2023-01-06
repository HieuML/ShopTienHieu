package com.example.shoptienhieu.utils;


import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.security.jwt.JwtUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


@Service
public class CommonMethod {

    public static String getUsernameFromJwt(HttpServletRequest request, JwtUtils jwtUtils) {
        String headerAuth = request.getHeader("Authorization");
        String jwt = null;

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            jwt = headerAuth.substring(7);
        } else throw new ResourceNotFoundException(TextStatus.INVALID_ACCESS_TOKEN);

        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            return jwtUtils.getUserNameFromJwtToken(jwt);

        } else throw new ResourceNotFoundException(TextStatus.INVALID_ACCESS_TOKEN);
    }


    public static Sort.Direction findDirection(String s) {
        if(s.equals("asc")) {
            return Sort.Direction.ASC;
        }
        else return Sort.Direction.DESC;
    }


}
