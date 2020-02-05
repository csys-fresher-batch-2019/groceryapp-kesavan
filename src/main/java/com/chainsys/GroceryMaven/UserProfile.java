package com.chainsys.GroceryMaven;

public class UserProfile {

	// ORDER DETAILS
	int productid;
	int noOfItems;


	public UserProfile(int productid, int noOfItems) {
		super();
		this.productid = productid;
		this.noOfItems = noOfItems;
	}
	public UserProfile() {
	}

	@Override
	public String toString() {
		return "UserProfile [productid=" + productid + ", noOfItems=" + noOfItems + "]";
	}


}
