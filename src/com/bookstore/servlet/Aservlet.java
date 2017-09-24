package com.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Aservlet
 */
public class Aservlet extends MyServlet {
	private static final long serialVersionUID = 1L;
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String name = request.getParameter("username");
		System.out.println(name);
		return "r:/index.jsp";
		
	}
	public String update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		return "index.jsp";
	}
}
