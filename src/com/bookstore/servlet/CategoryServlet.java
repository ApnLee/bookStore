package com.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Category;
import com.bookstore.service.CategoryService;


/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends MyServlet {
	private static final long serialVersionUID = 1L;
	private CategoryService service = new CategoryService();
	
	public String findAll(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> parents = service.findAll();
		request.setAttribute("parents", parents);
		return "/jsps/left.jsp";
	}
}
