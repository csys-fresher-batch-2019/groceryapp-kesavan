package com.chainsys.GroceryMaven;

import java.util.ArrayList;
import java.util.Scanner;

import com.chainsys.Util.LoggerGrocery;

public class TestAdmin {

	public static void main(String[] args) throws Exception {

		LoggerGrocery LOGGER = LoggerGrocery.getInstance();

		boolean test = true;
		Scanner scan = new Scanner(System.in);
		LOGGER.info("\n===== ADMIN PROFILE =====\n");
		AdminProfileDaoImpl obj = new AdminProfileDaoImpl();
		while (test) {

			LOGGER.getInput(
					" Press\n 1.Add Products \n 2.Add User Info \n 3.Update ProductStock \n 4.View Products\n 5.Close");
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
				// ADD USERDETAILS
				AdminProfile u1 = new AdminProfile("Ravi1105", "Ravi", "53,Kumaran nagar,Kovai", 9090909090L,
						"ravi@gmail.com");
				AdminProfile u2 = new AdminProfile("Hari1106", "Hari", "23,Bharathi nagar,Kovai", 8080808080L,
						"hari@gmail.com");
				AdminProfile u3 = new AdminProfile("Kumar1107", "Kumar", "83,Gandhi nagar,Kovai", 7070707070L,
						"kumar@gmail.com");
				AdminProfile[] u = { u1, u2, u3 };
				obj.userDetails(u);
				break;
			case 3:
				// UPDATE STOCK
				LOGGER.getInput("Enter the stock value");
				int val = scan.nextInt();
				LOGGER.getInput("Enter the product id");
				int id = scan.nextInt();
				obj.updateProducts(val, id);
				break;
			case 4:
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
