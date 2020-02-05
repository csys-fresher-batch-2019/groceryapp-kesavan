package com.chainsys.GroceryMaven;

public class UserDisplay {
	//USER DISPLAY REVIEW
	
	public String productName;
	public int productId;
	public String manufacturer;
	public float quantity;
	public String unit;
	public int priceRS;
	public int stock;
	public String status;
	public int rating;
	public String review;
	public UserDisplay(String productName, int productId, String manufacturer, float quantity, String unit, int priceRS,
			int stock, String status, int rating, String review) {
		super();
		this.productName = productName;
		this.productId = productId;
		this.manufacturer = manufacturer;
		this.quantity = quantity;
		this.unit = unit;
		this.priceRS = priceRS;
		this.stock = stock;
		this.status = status;
		this.rating = rating;
		this.review = review;
	}
	public UserDisplay() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserDisplay [productName=" + productName + ", productId=" + productId + ", manufacturer=" + manufacturer
				+ ", quantity=" + quantity + ", unit=" + unit + ", priceRS=" + priceRS + ", stock=" + stock
				+ ", status=" + status + ", rating=" + rating + ", review=" + review + "]";
	}
	
}
