package com.example.shoptienhieu.dto.request.authReq;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class LoginReq {
	@NotBlank
	private String username;

	@NotBlank
	private String password;


}
