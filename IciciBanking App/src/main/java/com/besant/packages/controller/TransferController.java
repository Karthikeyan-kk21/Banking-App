package com.besant.packages.controller;

import com.besant.packages.service.impl.TransferServiceImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/transfer")
public class TransferController extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		TransferServiceImpl service = new TransferServiceImpl();
		service.transfer(req, res);
		
		
	}

}
