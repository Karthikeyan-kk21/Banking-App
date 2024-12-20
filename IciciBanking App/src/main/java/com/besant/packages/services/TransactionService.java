package com.besant.packages.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface TransactionService {
	public void addMoney(HttpServletRequest req, HttpServletResponse res);
	public void withdrawMoney(HttpServletRequest req, HttpServletResponse res);
	public void viewBalance(HttpServletRequest req, HttpServletResponse res);

}
