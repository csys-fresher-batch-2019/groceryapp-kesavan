package com.chainsys.grocerymaven;

import java.util.Scanner;

import citiipay.implementation.TransactiondaoImpl;
import citiipay.messages.DBException;
import citiipay.models.Merchant;

public class Wallettest {
	public static void main(String[] args) throws DBException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Merchant Id:");
		String merchantId = sc.next();
		System.out.println("Enter the Mobile Number:");
		long mobileNo = sc.nextLong();
		System.out.println("Enter the Transfer Amount");
		int amount = sc.nextInt();
		TransactiondaoImpl obj1 = new TransactiondaoImpl();
		Merchant obj = new Merchant();
		obj = obj1.payToMerchant(merchantId, mobileNo, amount);
		System.out.println(obj.getStatus());
		if (obj.getStatus().equals("Transaction Successfull")) {
			System.out.println(obj.getTransactionId() + " is your transaction id");
		}

	}

}
