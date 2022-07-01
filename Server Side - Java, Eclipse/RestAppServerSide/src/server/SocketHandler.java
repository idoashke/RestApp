package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.HashMap;

import java.net.Socket;

public class SocketHandler extends Thread {
	
	Socket incoming;
	Sql sql;

	SocketHandler(Socket _in, Sql sql) {
		incoming = _in;
		this.sql = sql;

	}

	public void run() {
		System.out.println("I'm in run function");
		String clientSentence;
		String capitalizedSentence = null;
		int sum = 0;
		double result = 0;

		try {

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(incoming.getInputStream()));

			DataOutputStream outToClient = new DataOutputStream(incoming.getOutputStream());

			String str;
			str = inFromClient.readLine();
			String[] parts = str.split(",");

			switch (parts[0]) {
			case "insertUser":
				// insetUser,email,firstName,lastNmae,date,city,address,phoneNum,password
				// "insertUser,ido,ashkenazi,04091994,Holon,hheistadrot88,054111111,1234aa"
				sql.insert_statement_users(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7],
						parts[8]);
				System.out.println("User has been added");
				outToClient.writeBytes("success");
				break;

			case "insertAdmin":
				// insertAdmin,email,firstName,lastName,password
				sql.insert_statement_admin(parts[1], parts[2], parts[3], parts[4]);
				System.out.println("Admin has been added");
				outToClient.writeBytes("success");
				break;

			case "insertDish":
				// insertDish,id,name,category,description,price,image
				sql.insert_statement_dishes(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
				System.out.println("Dish has been added");
				outToClient.writeBytes("success");
				break;

			case "insertBusiness":
				// insertBusiness,name,city,address
				sql.insert_statement_business(parts[1], parts[2], parts[3]);
				System.out.println("Business has been added");
				outToClient.writeBytes("success");
				break;

			case "deleteUser":
				// deleteUser,email
				sql.delete_statement_user(parts[1]);
				System.out.println("User has been deleted");
				outToClient.writeBytes("success");
				break;
				
			case "deleteAdmin":
				// deleteUser,email
				sql.delete_statement_admin(parts[1]);
				System.out.println("User has been deleted");
				outToClient.writeBytes("success");
				break;

			case "deleteDish":
				// deleteDish,id
				sql.delete_statement_dish(parts[1]);
				System.out.println("User has been deleted");
				outToClient.writeBytes("success");
				break;

			case "updateUser":
				// updateUser,oldemail,email,fisrtname,lastname,date,city,address,phonenum,password
				sql.update_statement_user(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7],
						parts[8], parts[9]);
				System.out.println("User has been Updated");
				outToClient.writeBytes("success");
				break;

			case "updateDish":
				// updateDish,oldid,id,name,cate,des,price,image
				sql.update_statement_dish(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
				System.out.println("User has been Updated");
				outToClient.writeBytes("success");
				break;

			case "updateBusiness":
				// updateBusiness,oladName,name,city,address
				sql.update_statement_business(parts[1], parts[2], parts[3], parts[4]);
				System.out.println("Business has been updated");
				outToClient.writeBytes("success");
				break;

			case "updateAdmin":
				// updateAdmin,oldemail,email,firstname,lastname,password
				sql.update_statement_admin(parts[1], parts[2], parts[3], parts[4], parts[5]);
				System.out.println("Admin has been updated");
				outToClient.writeBytes("success");
				break;

			case "pullDish":
				// pullDish
				String pulldish = new String();
				pulldish = sql.pull_data_dish();
				System.out.println("success");
				outToClient.writeBytes(pulldish);
				break;

			case "pullUser":
				// pullUser
				String pulluser = new String();
				pulluser = sql.pull_data_user(parts[1]);
				System.out.println("success");
				outToClient.writeBytes(pulluser);
//				int status = Authentication.CheckAut(pulluser);
//				if (status == 1) {
//					outToClient.writeBytes(pulluser);
//				} else {
//					outToClient.writeBytes("failed"); // Authentication Failed
//				}
				break;

			case "pullAdmin":
				// pullAdmin
				String pulladmin = new String();
				pulladmin = sql.pull_data_admin(parts[1]);
				System.out.println("success");
				outToClient.writeBytes(pulladmin);
				break;

			case "pullBusiness":
				// pullBusiness
				String pullbusiness = new String();
				pullbusiness = sql.pull_data_business();
				System.out.println("success");
				outToClient.writeBytes(pullbusiness);
				break;

			}

		} catch (IOException e) {
			System.out.println("success");
			e.getMessage();
		}
		try {
			incoming.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	sql.insert_statement_dishes("20", "chiken", "main meal","firze chiken","25","c/image/frizechiken");
//	System.out.println("new dish has been added");
//	sql.insert_statement_users("bb@gmail.com","yossi", "cohen","19972020", "rosh hhayin","albert einstin", "0548034561", "asdvxcv06");
//	System.out.println("new user has been added");
//	sql.delete_statement_dish("2");
//	System.out.println("dish has been deleted");

}
