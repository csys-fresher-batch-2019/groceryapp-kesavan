package com.chainsys.GroceryMaven;

import java.sql.Date;

public class ordersummary{
	
	public int orderid;
	public String productname;
	public String manufacturer;
	public int noofitems;
	public int totalamount;
	public Date orderdate;
	public Date deliverydate;
	public String deliveryaddress;
	public String orderstatus;
	public String payment;

	public String toString() {
		return "ordersummary [orderid=" + orderid + ", productname=" + productname + ", manufacturer=" + manufacturer
				+ ", noofitems=" + noofitems + ", totalamount=" + totalamount + ", orderdate=" + orderdate
				+ ", deliverydate=" + deliverydate + ", deliveryaddress=" + deliveryaddress + ", orderstatus="
				+ orderstatus + ", payment=" + payment + "]";
	}

	/*public ordersummary(int orderid, String product_name, String manufacturer, int noofitems, int totalamount,
			Date orderdate, Date deliverydate, String deliveryaddress, String orderstatus, String payment) {
		super();
		this.orderid = orderid;
		this.productname = product_name;
		this.manufacturer = manufacturer;
		this.noofitems = noofitems;
		this.totalamount = totalamount;
		this.orderdate = orderdate;
		this.deliverydate = deliverydate;
		this.deliveryaddress = deliveryaddress;
		this.orderstatus = orderstatus;
		this.payment = payment;
	}*/

	//public  ordersummary() {
	//}
}
