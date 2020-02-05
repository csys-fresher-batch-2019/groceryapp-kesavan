package com.chainsys.GroceryMaven;

import java.util.ArrayList;

public interface AdminProfileDao {

	void addProducts(AdminProfile[] p) throws Exception;

	void userDetails(AdminProfile[] u) throws Exception;

	void createOrder(ArrayList<UserProfile> o, String user) throws Exception;

	void updateProducts(int value, int id) throws Exception;

	ArrayList<AdminProfile> viewProducts() throws Exception;
}
