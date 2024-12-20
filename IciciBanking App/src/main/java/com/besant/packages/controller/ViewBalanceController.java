package com.besant.packages.controller;

import com.besant.packages.service.impl.TransactionServiceImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewBalance")
public class ViewBalanceController extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		TransactionServiceImpl service=new TransactionServiceImpl();
		service.viewBalance(req, res);
		
	}

}
