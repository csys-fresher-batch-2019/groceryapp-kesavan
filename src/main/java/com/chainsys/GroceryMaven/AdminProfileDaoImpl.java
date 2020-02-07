package com.chainsys.GroceryMaven;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.chainsys.Util.Jdbcpst;

import com.chainsys.Util.databaseconnection;

public class AdminProfileDaoImpl implements AdminProfileDao {

	public void addProducts(AdminProfile[] p) throws Exception {
		for (AdminProfile obj : p) {
			Jdbcpst.preparestmt(
					"insert into products(product_name,product_id,manufacturer,quantity,unit,price_rs,stock)values('"
							+ obj.productName + "'," + obj.productId + ",'" + obj.manufacturer + "'," + obj.quantity
							+ ",'" + obj.unit + "'," + obj.priceRS + "," + obj.stock + ")");
			Jdbcpst.preparestmt(" update products set status='AVAILABLE'where stock > 0");
			Jdbcpst.preparestmt(" update products set status='OUTOFSTOCK',stock=0 where stock <= 0");
			Jdbcpst.preparestmt("insert into proreview(product_id) values(" + obj.productId + ")");
		}
	}

	public void userDetails(AdminProfile[] u) throws Exception {

		for (AdminProfile obj : u) {
			Jdbcpst.preparestmt(
					"insert into usersdata(user_id,password,phone_no,user_name,delivery_address,mail_id)  values(  "
							+ "se_name.nextval,'" + obj.password + "'," + obj.phoneno + ",'" + obj.username + "','"
							+ obj.deliveryaddress + "','" + obj.mail + "')");
		}
	}

	public void createOrder(ArrayList<UserProfile> ob, String user, String type) throws Exception {

		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		String sql2 = "select user_id from usersdata where user_name= '" + user + "'";
		ResultSet rs = stmt.executeQuery(sql2);
		int userId = 0;
		if (rs.next()) {
			userId = rs.getInt("user_id");
		}
		LocalDate today = LocalDate.now();
		LocalDate deliveryDate = today.plusDays(3);
		for (UserProfile obj1 : ob) {
			String sql = "select product_id,price_rs from products where product_id= ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, obj1.productid);
			ResultSet rs1 = pst.executeQuery();
			int productId = 0;
			int price = 0;
			if (rs1.next()) {
				productId = rs1.getInt("product_id");
				price = rs1.getInt("price_rs");
			}
			int totalBill = price * obj1.noOfItems;
			String payment = type;
			stmt.executeUpdate(
					"insert into orderdata(user_id,order_id,product_id,order_date,delivery_date,no_of_items,price_per_item,order_status,total_amount,payment) values( "
							+ userId + ",seq_name.nextval," + productId + ", to_date('" + today
							+ "','yyyy-MM-dd') , to_date( '" + deliveryDate + "','yyyy-MM-dd')," + obj1.noOfItems + ","
							+ price + ", 'ORDERED', " + totalBill + " ,'" + payment + "')");
			// stmt.executeUpdate(query);
			Jdbcpst.preparestmt("update products p set p.stock=p.stock- ?  where product_id =?", obj1.noOfItems,
					productId);
			Jdbcpst.preparestmt("update products set status='OUT OF STOCK',stock=0 where stock<=0");
		}
		con.close();

	}

	public void updateProducts(int value, int id) throws Exception {
		Jdbcpst.preparestmt("update products set stock = stock+? where product_id=?", value, id);
		Jdbcpst.preparestmt("update products set status='AVAILABLE'where stock > 0");
		Jdbcpst.preparestmt(" update products set status='OUTOFSTOCK',stock=0 where stock <= 0");
	}

	public ArrayList<AdminProfile> viewProducts() throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		ArrayList<AdminProfile> view = new ArrayList<AdminProfile>();
		String sql = "select * from products";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			AdminProfile ap = new AdminProfile();
			ap.productName = rs.getString("product_name");
			ap.productId = rs.getInt("product_Id");
			ap.manufacturer = rs.getString("manufacturer");
			ap.quantity = rs.getFloat("quantity");
			ap.unit = rs.getString("unit");
			ap.priceRS = rs.getInt("price_rs");
			ap.stock = rs.getInt("stock");
			ap.status = rs.getString("status");
			view.add(ap);
		}
		con.close();
		return view;
	}

	public int bill(ArrayList<UserProfile> ob) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		int amount = 0;
		for (UserProfile obj1 : ob) {
			String sql = "select product_id,price_rs from products where product_id= ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, obj1.productid);
			ResultSet rs1 = pst.executeQuery();
			int productId = 0;
			int price = 0;
			if (rs1.next()) {
				productId = rs1.getInt("product_id");
				price = rs1.getInt("price_rs");
			}
			int totalBill = price * obj1.noOfItems;

			amount = amount + totalBill;
		}
		con.close();
		return amount;
	}
}
