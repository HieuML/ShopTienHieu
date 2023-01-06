package com.example.shoptienhieu.service.userService;

import com.example.shoptienhieu.dto.request.userReq.ChangePasswordReq;
import com.example.shoptienhieu.dto.response.BaseRes;
import com.example.shoptienhieu.dto.response.BaseUpdated;
import com.example.shoptienhieu.entities.User;
import com.example.shoptienhieu.dto.request.UpdatePasswordRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    /**
     * find all users
     * @return
     */
    List<User> getAll();

    /**
     * find user by id
     * @param userId
     * @return
     */
    User getById(int userId);

    /**
     * find users by firstName
     * @param firstName
     * @return
     */
    List<User> findByFirstName(String firstName);

    /**
     * send otp to emailUser
     * @param username
     */
    boolean sendOTP(String username);

    /**
     * confirm otp
     * @param otp
     * @return
     */
    int confirmOTP(String otp);

    /**
     * update new password
     * @param requestUpdatePassword
     * @return
     */
    boolean updatePassword(UpdatePasswordRequest updatePasswordRequest);


    BaseRes<BaseUpdated> changePassword(HttpServletRequest request, ChangePasswordReq changePasswordReq);

}
