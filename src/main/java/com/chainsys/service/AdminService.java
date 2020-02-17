package com.chainsys.service;

import java.util.ArrayList;

import com.chainsys.grocerymaven.AdminProfile;
import com.chainsys.grocerymaven.AdminProfileDao;
import com.chainsys.grocerymaven.AdminProfileDaoImpl;
import com.chainsys.grocerymaven.UserProfile;

public class AdminService {

	private AdminProfileDao obj = new AdminProfileDaoImpl();

	public void addProducts(AdminProfile[] p) {
		obj.addProducts(p);
	}

	public void userDetails(AdminProfile[] u) {
		obj.userDetails(u);
	}

	public void createOrder(ArrayList<UserProfile> ob, String user, String type, int id) {
		obj.createOrder(ob, user, type, id);
	}

	public void updateProducts(int value, int id) {
		obj.updateProducts(value, id);
	}

	public ArrayList<AdminProfile> viewProducts() {
		ArrayList<AdminProfile> pro=obj.viewProducts();
		return pro;
	}

	public int bill(ArrayList<UserProfile> ob) {
		int amount=obj.bill(ob);
		return amount;
	}
}
