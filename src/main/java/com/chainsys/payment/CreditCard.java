package com.chainsys.payment;

import java.time.LocalDate;
import creditcard.CreditCardService;
import creditcard.PaymentResponse;

public class CreditCard {

	public static PaymentResponse cardpayment(long cardno, LocalDate exp, int cvv, float amount,String comments) {
		creditcard.CreditCard creditCard = new creditcard.CreditCard();
		creditCard.setCardNo(cardno);
		creditCard.setExpiryDate(exp);
		creditCard.setCvvNo(cvv);
		PaymentResponse obj =null;
		try {
			String id = "Grocery";
			 obj = CreditCardService.pay(creditCard, amount, id,comments);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return obj;

	}

}
