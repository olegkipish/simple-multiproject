package com.example.wallet.repository.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "WALLET")
@DynamicInsert
@DynamicUpdate
public class WalletEntity {

	@Id
	@Column(name = "PLAYER_ID", updatable = false, length = 36)
	private String playerId;

	@Column(name = "CREATE_DT", nullable = false, updatable = false)
	private LocalDateTime createDate;

	@Column(name = "DEPOSIT_AMOUNT", nullable = false)
	private int depositAmount;

	@Column(name = "LOYALTY_ENABLED", nullable = false)
	private boolean loyaltyEnabled;

	public WalletEntity(
			String playerId) {
		this.playerId = playerId;
		this.createDate = LocalDateTime.now();
		this.depositAmount = 0;
		this.loyaltyEnabled = false;
	}

	public void addDeposit(int addAmount) {
		this.depositAmount += addAmount;
	}
}
