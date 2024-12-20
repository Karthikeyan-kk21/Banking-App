package com.besant.packages.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.besant.packages.services.TransactionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TransactionServiceImpl implements TransactionService{

	@Override
	public void addMoney(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root",
					"W7301@jqir#");
			PreparedStatement statement = connection.prepareStatement(
					"select balance from BankingApp.profile where userid=?;");

			HttpSession session = req.getSession();
			if(session.getAttribute("userid")==null) {
				res.sendRedirect("Dashboard.html");
			}
			else {
				statement.setString(1, (String) session.getAttribute("userid"));
				
				ResultSet result= statement.executeQuery();

				if (result.next()) {
					PreparedStatement statement2 = connection.prepareStatement(
							"update bankingapp.profile set balance = ? where userid =?");
					int newBalance = Integer.parseInt(result.getString("balance"))
							+ Integer.parseInt(req.getParameter("depositMoney"));
					statement2.setInt(1, newBalance);
					statement2.setString(2, (String) session.getAttribute("userid"));
					int result2 = statement2.executeUpdate();
					if (result2 > 0) {
						res.sendRedirect("ViewBalance.jsp");
						System.out.println("Money added successfully ");
					} else {
						System.err.println("Something went wrong.... try again later");
					}
				}
			}
			
 
			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void withdrawMoney(HttpServletRequest req, HttpServletResponse res) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root",
					"W7301@jqir#");
			PreparedStatement statement = connection.prepareStatement(
					"select balance from BankingApp.profile where userid=?;");

			HttpSession session = req.getSession();
			if(session.getAttribute("userid")==null) {
				res.sendRedirect("Dashboard.html");
			}
			else {
				statement.setString(1, (String) session.getAttribute("userid"));
				
				ResultSet result= statement.executeQuery();
				

				if (result.next()) {
					PreparedStatement statement2 = connection.prepareStatement(
							"update bankingapp.profile set balance = ? where userid =?");
					
					int balance = Integer.parseInt(result.getString("balance"));
					int withdrawnmoney=Integer.parseInt(req.getParameter("withdrawMoney"));
					
					if(balance<0) {
						System.out.println("Insufficient Balance");
						res.sendRedirect("WithdrawMoney.html");
					}
					else if(balance<withdrawnmoney) {
						System.out.println("Withdrawing money is higher than the balance... Try again...");
						res.sendRedirect("WithdrawMoney.html");
						
					}
					else {
						int newBalance = Integer.parseInt(result.getString("balance"))
								- Integer.parseInt(req.getParameter("withdrawMoney"));
						statement2.setInt(1, newBalance);
						statement2.setString(2, (String) session.getAttribute("userid"));
						int result2 = statement2.executeUpdate();
						if (result2 > 0) {
							res.sendRedirect("ViewBalance.jsp");
							System.out.println("Money Withdrawn successfully");
						} else {
							System.err.println("Something went wrong.... try again ");
						}
						
					}
			}
			
			
				
			}
 
			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void viewBalance(HttpServletRequest req, HttpServletResponse res) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root",
					"W7301@jqir#");
			PreparedStatement statement = connection.prepareStatement(
					"select balance from BankingApp.profile where userid=?;");

			HttpSession session = req.getSession();
			if(session.getAttribute("userid")==null) {
				res.sendRedirect("Dashboard.html");
			}
			else {
				statement.setString(1, (String) session.getAttribute("userid"));
				
				ResultSet result= statement.executeQuery();
				

				if (result.next()) {
					
					int newBalance = Integer.parseInt(result.getString("balance"));
					System.out.println("Your account balance is: "+ newBalance);
					res.sendRedirect("Dashboard.html");
					
				}
			}
			
			
 
			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
