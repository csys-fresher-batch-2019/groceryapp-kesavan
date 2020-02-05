package com.chainsys.GroceryMaven;

import java.util.ArrayList;
import java.util.Scanner;
import com.chainsys.GroceryMaven.UserProfileDaoImpl.ordersummary;
import com.chainsys.Util.LoggerGrocery;

public class TestUser {

	public static <ordersummary> void main(String[] args) throws Exception {

		LoggerGrocery LOGGER = LoggerGrocery.getInstance();
		UserProfileDaoImpl obj = new UserProfileDaoImpl();
		Scanner sc = new Scanner(System.in);
		int choice1 = 0;
		boolean paastest = true;
		ArrayList<UserDisplay> listproducts = new ArrayList<UserDisplay>();
		listproducts = obj.ViewProducts("");
		display(listproducts);
		LOGGER.getInput("\n1.New User\n2.Existing User");
		choice1 = sc.nextInt();
		if (choice1 == 1) {
			boolean pt = true, pt1 = true;
			String user, pass, address, mail = "";
			while (paastest) {
				LOGGER.getInput("Enter username");
				user = sc.next();
				if (obj.checkusernamecreate(user)) {
					while (pt) {
						LOGGER.getInput("Enter Mailid");
						mail = sc.next();
						if (obj.checkmailcreate(mail)) {
							while (pt1) {
								LOGGER.getInput("Enter Mobile number ");
								long mobile = sc.nextLong();
								if (obj.checkmobilenocreate(mobile)) {
									pt = false;
									pt1 = false;
									LOGGER.getInput("Enter your Password");
									pass = sc.next();
									LOGGER.getInput("Enter your Address");
									address = sc.next();
									int id = obj.CreateAccount(user, pass, address, mobile, mail);
									LOGGER.info("\n" + "Account Created Successfully");
									LOGGER.info("Your USERID  : " + id);
									paastest = false;
									logintest();
								} else {
									LOGGER.info("\nPhone number Already exixts ...");
								}
							}
						} else {
							LOGGER.info("\nMailId exists Try another one...");
						}
					}
				} else {
					LOGGER.info("\nUsername Already Exists Try Another name !!");
				}
			}
		} else {
			logintest();
		}
	}

