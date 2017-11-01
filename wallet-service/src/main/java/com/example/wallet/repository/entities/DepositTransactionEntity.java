package com.example.wallet.repository.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DEPOSIT_TRANSACTION")
@DynamicInsert
@DynamicUpdate
public class DepositTransactionEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "ID", nullable = false, updatable = false, length = 36)
	private String id;

	@Column(name = "RECEIVE_DT", nullable = false, updatable = false)
	private LocalDateTime receiveDate;

	@Column(name = "AMOUNT", nullable = false, updatable = false)
	private int amount;

	@ManyToOne
	@JoinColumn(name = "WALLET_ID", nullable = false, updatable = false)
	private WalletEntity wallet;

}
