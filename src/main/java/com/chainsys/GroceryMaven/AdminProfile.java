package com.chainsys.GroceryMaven;

public class AdminProfile {

	// PRODUCTS
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

	private String productName;
	private int productId;
	private String manufacturer;
	private float quantity;
	private String unit;
	private int priceRS;
	private int stock;
	private String status;

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
	private int userid;
	private String password;
	private String username;
	private long phoneno;
	private String deliveryaddress;
	private String mail;

	public AdminProfile(String password, String username, String deliveryaddress, long phoneno, String mail) {
		super();
		this.password = password;
		this.username = username;
		this.deliveryaddress = deliveryaddress;
		this.phoneno = phoneno;
		this.mail = mail;
	}

	// ORDER DETAILS
	private int noOfItems;
	private String products;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}

	public String getDeliveryaddress() {
		return deliveryaddress;
	}

	public void setDeliveryaddress(String deliveryaddress) {
		this.deliveryaddress = deliveryaddress;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

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
				+ manufacturer + ", quantity=" + quantity + ", unit=" + unit + ", priceRS=" + priceRS + ", stock="
				+ stock + ", status=" + status + "]";
	}

}
