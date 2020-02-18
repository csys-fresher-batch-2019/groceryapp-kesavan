package com.chainsys.dto;

import java.time.LocalDate;

public class WalletDTO {

	private long mobile;
	private int id;
	private int amount;

	public WalletDTO(long mobile, int id, int amount) {
		super();
		this.mobile = mobile;
		this.id = id;
		this.amount = amount;
	}
}
