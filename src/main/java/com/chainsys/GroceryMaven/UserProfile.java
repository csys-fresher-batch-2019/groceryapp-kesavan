package com.chainsys.GroceryMaven;

public class UserProfile {

	// ORDER DETAILS
	private int productid;
	private int noOfItems;

	

	public UserProfile() {
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}

	public String toString() {
		return "UserProfile [productid=" + productid + ", noOfItems=" + noOfItems + "]";
	}

}
