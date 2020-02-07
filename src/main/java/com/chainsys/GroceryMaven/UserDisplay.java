package com.chainsys.GroceryMaven;

public class UserDisplay {
	// USER DISPLAY REVIEW

	private String productName;
	private int productId;
	private String manufacturer;
	private float quantity;
	private String unit;
	private int priceRS;
	private int stock;
	private String status;
	private int rating;
	private String review;

	private UserDisplay(String productName, int productId, String manufacturer, float quantity, String unit,
			int priceRS, int stock, String status, int rating, String review) {
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getPriceRS() {
		return priceRS;
	}

	public void setPriceRS(int priceRS) {
		this.priceRS = priceRS;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public UserDisplay() {
	}

	public String toString() {
		return "UserDisplay [productName=" + productName + ", productId=" + productId + ", manufacturer=" + manufacturer
				+ ", quantity=" + quantity + ", unit=" + unit + ", priceRS=" + priceRS + ", stock=" + stock
				+ ", status=" + status + ", rating=" + rating + ", review=" + review + "]";
	}

}
