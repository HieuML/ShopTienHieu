package com.example.shoptienhieu.dto.request.userReq;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePasswordReq {
    private String oldPassword;
    private String newPassword;
}
