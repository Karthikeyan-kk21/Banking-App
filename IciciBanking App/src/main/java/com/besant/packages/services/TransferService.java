package com.besant.packages.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface TransferService {
	public void panNumber(HttpServletRequest req,HttpServletResponse res);
	public void transfer(HttpServletRequest req, HttpServletResponse res);

}
