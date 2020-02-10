package com.chainsys.grocerymaven;

import java.util.ArrayList;
import java.util.Scanner;

import com.chainsys.util.LoggerGrocery;
import com.chainsys.grocerymaven.AdminProfile;
import com.chainsys.grocerymaven.AdminProfileDaoImpl;

public class TestAdmin {

	public static void main(String[] args) throws Exception {

		LoggerGrocery out = LoggerGrocery.getInstance();

		boolean test = true;
		Scanner scan = new Scanner(System.in);
		out.info("\n===== ADMIN PROFILE =====\n");
		AdminProfileDaoImpl obj = new AdminProfileDaoImpl();
		while (test) {

			out.getInput(" Press\n 1.Add Products \n 2.Update ProductStock \n 3.View Products\n 4.Close");
			out.getInput("\n Enter your Choice");

			int choice = scan.nextInt();
			switch (choice) {
			case 1:
				// ADD PRODUCTDETAILS
				AdminProfile obj1 = new AdminProfile();
				out.getInput("Enter productname :");
				obj1.setProductName(scan.next());
				out.getInput("Enter productid :");
				obj1.setProductId(scan.nextInt());
				out.getInput("Enter manufacturer :");
				obj1.setManufacturer(scan.next());
				out.getInput("Enter quantity :");
				obj1.setQuantity(scan.nextFloat());
				out.getInput("Enter Units :");
				obj1.setUnit(scan.next());
				out.getInput("Enter Price :");
				obj1.setPriceRS(scan.nextInt());
				out.getInput("Enter Stock :");
				obj1.setStock(scan.nextInt());
				AdminProfile[] p = { obj1 };
				obj.addProducts(p);
				break;

			case 2:
				// UPDATE STOCK
				out.getInput("Enter the stock value");
				int val = scan.nextInt();
				out.getInput("Enter the product id");
				int id = scan.nextInt();
				obj.updateProducts(val, id);
				break;
			case 3:
				// VIEW PRODUCTS
				ArrayList<AdminProfile> view1 = new ArrayList<AdminProfile>();
				view1 = obj.viewProducts();
				for (AdminProfile h : view1) {
					out.info(h);
				}
				break;
			default:
				test = false;
				break;
			}
		}
	}
}
