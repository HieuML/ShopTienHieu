package com.example.shoptienhieu.dto.request;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
    private int userId;
    private String newPassword;
}
