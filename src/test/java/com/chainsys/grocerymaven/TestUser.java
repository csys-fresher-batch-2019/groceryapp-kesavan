package com.chainsys.grocerymaven;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.chainsys.util.LoggerGrocery;

public class TestUser {

	public static <ordersummary> void main(String[] args) throws Exception {

		LoggerGrocery out = LoggerGrocery.getInstance();
		UserProfileDaoImpl obj = new UserProfileDaoImpl();
		Scanner sc = new Scanner(System.in);
		int choice1 = 0;
		boolean paastest = true;
		ArrayList<UserDisplay> listproducts = new ArrayList<UserDisplay>();
		listproducts = obj.ViewProducts("");
		display(listproducts);
		out.getInput("\n1.New User\n2.Existing User");
		choice1 = sc.nextInt();
		if (choice1 == 1) {
			boolean pt = true, pt1 = true;
			String user, pass, address, mail = "";
			while (paastest) {
				out.getInput("Enter username");
				user = sc.next();
				if (obj.checkusernamecreate(user)) {
					while (pt) {
						out.getInput("Enter Mailid");
						mail = sc.next();
						if (obj.checkmailcreate(mail)) {
							while (pt1) {
								out.getInput("Enter Mobile number ");
								long mobile = sc.nextLong();
								if (obj.checkmobilenocreate(mobile)) {
									pt = false;
									pt1 = false;
									out.getInput("Enter your Password");
									pass = sc.next();
									out.getInput("Enter your Address");
									address = sc.next();
									int id = obj.CreateAccount(user, pass, address, mobile, mail);
									out.info("\n" + "Account Created Successfully");
									out.info("Your USERID  : " + id);
									paastest = false;
									logintest();
								} else {
									out.info("\nPhone number Already exixts ...");
								}
							}
						} else {
							out.info("\nMailId exists Try another one...");
						}
					}
				} else {
					out.info("\nUsername Already Exists Try Another name !!");
				}
			}
		} else {
			logintest();
		}
	}

