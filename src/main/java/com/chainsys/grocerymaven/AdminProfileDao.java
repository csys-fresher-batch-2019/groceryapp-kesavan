package com.chainsys.grocerymaven;

import java.util.ArrayList;

public interface AdminProfileDao {

	void addProducts(AdminProfile[] p);

	void createOrder(ArrayList<UserProfile> o, String user, String pay);

	void updateProducts(int value, int id);

	ArrayList<AdminProfile> viewProducts();

	int bill(ArrayList<UserProfile> ob);
}