	public static void logintest() throws Exception {

		LoggerGrocery LOGGER = LoggerGrocery.getInstance();
		Scanner sc = new Scanner(System.in);
		UserProfileDaoImpl obj = new UserProfileDaoImpl();
		int choice = 0;
		boolean test2 = true;
		String username = "";
		boolean test = true;
		boolean res = false, res1 = false;
		while (test2) {
			LOGGER.info("\nPlease Login !!! ");
			LOGGER.getInput("Enter Username");
			username = sc.next();
			LOGGER.getInput("Enter  password");
			String password = sc.next();
			res = obj.Login(username, password);
			if (res) {
				LOGGER.info("\nLogin Succesfull");
				test2 = false;
			} else {
				LOGGER.info("Invalid Username or password");
				LOGGER.info("1.TryAgain\n2.ForgotPassword");
				int n = sc.nextInt();
				if (n == 1) {
				} else {
					TestUser.forgot(username);
					test2 = false;
				}
			}
		}
		while (test && res) {

			ArrayList<UserDisplay> listproducts = new ArrayList<UserDisplay>();
			LOGGER.getInput(
					"\n1.View Products\n2.Sort By View Products\n3.Order Products\n4.View OrderSummary\n5.Cancel Order\n6.Track Order\n7.Change Password\n8.Change Address\n9.Close");
			choice = sc.nextInt();
			if (choice == 1) {
				listproducts = obj.ViewProducts("");
				display(listproducts);

			} else if (choice == 2) {
				LOGGER.getInput(
						"\n Choose \n1.By Price Low to High\n2.By Price High to Low\n3.By Rating Low to High\n4.By Rating High to Low");
				int n = sc.nextInt();
				if (n == 1) {
					listproducts = obj.ViewProducts(" order by  p.price_rs asc");
					display(listproducts);

				} else if (n == 2) {
					listproducts = obj.ViewProducts(" order by  p.price_rs desc");
					display(listproducts);

				} else if (n == 3) {
					listproducts = obj.ViewProducts("  order by pr.rating asc");
					display(listproducts);

				} else if (n == 4) {
					listproducts = obj.ViewProducts(" order by pr.rating desc");
					display(listproducts);

				} else {
					continue;
				}
			} else if (choice == 3) {
				// create order
				ArrayList<UserProfile> orderproducts = new ArrayList<UserProfile>();
				LOGGER.getInput("\nPress 1 for add products Press 0 for close");
				int test1 = 1, test3 = 1, n = 0;
				while (test1 == 1 && test3 == 1) {
					LOGGER.getInput("Enter product Id");
					int a = sc.nextInt();
					if (obj.checkproduct(a)) {
						LOGGER.getInput("Enter number of items");
						n = sc.nextInt();
						if (obj.checkstock(n, a)) {
							test1 = sc.nextInt();
							test3 = 1;
							UserProfile od = new UserProfile(a, n);
							orderproducts.add(od);
						} else {
							LOGGER.info("\nCheck the Stock Value");
							test3 = 0;
						}
					} else {
						LOGGER.info("\nEnter valid Product");
						test3 = 0;
					}
				}
				if (orderproducts.size() > 0 && n >= 1) {
					obj.PlaceOrder(orderproducts, username);
					LOGGER.info(" !!! Order Placed Successfully !!! ");
				}
			} else if (choice == 4) {
				ArrayList<ordersummary> orderproducts = new ArrayList<ordersummary>();
				int user = obj.checkuserid(username);
				orderproducts = obj.ViewOrder(user);
				if (orderproducts.size() > 0) {
					for (ordersummary obj1 : orderproducts) {
						LOGGER.info(obj1);
					}
				} else {
					LOGGER.info("\nNOT YET ANY ORDER PLACED");
				}
			} else if (choice == 5) {
				LOGGER.getInput("Enter Orderid");
				int id = sc.nextInt();
				if (obj.checkorderid(id)) {
					int days = obj.Trackorder(id);
					if (days == 0) {
						LOGGER.getInput("Type CONFIRM to cancel order");
						String a = sc.next();
						if (a.equals("CONFIRM")) {
							String result = obj.Cancelorder(id);
							LOGGER.info(result);
						} else {
							LOGGER.info("Try Again");
							continue;
						}
					} else {
						LOGGER.info("\nYOUR ORDER DISPATCHED !! NOT ABLE TO CANCEL IT");
					}

				} else {
					LOGGER.info("\nINVALID ORDERID");
				}

			} else if (choice == 6) {
				LOGGER.getInput("Enter Orderid");
				int id = sc.nextInt();
				if (obj.checkorderid(id)) {
					int n = obj.Trackorder(id);
					if (n == 0) {
						LOGGER.info(" \n !! ORDERED !! ");
					} else if (n == 1) {
						LOGGER.info(" \n !! DISPATCHED  WAIT FOR 2 MORE DAYS !! ");
					} else if (n == 2) {
						LOGGER.info(" \n !! SHIPPED WAIT FOR 1 MORE DAY !! ");
					} else if (n >= 3) {
						LOGGER.info(" \n !! DELIVERED !! ");
						LOGGER.info("Press \n 1.Add Review \n 2.Skip");
						int choice3 = sc.nextInt();
						if (choice3 == 1) {
							if (obj.checkrating(id)) {
								LOGGER.getInput("Enter  Your Rating Out of 5");
								int rating = sc.nextInt();
								obj.Review(id, rating);
							} else {
								LOGGER.info("You Already reviewed this product");
								continue;
							}
						} else {
							continue;
						}
					}
				} else {
					LOGGER.info("Invalid OrderId");
				}
			} else if (choice == 7) {
				TestUser.forgot(username);
				test2 = false;

			} else if (choice == 8) {
				LOGGER.getInput("Enter your address ");
				String address = sc.next();
				obj.changeaddress(username, address);
			} else {
				test = false;
				LOGGER.info(" !!! THANK YOU !!! ");
			}
		}
	}

	private static void display(ArrayList<UserDisplay> listproducts) {
		LoggerGrocery LOGGER = LoggerGrocery.getInstance();
		for (UserDisplay ad : listproducts) {
			LOGGER.info(ad);
		}
	}

	public static boolean forgot(String user) throws Exception {
		LoggerGrocery LOGGER = LoggerGrocery.getInstance();
		Scanner sc = new Scanner(System.in);
		UserProfileDaoImpl obj = new UserProfileDaoImpl();
		String user1 = user;
		if (obj.checkusername(user1)) {
			LOGGER.getInput("Enter your MailId");
			String mail = sc.next();
			if (obj.checkmail(mail)) {
				LOGGER.getInput("Enter New Password");
				String a = sc.next();
				LOGGER.getInput("Confirm Password");
				String b = sc.next();
				if (a.equals(b)) {
					obj.Forgotpassword(mail, b);
					LOGGER.info("Password Updated Successfully ");
					TestUser.logintest();
				} else {
					LOGGER.info("Password Mismatch !! ");
				}
			} else {
				LOGGER.info("Invalid mailId");
			}
		} else {
			LOGGER.info("Invalid Username");
		}
		return false;
	}
}
