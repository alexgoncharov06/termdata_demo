package com.github.alexwolfgoncharov.termdata.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




//@WebServlet(name = "main", urlPatterns = { "/" })
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = -9182603768298319465L;
	private static final Logger log = Logger.getLogger(Servlet.class.getName());

	@Override
	public void init() throws ServletException {
		log.info("init method Servlet");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		doGet(request, response);
		
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 
	        req.getRequestDispatcher("index.jsp").forward(req, resp);

	}
}