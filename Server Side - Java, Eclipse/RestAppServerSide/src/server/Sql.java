package server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

public class Sql {

	private  Connection connect; 
	
	public  void delete_statement_dish(String id){
		
		String sqldelete = "delete from dish where id = ? ";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqldelete);
			
			pst.setString(1, id);
			pst.execute();
			System.out.println("dish id "+id+ "has been deledted");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	
	public String pull_data_dish()
	{
		String sqlpull="SELECT * from dish";
		String answer=new String();
		try {
			java.sql.Statement pst = connect.createStatement();
			ResultSet rm=pst.executeQuery(sqlpull);
			while(rm.next())
			{
			System.out.println(rm.getString(1)+"~"+rm.getString(2)+"~"+rm.getString(3)+"~"+rm.getString(4)+"~"+rm.getString(5)+"~"+rm.getString(6)+";");
			answer=answer+rm.getString(1)+"~"+rm.getString(2)+"~"+rm.getString(3)+"~"+rm.getString(4)+"~"+rm.getString(5)+"~"+rm.getString(6)+";";
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return answer;
		
	}
	public String pull_data_user(String email)
	{
//		email="'aa@gmail.com'";
		String sqlpull="SELECT * FROM user where email = '" + email + "'";
		String answer=new String();
		try {
			PreparedStatement pst = connect.prepareStatement(sqlpull);			
			ResultSet rm=pst.executeQuery(sqlpull);
			while(rm.next())
			{
			System.out.println(rm.getString(1)+"~"+rm.getString(2)+"~"+rm.getString(3)+"~"+rm.getString(4)+"~"+rm.getString(5)+"~"+rm.getString(6)+"~"+rm.getString(7)+"~"+rm.getString(8));
			answer=answer+rm.getString(1)+"~"+rm.getString(2)+"~"+rm.getString(3)+"~"+rm.getString(4)+"~"+rm.getString(5)+"~"+rm.getString(6)+"~"+rm.getString(7)+"~"+rm.getString(8);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return answer;
	}
	
	
	public String pull_data_business()
	{
		String sqlpull="SELECT * from business";
		String answer=new String();
		try {
			java.sql.Statement pst = connect.createStatement();
			ResultSet rm=pst.executeQuery(sqlpull);
			while(rm.next())
			{
			System.out.println(rm.getString(1)+"~"+rm.getString(2)+"~"+rm.getString(3));
			answer=answer+rm.getString(1)+"~"+rm.getString(2)+"~"+rm.getString(3);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return answer;
		
	}
	
	public String pull_data_admin(String email)
	{
		String sqlpull="SELECT * from admin where email = '" + email + "'";
		String answer=new String();
		try {
			java.sql.Statement pst = connect.createStatement();
			ResultSet rm=pst.executeQuery(sqlpull);
			while(rm.next())
			{
			System.out.println(rm.getString(1)+"~"+rm.getString(2)+"~"+rm.getString(3)+"~"+rm.getString(4));
			answer=answer+rm.getString(1)+"~"+rm.getString(2)+"~"+rm.getString(3)+"~"+rm.getString(4);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return answer;
	}
	
	

	
	public  void delete_statement_user(String email){
		
		String sqldelete = "delete from user where email = ? ";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqldelete);
			
			pst.setString(1, email);
			pst.execute();
			System.out.println("user email "+email+ "has been deledted");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	public  void delete_statement_admin(String email){
		
		String sqldelete = "delete from admin where email = ? ";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqldelete);
			
			pst.setString(1, email);
			pst.execute();
			System.out.println("admin email "+email+ "has been deledted");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public  void update_statement_user(String oldEmail,String email,String firstName,String lastName,String date,String city,String address,String phoneNum,String password)
	{
	String sqlupdate = "UPDATE user SET email=?,firstName=?,lastName=?,dateOfBirth=?,city=?,address=?,phone=?,password=?  WHERE email =? ";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqlupdate);
			
			pst.setString(1, email);
			pst.setString(2, firstName);
			pst.setString(3, lastName);
			pst.setString(4, date);
			pst.setString(5, city);
			pst.setString(6, address);
			pst.setString(7, phoneNum);
			pst.setString(8, password);
			pst.setString(9, oldEmail);

			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public  void update_statement_dish(String oldID,String id,String name,String cate,String des,String price,String image)
	{
	String sqlupdate = "UPDATE dish SET id=?,name=?,category=?,description=?,price=?,image=?  WHERE id =? ";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqlupdate);
			
			pst.setString(1, id);
			pst.setString(2, name);
			pst.setString(3, cate);
			pst.setString(4, des);
			pst.setString(5, price);
			pst.setString(6, image);
			pst.setString(7, oldID);
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  void update_statement_business(String oldName,String name,String city,String address)
	{
	String sqlupdate = "UPDATE business SET name=?,city=?,address=? WHERE name =? ";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqlupdate);
			
			pst.setString(1, name);
			pst.setString(2, city);
			pst.setString(3, address);
			pst.setString(4, oldName);
	
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public  void update_statement_admin(String oldEmail,String email,String firstName,String lastName,String password)
	{
	String sqlupdate = "UPDATE admin SET email=?,firstName=?,lastName=?,password=? WHERE email =? ";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqlupdate);
			
			pst.setString(1, email);
			pst.setString(2, firstName);
			pst.setString(3, lastName);
			pst.setString(4, password);
			pst.setString(5, oldEmail);
	
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	//insert business to database
	public  void insert_statement_business( String name, String city, String address){
		
		String sqlInsert = "insert into business (name, city, address) values (?,?,?)";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqlInsert);
			pst.setString(1, name);
			pst.setString(2, city);
			pst.setString(3, address);
	
			pst.execute();	
			System.out.println("business has been added");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//insert  dishes to database
	public  void insert_statement_dishes(String id, String name, String cate, String des, String price, String image){
		
		String sqlInsert = "INSERT INTO dish (id, name, category, description, price, image) values (?,?,?,?,?,?)";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqlInsert);
			pst.setString(1, id);
			pst.setString(2, name);
			pst.setString(3, cate);
			pst.setString(4, des);
			pst.setString(5, price);
			pst.setString(6, image);
					
			pst.execute();	
			System.out.println("dish has been added");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//insert user to dishes
	public  void insert_statement_admin(String email, String firstName, String lastName, String password){
		
		String sqlInsert = "insert into admin (email, firstName, lastName, password) values (?,?,?,?)";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqlInsert);
			pst.setString(1, email);
			pst.setString(2, firstName);
			pst.setString(3, lastName);
			pst.setString(4, password);
			
			pst.execute();	
			System.out.println("admin has been added");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//insert user to database
	public  void insert_statement_users(String email, String firstName, String lastName, String date, String city, String address, String phoneNum, String password  ){
		
		String sqlInsert = "insert into user (email, firstName, lastName, dateOfBirth, city, address, phone, password) values (?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqlInsert);
			pst.setString(1, email);
			pst.setString(2, firstName);
			pst.setString(3, lastName);
			pst.setString(4, date);
			pst.setString(5, city);
			pst.setString(6, address);
			pst.setString(7, phoneNum);
			pst.setString(8, password);
					
			pst.execute();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	

	
	public  void connection()
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Works");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  void ConectingToSQL()
	{
		
		connection();
		String host = "jdbc:mysql://localhost:3306/rest_schema";
		String username = "root";
		String password = "159652";
		
		
		try {
			 connect = (Connection) DriverManager.getConnection(host, username, password);
		System.out.println("work");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	
	

}
