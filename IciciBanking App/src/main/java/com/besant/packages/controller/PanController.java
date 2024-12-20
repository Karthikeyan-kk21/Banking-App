package com.besant.packages.controller;

import com.besant.packages.service.impl.TransferServiceImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/panNumber")
public class PanController extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		TransferServiceImpl service=new TransferServiceImpl();
		service.panNumber(req, res);
	}

}
