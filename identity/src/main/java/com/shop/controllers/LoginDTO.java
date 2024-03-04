package com.shop.controllers;

import java.util.regex.Pattern;

import com.shop.model.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDTO {
	
	private String usernameOrEmail;
	private String password;
	
	public static boolean checkIfValidEmail(String email) {
	    String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		return Pattern
				.compile(regexPattern)
				.matcher(email)
				.matches();
	}

}
