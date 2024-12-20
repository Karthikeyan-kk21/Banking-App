package com.besant.packages.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.besant.packages.services.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServiceImpl implements LoginService {

	@Override
	public void Loginup(HttpServletRequest req, HttpServletResponse res) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root",
					"W7301@jqir#");
			PreparedStatement statement = connection
					.prepareStatement("select * from BankingApp.authentication where email=? and password=?;");

			statement.setString(1, req.getParameter("email"));
			statement.setString(2, req.getParameter("password"));

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				HttpSession session = req.getSession();
				session.setAttribute("userid", result.getString("id"));

				res.sendRedirect("Dashboard.html");
				System.out.println("Logged in successfully");
			} else {
				System.err.println("email or password is incorrect");
			}

			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
