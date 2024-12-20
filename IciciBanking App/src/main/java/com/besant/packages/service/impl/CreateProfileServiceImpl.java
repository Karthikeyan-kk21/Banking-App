package com.besant.packages.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.besant.packages.services.CreateProfileService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CreateProfileServiceImpl implements CreateProfileService{

	@Override
	public void createProfile(HttpServletRequest req, HttpServletResponse res) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root",
					"W7301@jqir#");
			PreparedStatement statement = connection
					.prepareStatement("insert into BankingApp.profile (name, balance, pan, address, userid) values (?,0,?,?,?);");
			
			
			statement.setString(1, req.getParameter("name"));
			statement.setString(2, req.getParameter("pan"));
			statement.setString(3, req.getParameter("address"));
			
			HttpSession session = req.getSession();
			if(session.getAttribute("userid")==null) {
				res.sendRedirect("Dashboard.html");
				System.out.println("created profile successfully...");
			}
			else {
				statement.setString(4, (String) session.getAttribute("userid"));
				

				int result = statement.executeUpdate();
				

				if (result>0) {
					res.sendRedirect("Dashboard.html");
					System.out.println("Profile created successfully");
				} else {
					System.err.println("email or password is incorrect");
				}

			}
			
			
			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	

}