	public static void logintest() throws Exception {

		LoggerGrocery out = LoggerGrocery.getInstance();
		Scanner sc = new Scanner(System.in);
		UserProfileDaoImpl obj = new UserProfileDaoImpl();
		int choice = 0;
		boolean test2 = true;
		String username = "";
		boolean test = true;
		boolean res = false, res1 = false;
		while (test2) {
			out.info("\nPlease Login !!! ");
			out.getInput("Enter Username");
			username = sc.next();
			out.getInput("Enter  password");
			String password = sc.next();
			res = obj.Login(username, password);
			if (res) {
				out.info("\nLogin Succesfull");
				test2 = false;
			} else {
				out.info("Invalid Username or password");
				out.info("1.TryAgain\n2.ForgotPassword");
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
			out.getInput(
					"\n1.View Products\n2.Sort By View Products\n3.Order Products\n4.View OrderSummary\n5.Cancel Order\n6.Track Order\n7.Change Password\n8.Change Address\n9.Close");
			choice = sc.nextInt();
			if (choice == 1) {
				listproducts = obj.ViewProducts("");
				display(listproducts);

			} else if (choice == 2) {
				out.getInput(
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
				ArrayList<UserProfile> orderproducts = new ArrayList<>();
				out.getInput("\nPress 1 for add products Press 0 for close");
				int test1 = 1, test3 = 1, n = 0;
				while (test1 == 1 && test3 == 1) {
					out.getInput("Enter product Id");
					int a = sc.nextInt();

					if (obj.checkproduct(a)) {
						out.getInput("Enter number of items");
						n = sc.nextInt();
						if (obj.checkstock(n, a) && n > 0) {
							UserProfile od = new UserProfile();
							od.setProductid(a);
							od.setNoOfItems(n);
							orderproducts.add(od);
							test1 = sc.nextInt();
							test3 = 1;
						} else {
							out.info("\nCheck the Stock Value");
							test3 = 0;
						}
					} else {
						out.info("\nEnter valid Product");
						test3 = 0;
					}
				}

				if (orderproducts.size() > 0 && n >= 1) {
					AdminProfileDaoImpl obj7 = new AdminProfileDaoImpl();
					int total = obj7.bill(orderproducts);
					System.out.println("Bill Amount : " + total);
					int amount = total + 70;
					System.out.println("Select payment type \n1.COD \n2.CreditCard\n3.Wallet");
					int type = sc.nextInt();
					if (type == 1) {
						String paytype = "COD";
						int id = 0;
						obj.PlaceOrder(orderproducts, username, paytype, id);
						out.info(" !!! Order Placed Successfully !!! ");
					} else if (type == 2) {
						String paytype = "CARD";
						System.out.println("Enter card number :");
						long cardnum = sc.nextLong();
						System.out.println("Enter expiry date :");
						String date = sc.next();
						LocalDate exp = LocalDate.parse(date);
						System.out.println("Enter cvv :");
						int cvv = sc.nextInt();
						System.out.println("Enter comments");
						String comments = sc.next();
						/*
						CreditCardAPI pay = new CreditCardAPI();
						PaymentResponse payment = pay.cardpayment(cardnum, exp, cvv, amount, comments);
						int transId = payment.getTransactionId();
						System.out.println(payment.isStatus());
						if (payment.isStatus()) {
							obj.PlaceOrder(orderproducts, username, paytype, transId);
							out.info(" !!! Order Placed Successfully !!! ");
						} else {
							out.info("Transaction failed");
							continue;
						}*/
					} else {
						String paytype = "CITIWALLET";
						
					}
				}
			} else if (choice == 4) {
				ArrayList<Ordersummary> orderproducts = new ArrayList<Ordersummary>();
				int user = obj.checkuserid(username);
				orderproducts = obj.ViewOrder(user);
				if (orderproducts.size() > 0) {
					for (Ordersummary obj1 : orderproducts) {
						out.info(obj1);
					}
				} else {
					out.info("\nNOT YET ANY ORDER PLACED");
				}
			} else if (choice == 5) {
				out.getInput("Enter Orderid");
				int id = sc.nextInt();
				if (obj.checkorderid(id)) {
					int days = obj.Trackordercancel(id);
					if (days == 0) {
						out.getInput("Type CONFIRM to cancel order");
						String a = sc.next();
						if (a.equals("CONFIRM")) {
							String result = obj.Cancelorder(id);
							out.info(result);
						} else {
							out.info("Try Again");
							continue;
						}
					} else {
						out.info("\nYOUR ORDER DISPATCHED !! NOT ABLE TO CANCEL IT");
					}

				} else {
					out.info("\nINVALID ORDERID");
				}

			} else if (choice == 6) {
				out.getInput("Enter Orderid");
				int id = sc.nextInt();
				if (obj.checkorderid(id)) {
					String n = obj.Trackorder(id);
					out.info(n);
					int days = obj.Trackordercancel(id);
					if (days > 3) {
						out.info("Press \n 1.Add Review \n 2.Skip");
						int choice3 = sc.nextInt();
						if (choice3 == 1) {
							if (obj.checkrating(id)) {
								out.getInput("Enter  Your Rating Out of 5");
								int rating = sc.nextInt();
								obj.Review(id, rating);
							} else {
								out.info("You Already reviewed this product");
								continue;
							}
						}
					} else {
						continue;
					}

				} else {
					out.info("Invalid OrderId");
				}
			} else if (choice == 7) {
				TestUser.forgot(username);
				test2 = false;

			} else if (choice == 8) {
				out.getInput("Enter your address ");
				String address = sc.next();
				obj.changeaddress(username, address);
			} else {
				test = false;
				out.info(" !!! THANK YOU !!! ");
			}
		}
	}

	private static void display(ArrayList<UserDisplay> listproducts) {
		LoggerGrocery out = LoggerGrocery.getInstance();
		for (UserDisplay ad : listproducts) {
			out.info(ad);
		}
	}

	public static boolean forgot(String user) throws Exception {
		LoggerGrocery out = LoggerGrocery.getInstance();
		Scanner sc = new Scanner(System.in);
		UserProfileDaoImpl obj = new UserProfileDaoImpl();
		out.getInput("Enter your MailId");
		String mail = sc.next();
		out.getInput("Enter New Password");
		String a = sc.next();
		out.getInput("Confirm Password");
		String b = sc.next();
		if (a.equals(b)) {
			if (obj.checkmailpass(mail, user, b)) {
				out.info("Password Updated Successfully ");
				TestUser.logintest();
			} else {
				out.info("Invalid credentials ");
			}
		} else {
			out.info("Password Mismatch !! ");
		}

		return false;
	}
}
