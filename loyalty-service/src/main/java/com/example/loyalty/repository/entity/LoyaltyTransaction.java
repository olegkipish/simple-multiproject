package com.example.loyalty.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "LOYALTY_TRANSACTION")
public class LoyaltyTransaction {

	@Id
	@Column(name = "PAYMENT_TRANSACTION_ID", nullable = false, length = 36)
	private String paymentTransactionId;

	@Column(name = "RECEIVE_DT", nullable = false, updatable = false)
	private LocalDateTime receiveDt;

	@Column(name = "POINTS_AMOUNT", updatable = false)
	private int pointsAmount;

	@Column(name = "SUCCESS", nullable = false)
	private boolean success;

	@ManyToOne
	@JoinColumn(name = "LOYALTY_POINTS_PLAYER_ID", updatable = false)
	private LoyaltyPoints loyaltyPoints;

}
