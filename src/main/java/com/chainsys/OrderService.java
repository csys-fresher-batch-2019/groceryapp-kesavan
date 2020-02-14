package com.chainsys;

import java.util.ArrayList;

import com.chainsys.dto.CreditCardDTO;
import com.chainsys.grocerymaven.AdminProfileDao;
import com.chainsys.grocerymaven.AdminProfileDaoImpl;
import com.chainsys.grocerymaven.UserProfile;

public class OrderService {

	private static boolean pay(CreditCardDTO dto) {
		boolean paymentStatus = false;
		// CreditCardAPI pay = new CreditCardAPI();
		// PaymentResponse obj = null;
		// pay.cardpayment(cardnum, exp, cvv, amount, comments);
		paymentStatus = true;
		return paymentStatus;
	}

	public static void createOrder(ArrayList<UserProfile> items, String username, CreditCardDTO dto) throws Exception {
		int transactionId = 1;
		try {
			boolean status = pay(dto);
			if (status) {
				AdminProfileDao obj1 = new AdminProfileDaoImpl();
				obj1.createOrder(items, username, "CARD", transactionId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to place order");
		}
	}
}
