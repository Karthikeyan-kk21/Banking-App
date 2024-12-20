package com.besant.packages.controller;

import com.besant.packages.service.impl.SignupServiceImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Signup")
public class SignupController extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		SignupServiceImpl signupServiceImpl = new SignupServiceImpl();
		signupServiceImpl.signUp(req, res);
	}
	

}
