package com.shop.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.controllers.LoginDTO;
import com.shop.controllers.UserDTO;
import com.shop.model.Role;
import com.shop.model.TokenEntity;
import com.shop.model.TokenType;
import com.shop.model.UserEntity;
import com.shop.repositories.TokenEntityRepository;
import com.shop.repositories.UserEntityRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserEntityRepository userEntityRepository;

	@Autowired
	private TokenEntityRepository tokenEntityRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public UserDTO saveUser(UserEntity userEntity) {
		userEntity.setRole(Role.USER);
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		return UserDTO.userEntityToUserDTO(userEntityRepository.save(userEntity));
	}
	
	public UserDTO saveAdmin(UserEntity userEntity) {
		userEntity.setRole(Role.ADMIN);
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		return UserDTO.userEntityToUserDTO(userEntityRepository.save(userEntity));
	}

	public UserDTO loginUser(LoginDTO loginDTO) {
		// Find out if the user inserted a username or password and create a userDTO object
		UserDTO userDTO;
		if (LoginDTO.checkIfValidEmail(loginDTO.getUsernameOrEmail())) {
			userDTO = UserDTO.builder()
					.email(loginDTO.getUsernameOrEmail())
					.password(loginDTO.getPassword())
					.build();
		} else {
			userDTO = UserDTO.builder()
					.username(loginDTO.getUsernameOrEmail())
					.password(loginDTO.getPassword())
					.build();
		}
		
		// Check to validate with email or password;
		String username = userDTO.getUsername();
		Optional<UserEntity> optionalUserEntity;
		if (username == null || username.isBlank()) {
			optionalUserEntity = userEntityRepository.findByEmail(userDTO.getEmail());
		} else {
			optionalUserEntity = userEntityRepository.findByUsername(username);
		}
		if (optionalUserEntity.isEmpty())
			return null;

		UserEntity userEntity = optionalUserEntity.get();
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userDTO.getPassword()));

		Set<TokenEntity> tokenEntities = tokenEntityRepository.findAllValidTokensByUserEntity(userEntity.getId());
		if (!tokenEntities.isEmpty()) {
			tokenEntities.stream().forEach((te) -> {
				te.setRevoked(true);
				te.setExpired(true);
			});
		}

		String newJwtToken = jwtService.generateToken(userEntity);
		TokenEntity tokenEntity = TokenEntity.builder().expired(false).revoked(false).token(newJwtToken)
				.tokenType(TokenType.BEARER).userEntity(userEntity).build();

		tokenEntityRepository.save(tokenEntity);

		UserDTO newUserDTO = UserDTO.userEntityToUserDTO(userEntity);
		newUserDTO.setJwt(newJwtToken);

		return newUserDTO;
	}

	public boolean validateJWT(String jwt) {
		TokenEntity tokenEntity = tokenEntityRepository.findByToken(jwt).orElse(null);
		if (tokenEntity == null) return false;
		
		if (tokenEntity.isExpired() || tokenEntity.isRevoked()) return false;
		
		try {
			jwtService.validateToken(jwt);
		} catch (Exception ex) {
			return false;
		}
		
		return true;
	}

	public boolean validateJWTAndUserId(String jwt, int userId) {
		TokenEntity tokenEntity = tokenEntityRepository.findByToken(jwt).orElse(null);
		if (tokenEntity == null)
			return false;

		if (tokenEntity.isExpired() || tokenEntity.isRevoked() || (tokenEntity.getUserEntity().getId() != userId))
			return false;
		
		try {
			jwtService.validateToken(jwt);
		} catch (Exception ex) {
			return false;
		}

		return true;
	}
	
	
	public boolean validateJWTAndIsAdmin(String jwt) {
		TokenEntity tokenEntity = tokenEntityRepository.findByToken(jwt).orElse(null);
		if (tokenEntity == null) return false;
		
		if (tokenEntity.isExpired() || tokenEntity.isRevoked() || !tokenEntity.getUserEntity().getRole().equals(Role.ADMIN)) return false;
		
		try {
			jwtService.validateToken(jwt);
		} catch (Exception ex) {
			return false;
		}
		
		return true;
	}

}
