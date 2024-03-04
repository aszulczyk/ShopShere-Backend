package com.shop.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
	
	private String firstname;
	private String middlename;
	private String lastname;
	
	@Embedded
	private Address address;
	

}
