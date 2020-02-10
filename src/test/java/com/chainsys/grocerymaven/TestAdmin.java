package com.chainsys.grocerymaven;

import java.util.ArrayList;
import java.util.Scanner;

import com.chainsys.util.LoggerGrocery;
import com.chainsys.grocerymaven.AdminProfile;
import com.chainsys.grocerymaven.AdminProfileDaoImpl;

public class TestAdmin {

	public static void main(String[] args) throws Exception {

		LoggerGrocery LOGGER = LoggerGrocery.getInstance();

		boolean test = true;
		Scanner scan = new Scanner(System.in);
		LOGGER.info("\n===== ADMIN PROFILE =====\n");
		AdminProfileDaoImpl obj = new AdminProfileDaoImpl();
		while (test) {

			LOGGER.getInput(" Press\n 1.Add Products \n 2.Update ProductStock \n 3.View Products\n 4.Close");
			LOGGER.getInput("\n Enter your Choice");

			int choice = scan.nextInt();
			switch (choice) {
			case 1:
				// ADD PRODUCTDETAILS
				AdminProfile obj1 = new AdminProfile();
				LOGGER.getInput("Enter productname :");
				obj1.setProductName(scan.next());
				LOGGER.getInput("Enter productid :");
				obj1.setProductId(scan.nextInt());
				LOGGER.getInput("Enter manufacturer :");
				obj1.setManufacturer(scan.next());
				LOGGER.getInput("Enter quantity :");
				obj1.setQuantity(scan.nextFloat());
				LOGGER.getInput("Enter Units :");
				obj1.setUnit(scan.next());
				LOGGER.getInput("Enter Price :");
				obj1.setPriceRS(scan.nextInt());
				LOGGER.getInput("Enter Stock :");
				obj1.setStock(scan.nextInt());
				AdminProfile[] p = { obj1 };
				obj.addProducts(p);
				break;

			case 2:
				// UPDATE STOCK
				LOGGER.getInput("Enter the stock value");
				int val = scan.nextInt();
				LOGGER.getInput("Enter the product id");
				int id = scan.nextInt();
				obj.updateProducts(val, id);
				break;
			case 3:
				// VIEW PRODUCTS
				ArrayList<AdminProfile> view1 = new ArrayList<AdminProfile>();
				view1 = obj.viewProducts();
				for (AdminProfile h : view1) {
					LOGGER.info(h);
				}
				break;
			default:
				test = false;
				break;
			}
		}
	}
}
