package com.chainsys.grocerymaven;

import java.util.ArrayList;

public interface AdminProfileDao {

	void addProducts(AdminProfile[] p);
	
	void userDetails(AdminProfile[] u);

	void createOrder(ArrayList<UserProfile> o, String user, String pay,int id);

	void updateProducts(int value, int id);

	ArrayList<AdminProfile> viewProducts();

	int bill(ArrayList<UserProfile> ob);
}
