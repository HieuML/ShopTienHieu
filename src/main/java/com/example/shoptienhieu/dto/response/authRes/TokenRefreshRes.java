package com.example.shoptienhieu.dto.response.authRes;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenRefreshRes {
  private String accessToken;
  private String refreshToken;
  private String tokenType = "Bearer";

  public TokenRefreshRes(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }



}
