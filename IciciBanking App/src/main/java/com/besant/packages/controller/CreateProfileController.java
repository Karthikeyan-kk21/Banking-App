package com.besant.packages.controller;

import com.besant.packages.service.impl.CreateProfileServiceImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createprofile")
public class CreateProfileController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		CreateProfileServiceImpl createProfileServiceImpl = new CreateProfileServiceImpl();
		createProfileServiceImpl.createProfile(req, res);
		
		
	}
}
