package com.example.loyalty.repository.entity;

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
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "LOYALTY_POINTS")
public class LoyaltyPoints {

	@Id
	@Column(name = "PLAYER_ID", nullable = false, length = 36, updatable = false)
	private String playerId;

	@Column(name = "POINTS_AMOUNT", nullable = false)
	private int pointsAmount;

	@Column(name = "NEXT_TOPUP", nullable = false)
	private LocalDateTime nextTopUp;

	@Column(name = "OPER_COUNT")
	private int operCount;

	public LoyaltyPoints(String playerId) {
		this.playerId = playerId;
		this.pointsAmount = 0;
		this.nextTopUp = LocalDateTime.now();
	}

	public void incOperCount() {
		this.operCount++;
	}

	public void addPointsAmount(int pointsAmount) {
		this.pointsAmount += pointsAmount;
	}
}
