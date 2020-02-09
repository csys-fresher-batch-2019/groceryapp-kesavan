package com.chainsys.grocerymaven;

import java.util.ArrayList;

public interface UserProfileDao {

	int CreateAccount(String user, String pass, String address, long mobile, String mail);

	boolean Login(String username, String password);

	void Forgotpassword(String mailid, String password);

	ArrayList<UserDisplay> ViewProducts(String a);

	ArrayList<UserProfile> PlaceOrder(ArrayList<?> o, String username, String payment);

	ArrayList<Ordersummary> ViewOrder(int userid);

	void Review(int id, int rating);

	String Cancelorder(int orderid);

	String Trackorder(int orderid);

	int Trackordercancel(int orderid);

	boolean checkusername(String username);

	boolean checkmobileno(long mobile);

	boolean checkproduct(int product);

	boolean checkstock(int noofitems, int product);

	int checkuserid(String user);

	boolean checkorderid(int orderid);

	boolean checkmail(String mail);

}
