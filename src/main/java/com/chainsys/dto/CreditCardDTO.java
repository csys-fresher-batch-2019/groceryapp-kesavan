package com.chainsys.dto;

import java.time.LocalDate;

public class CreditCardDTO {

	private long cardnum;
	private LocalDate exp;
	private int cvv;
	private float amount;
	private String comments;
	
	public CreditCardDTO(long cardnum, LocalDate exp, int cvv, float amount, String comments) {
		super();
		this.cardnum = cardnum;
		this.exp = exp;
		this.cvv = cvv;
		this.amount = amount;
		this.comments = comments;
	}
	
	
}
