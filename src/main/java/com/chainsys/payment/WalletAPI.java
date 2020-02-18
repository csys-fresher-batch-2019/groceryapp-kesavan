package com.chainsys.payment;

import citiipay.implementation.TransactiondaoImpl;
import citiipay.messages.DBException;
import citiipay.models.Merchant;

public class WalletAPI {
	public Merchant cardpayment(long mobileNo, int amount) throws DBException {
		String merchantId="GROCERY";
		TransactiondaoImpl obj1 = new TransactiondaoImpl();
		Merchant obj = new Merchant();
		obj=obj1.payToMerchant(merchantId, mobileNo, amount);
		return obj;
	}
}