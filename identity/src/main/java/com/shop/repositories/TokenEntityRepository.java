/**
 * @author: Anvar Szulczyk
 * @date: Feb 13, 2024
 */
package com.shop.repositories;

import java.util.Set;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.model.TokenEntity;

@Repository
public interface TokenEntityRepository extends JpaRepository<TokenEntity, Integer> {

	@Query(value = "FROM TokenEntity te INNER JOIN UserEntity ue ON te.userEntity.id = ue.id "
			+ "WHERE ue.id = :userId AND (te.expired = false OR te.revoked = false)")
	Set<TokenEntity> findAllValidTokensByUserEntity(int userId);
	
	Optional<TokenEntity> findByToken(String jwtToken);
	
	
}
