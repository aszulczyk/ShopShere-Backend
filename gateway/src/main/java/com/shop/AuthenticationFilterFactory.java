package com.shop;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class AuthenticationFilterFactory extends AbstractGatewayFilterFactory<AuthenticationFilterFactory.Config> {

	public static class Config {
		public static final URI uri = UriComponentsBuilder.fromUriString("http://localhost:8081/DoesNotExist")
				.build(new HashMap<String, Object>());

	}

	public AuthenticationFilterFactory() {
		super(Config.class);
	}

	@Autowired
	private RouteValidator validator;

//	@Autowired
//	private JWTUtil jwtUtil;

	@Autowired
	private IdentityClientService identityClientService;

	@Override
	public GatewayFilter apply(Config config) {

		return ((exchange, chain) -> {

			try {
				if (validator.isSecured.test(exchange.getRequest())) {
					if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
						throw new RuntimeException("Header not Found");
					}
					String jwtToken = "";
					String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
					if (authHeader != null && authHeader.startsWith("Bearer ")) {
						jwtToken = authHeader.substring(7);
					}
					// jwtUtil.validateToken(jwtToken);
					String valid = identityClientService.validateUserJWT(jwtToken);
					if (!Boolean.valueOf(valid)) {
						return chain.filter(exchange.mutate().request(builder -> builder.uri(Config.uri)).build());
					}
				}

			} catch (Exception ex) {
				return chain.filter(exchange.mutate().request(builder -> builder.uri(Config.uri)).build());
			}

			return chain.filter(exchange);
		});
	}

}
