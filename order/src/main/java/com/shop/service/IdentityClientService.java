package com.shop.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "identity-service", url = "localhost:8081/auth")
public interface IdentityClientService {
	
	@PostMapping(path = {"/validate-jwt"})
	public String validateJWT(@RequestParam("jwt") String jwt);
	
	@PostMapping("/validate-jwt-userid")
	public String validateJWTAndUserId(@RequestParam("jwt") String jwt, @RequestParam("userid") int userid);

}
