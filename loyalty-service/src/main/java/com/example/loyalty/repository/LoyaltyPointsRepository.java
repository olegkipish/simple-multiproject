package com.example.loyalty.repository;

import com.example.loyalty.repository.entity.LoyaltyPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface LoyaltyPointsRepository extends JpaRepository<LoyaltyPoints, String>{

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT t FROM LoyaltyPoints t WHERE t.playerId = :playerId")
	LoyaltyPoints findOneForUpdate(@Param("playerId") String playerId);
}
