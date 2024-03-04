package com.shop.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final String secret = "gQ7opnLAA3KvHX7JvkCniaBlkbAU1Qfvzdacp+sfXEFo3n6/pFBNxHFvqyr6BpWm";

	public void validateToken(String jwtToken) {
		Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(jwtToken);
	}
	
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<String, Object>(), userDetails);
	}

	private String generateToken(Map<String, Object> claims, UserDetails userDetails) {
		return Jwts.builder()
				.claims(claims)
				.subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getSignKey())
				.compact();
	}

	private Key getSignKey() {
		byte[] keys = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keys);
	}
}