package com.besant.packages.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.besant.packages.services.TransferService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TransferServiceImpl implements TransferService {

	@Override
	public void panNumber(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root",
					"W7301@jqir#");
			PreparedStatement statement = connection.prepareStatement(
					"select * from BankingApp.profile where pan=?;");

			statement.setString(1, req.getParameter("panNumber"));
			
			
			ResultSet result= statement.executeQuery();
			
			
			if (result.next()) {
				HttpSession session1 = req.getSession();
				
				session1.setAttribute("receiverPan", req.getParameter("panNumber"));
				
				res.sendRedirect("Transfer.html");
				System.out.println("Receiver Pan is valid");
			} else {
				res.sendRedirect("PanNumber.html");
				System.err.println("Receiver Pan is invalid");
				
			}

			
			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public void transfer(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("Transfer works");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root",
					"W7301@jqir#");
			PreparedStatement statement = connection.prepareStatement(
					"select balance from BankingApp.profile where userid=?;");

			HttpSession session = req.getSession();
			statement.setString(1, (String) session.getAttribute("userid"));
				
				ResultSet result= statement.executeQuery();
				

				if (result.next()) {
					PreparedStatement statement2 = connection.prepareStatement(
							"update bankingapp.profile set balance = ? where userid =?");
					
					int balance = Integer.parseInt(result.getString("balance"));
					int transferamount=Integer.parseInt(req.getParameter("transferMoney"));
					
					if(balance<0) {
						System.out.println("Insufficient Balance");
						res.sendRedirect("Transfer.html");
					}
					else if(balance<transferamount) {
						System.out.println("Transfering money is higher than the balance... Try again...");
						res.sendRedirect("Transfer.html");
						
					}
					else {
						int newBalance = Integer.parseInt(result.getString("balance"))
								- Integer.parseInt(req.getParameter("transferMoney"));
						statement2.setInt(1, newBalance);
						statement2.setString(2, (String) session.getAttribute("userid"));
						statement2.executeUpdate();
						
						
						PreparedStatement statement3 = connection.prepareStatement(
								"update bankingapp.profile set balance = balance + ? where pan = ?");
						HttpSession session1 = req.getSession();
						
						int newBalance2 = Integer.parseInt(req.getParameter("transferMoney"));
						statement3.setInt(1, newBalance2);
						
						statement3.setString(2, (String) session1.getAttribute("receiverPan"));
						
						
						int result2 = statement3.executeUpdate();
						if (result2 > 0) {
							res.sendRedirect("ViewBalance.jsp");
							System.out.println("Money Transfered successfully");
						} else {
							System.err.println("Something went wrong.... try again ");
						}
						
					
			}
			
			
				
			}
 
			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
