package com.chainsys.GroceryMaven;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.chainsys.Util.Jdbcpst;
import com.chainsys.Util.databaseconnection;
import com.chainsys.GroceryMaven.ordersummary;

public class UserProfileDaoImpl implements UserProfileDao {

	// CREATE ACCOUNT
	public int CreateAccount(String user, String pass, String address, long mobile, String mail) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		String sql = "insert into usersdata(user_id,user_name,delivery_address,password,phone_no,mail_id) "
				+ "values(se_name.nextval,?,?,?,?,?)";
		Jdbcpst.preparestmt(sql, user, address, pass, mobile, mail);
		String sql1 = "select user_id from usersdata where user_name='" + user + "'";
		stmt.executeUpdate(sql1);
		ResultSet rs = stmt.executeQuery(sql1);
		rs.next();
		int id = rs.getInt("user_id");
		con.close();
		return id;

	}

	// LOGIN
	public boolean Login(String username, String pass) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		String sql = "select user_name,password from usersdata where user_name = '" + username + "' and password = '"
				+ pass + "'";
		ResultSet rs1 = stmt.executeQuery(sql);
		if (rs1.next()) {
			return true;
		}
		con.close();
		return false;
	}

	// VIEW PRODUCTS
	public ArrayList<UserDisplay> ViewProducts(String a) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		ArrayList<UserDisplay> products = new ArrayList<UserDisplay>();
		String sql = "select p.*,pr.review,pr.rating  from products p,proreview pr where p.product_id= pr.product_id "
				+ a;
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			UserDisplay ap = new UserDisplay();
			ap.productName = rs.getString("product_name");
			ap.productId = rs.getInt("product_Id");
			ap.manufacturer = rs.getString("manufacturer");
			ap.quantity = rs.getFloat("quantity");
			ap.unit = rs.getString("unit");
			ap.priceRS = rs.getInt("price_rs");
			ap.stock = rs.getInt("stock");
			ap.status = rs.getString("status");
			ap.review = rs.getString("review");
			ap.rating = rs.getInt("rating");
			products.add(ap);
		}
		con.close();
		return products;
	}

	// PLACE ORDER
	public ArrayList<UserProfile> PlaceOrder(ArrayList a, String username, String type) throws Exception {
		AdminProfileDaoImpl obj = new AdminProfileDaoImpl();
		obj.createOrder(a, username, type);
		return null;
	}

	// VIEW ORDERSUMMARY
	public ArrayList<ordersummary> ViewOrder(int userid) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		ArrayList<ordersummary> productsview = new ArrayList<ordersummary>();
		LocalDate today = LocalDate.now();
		Jdbcpst.preparestmt("update orderdata set order_status = 'DELIVERED' where to_date('" + today
				+ "','yyyy-MM-dd') = delivery_date");
		String sql = "select order_id,product_name,manufacturer,no_of_items,price_per_item,total_amount,"
				+ "order_date,delivery_date,delivery_address,order_status,payment from orderdata o "
				+ "inner join products p on p.product_id=o.product_id and user_id=" + userid + ""
				+ "inner join usersdata u on u.user_id=" + userid + "";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			ordersummary os = new ordersummary();
			os.orderid = rs.getInt("order_id");
			os.productname = rs.getString("product_name");
			os.manufacturer = rs.getString("manufacturer");
			os.noofitems = rs.getInt("no_of_items");
			os.totalamount = rs.getInt("total_amount");
			os.orderdate = rs.getDate("order_date");
			os.deliverydate = rs.getDate("delivery_date");
			os.deliveryaddress = rs.getString("delivery_address");
			os.orderstatus = rs.getString("order_status");
			os.payment = rs.getString("payment");
			productsview.add(os);
		}
		con.close();
		return productsview;
	}

	// CANCELORDER
	public String Cancelorder(int orderid) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		String sql2 = "select product_id from orderdata where order_id=" + orderid;
		ResultSet rs = stmt.executeQuery(sql2);
		rs.next();
		int id = rs.getInt("product_id");
		Jdbcpst.preparestmt(
				"update products p set p.stock=p.stock+ (select no_of_items from orderdata  where product_id = " + id
						+ " and order_id=" + orderid + ") where p.product_id = " + id + "");
		Jdbcpst.preparestmt("delete from orderdata where order_id= ?", orderid);
		Jdbcpst.preparestmt("update products set status='AVAILABLE'where stock > 0");
		Jdbcpst.preparestmt(" update products set status='OUTOFSTOCK'where stock <= 0");
		String result = "CANCELLED SUCCESSFULLY";
		con.close();
		return result;
	}

	// TRACKORDER
	public String Trackorder(int orderid) throws Exception {
		String s = "";
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		String sql = "select order_date from orderdata where order_id=" + orderid;
		stmt.executeUpdate(sql);
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		String date = rs.getString("order_date");
		String local = date.substring(0, 10);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(local, formatter);
		int n = Period.between(date1, LocalDate.now()).getDays();
		if (n == 0) {
			s = " \n !! ORDERED !! ";
		} else if (n == 1) {
			s = " \n !! DISPATCHED  WAIT FOR 2 MORE DAYS !! ";
		} else if (n == 2) {
			s = " \n !! SHIPPED WAIT FOR 1 MORE DAY !! ";
		} else if (n >= 3) {
			s = " \n !! DELIVERED !! ";
		}
		con.close();
		return s;
	}

	// ADD REVIEW
	public void Review(int orderid, int rating) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		String sql4 = "select product_id from orderdata where order_id=" + orderid;
		ResultSet rs1 = stmt.executeQuery(sql4);
		rs1.next();
		int productId = rs1.getInt("product_id");
		Jdbcpst.preparestmt("insert into review(order_id,product_id,rating)values(?,?,?)", orderid, productId, rating);
		String sql1 = "select avg(rating) as avrg from review where product_id=" + productId;
		ResultSet rs = stmt.executeQuery(sql1);
		rs.next();
		float avg = rs.getFloat("avrg");
		if (avg >= 4) {
			Jdbcpst.preparestmt("update proreview set review='Good',rating=? where product_id=?", avg, productId);
		} else if (avg >= 3 && avg < 4) {
			Jdbcpst.preparestmt("update proreview set review='Better',rating=? where product_id=?", avg, productId);
		} else {
			Jdbcpst.preparestmt("update proreview set review='Bad',rating=? where product_id=?", avg, productId);
		}
	}

	// CHANGE PASSWORD
	public void Forgotpassword(String mail, String pass) throws Exception {
		Jdbcpst.preparestmt("update usersdata set password = ? where mail_id=?", pass, mail);
	}

	// CHANGE ADDRESS
	public void changeaddress(String username, String address) throws Exception {
		UserProfileDaoImpl obj = new UserProfileDaoImpl();
		int id = obj.checkuserid(username);
		Jdbcpst.preparestmt("update usersdata set delivery_address= ? where user_id= ?", address, id);

	}

	// USERNAME CHECK FOR FORGOT PASSWORD
	public boolean checkusername(String username) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		if (stmt.executeUpdate("select user_name from usersdata where user_name='" + username + "'") != 0) {
			con.close();
			return true;
		}
		con.close();
		return false;
	}

	// MOBILE NO CHECK
	public boolean checkmobileno(long mobile) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		if (stmt.executeUpdate("select phone_no from usersdata where phone_no='" + mobile + "'") == 1) {
			con.close();
			return false;
		}
		con.close();
		return true;
	}

	// CHECK VALID PRODUCT
	public boolean checkproduct(int product) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		if (stmt.executeUpdate("select product_id from products where product_id=" + product) != 1) {
			con.close();
			return false;
		}
		con.close();
		return true;
	}

	// CHECK STOCK
	public boolean checkstock(int noofitems, int product) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select stock from products where product_id =" + product);
		rs.next();
		int stock = rs.getInt("stock");
		if (noofitems <= stock) {
			con.close();
			return true;
		}
		con.close();
		return false;
	}

	// DISPLAY USERID
	public int checkuserid(String user) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		String sql = "select user_id from usersdata where user_name='" + user + "'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int user1 = rs.getInt("user_id");
		con.close();
		return user1;
	}

	// CHECK ORDERID
	public boolean checkorderid(int orderid) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		if (stmt.executeUpdate("select order_id from orderdata where order_id='" + orderid + "'") == 1) {
			con.close();
			return true;
		}
		con.close();
		return false;
	}

	// CHECKID
	public boolean checkid(int userid) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		if (stmt.executeUpdate("select user_id from usersdata where user_id=" + userid) != 0) {
			con.close();
			return true;
		}
		con.close();
		return false;
	}

	// CHECK MAIL
	public boolean checkmail(String mail) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		if (stmt.executeUpdate("select mail_id from usersdata where mail_id='" + mail + "'") != 0) {
			con.close();
			return true;
		}
		con.close();
		return false;
	}

	// CHECK MAIL FOR ACC CREATION
	public boolean checkmailcreate(String mail) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		if (stmt.executeUpdate("select mail_id from usersdata where mail_id='" + mail + "'") == 0) {
			con.close();
			return true;
		}
		con.close();
		return false;
	}

	// CHECK USERNAME FOR ACC CREATION
	public boolean checkusernamecreate(String username) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		if (stmt.executeUpdate("select user_name from usersdata where user_name='" + username + "'") == 0) {
			con.close();
			return true;
		}
		con.close();
		return false;
	}

	// CHECK MOBILE NO FOR ACC CREATION
	public boolean checkmobilenocreate(long mobile) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		if (stmt.executeUpdate("select phone_no from usersdata where phone_no='" + mobile + "'") == 0) {
			con.close();
			return true;
		}
		con.close();
		return false;
	}

	// CHECK FOR RATING IF ALREADY REVIEWED
	public boolean checkrating(int id) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		if (stmt.executeUpdate("select order_id from review where order_id=" + id) == 0) {
			con.close();
			return true;
		}
		con.close();
		return false;
	}

	// TRACK FOR CAMCEL
	public int Trackordercancel(int orderid) throws Exception {
		Connection con = databaseconnection.connect();
		Statement stmt = con.createStatement();
		String sql = "select order_date from orderdata where order_id=" + orderid;
		stmt.executeUpdate(sql);
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		String date = rs.getString("order_date");
		String local = date.substring(0, 10);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(local, formatter);
		int n = Period.between(date1, LocalDate.now()).getDays();

		return n;
	}

}
