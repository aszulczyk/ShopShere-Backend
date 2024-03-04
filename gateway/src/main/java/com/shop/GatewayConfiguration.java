package com.shop;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

@Configuration
public class GatewayConfiguration {
	
	private ObjectFactory<HttpMessageConverters> messageConverters = HttpMessageConverters::new;

    @Bean
    Encoder feignFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    @Bean
    Decoder feignFormDecoder() {
        return new SpringDecoder(messageConverters);
    }

	
	/*@Autowired
	  AuthenticationFilterFactory filter;
	
	*
	 * @Bean RouteLocator myRoutes(RouteLocatorBuilder builder) { return
	 * builder.routes() .route(p -> p .path("/auth/**") .filters(f ->
	 * f.filter(filter.apply(new AuthenticationFilterFactory.Config())))
	 * .uri("http://localhost:8081")) .build(); }
	 */

}
