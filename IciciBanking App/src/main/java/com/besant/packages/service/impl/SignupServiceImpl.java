package com.besant.packages.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.concurrent.ThreadLocalRandom;

import com.besant.packages.services.SignupService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupServiceImpl implements SignupService {

	@Override
	public void signUp(HttpServletRequest req, HttpServletResponse res) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root",
					"W7301@jqir#");
			PreparedStatement statement = connection.prepareStatement(
					"insert into BankingApp.authentication (email,password,accountNumber) values (?,?,?);");

			int randomInt = ThreadLocalRandom.current().nextInt(10000, 19999);
			statement.setString(1, req.getParameter("email"));
			statement.setString(2, req.getParameter("password"));
			statement.setLong(3, randomInt);

			int response = statement.executeUpdate();

			if (response > 0) {
				res.sendRedirect("Login.html");
				System.out.println("Signup successfully");
			} else {
				System.out.println("Something went wrong");
			}

			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
