package com.chainsys.GroceryMaven;

public class AdminProfile {

	// PRODUCTS
	public String productName;
	public int productId;
	public String manufacturer;
	public float quantity;
	public String unit;
	public int priceRS;
	public int stock;
	public String status;

	public AdminProfile(String productName, int productId, String manufacturer, float quantity, String unit, int price,
			int stock) {
		super();
		this.productName = productName;
		this.productId = productId;
		this.manufacturer = manufacturer;
		this.quantity = quantity;
		this.unit = unit;
		this.priceRS = price;
		this.stock = stock;
	}

	// USER
	public int userid;
	public String password;
	public String username;
	public long phoneno;
	public String deliveryaddress;
	public String mail;

	public AdminProfile(String password, String username, String deliveryaddress, long phoneno,String mail) {
		super();
		this.password = password;
		this.username = username;
		this.deliveryaddress = deliveryaddress;
		this.phoneno = phoneno;
		this.mail=mail;
	}

	// ORDER DETAILS
	int noOfItems;
	String products;

	public AdminProfile(String products, int noOfItems) {
		super();
		this.products = products;
		this.noOfItems = noOfItems;
	}

	public AdminProfile() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "AdminProfile [productName=" + productName + ", productId=" + productId + ", manufacturer="
				+ manufacturer + ", quantity=" + quantity + ", unit=" + unit + ", priceRS=" + priceRS + ", stock=" + stock
				+ ", status=" + status + "]";
	}

}
