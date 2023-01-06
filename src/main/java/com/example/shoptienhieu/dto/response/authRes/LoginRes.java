package com.example.shoptienhieu.dto.response.authRes;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRes {


	private String token;
	private String type = "Bearer";
	private String refreshToken;

	public LoginRes(String token, String refreshToken) {

		this.token = token;
		this.refreshToken = refreshToken;
	}
}
