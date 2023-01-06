package com.example.shoptienhieu.dto.request.authReq;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TokenRefreshReq {
  @NotBlank
  private String refreshToken;


}
