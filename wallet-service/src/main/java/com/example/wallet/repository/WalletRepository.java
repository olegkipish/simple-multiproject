package com.example.wallet.repository;


import com.example.wallet.repository.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, String> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select t from WalletEntity t where t.playerId = :playerId")
	WalletEntity findOneForUpdate(@Param("playerId") String playerId);

}
