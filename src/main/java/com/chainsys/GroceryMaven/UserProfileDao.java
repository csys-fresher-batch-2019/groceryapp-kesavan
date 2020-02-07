package com.chainsys.GroceryMaven;

import java.util.ArrayList;

public interface UserProfileDao {

	int CreateAccount(String user, String pass, String address, long mobile, String mail) throws Exception;

	boolean Login(String username, String password) throws Exception;

	void Forgotpassword(String mailid, String password) throws Exception;

	ArrayList<UserDisplay> ViewProducts(String a) throws Exception;

	ArrayList<UserProfile> PlaceOrder(ArrayList<?> o, String username,String payment) throws Exception;

	ArrayList<ordersummary> ViewOrder(int userid) throws Exception;

	void Review(int id, int rating) throws Exception;

	String Cancelorder(int orderid) throws Exception;

	String Trackorder(int orderid) throws Exception;
	
	int Trackordercancel(int orderid) throws Exception;


	boolean checkusername(String username) throws Exception;

	boolean checkmobileno(long mobile) throws Exception;

	boolean checkproduct(int product) throws Exception;

	boolean checkstock(int noofitems, int product) throws Exception;

	int checkuserid(String user) throws Exception;

	boolean checkorderid(int orderid) throws Exception;

	boolean checkmail(String mail) throws Exception;

}
