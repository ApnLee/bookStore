package com.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;
import com.bookstore.entity.Evaluate;
import com.bookstore.entity.User;
import com.bookstore.service.BookService;
import com.bookstore.service.EvaluateService;
import com.bookstore.service.UserService;
import com.bookstore.utils.PageBean;
import com.bookstore.utils.commons.CommonUtil;


public class BookServlet extends MyServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();
	private EvaluateService evaluateService = new EvaluateService();
	private UserService userService = new UserService();
	
	public String load(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		Book book = bookService.load(bid);
		List<Evaluate> evaluates = evaluateService.findEvaluateByBid(bid);
		
		request.setAttribute("book", book);
		request.setAttribute("evaluates", evaluates);
		
		return "/jsps/book/desc.jsp";
	}
	
	private int getPc(HttpServletRequest request){
		int pc = 1;
		String param = request.getParameter("pc");
		if(param!=null && !param.trim().isEmpty()){
			try{
				pc = Integer.parseInt(param);
			}catch(RuntimeException e){ }
		}
		return pc;
	}
	
	private String getUrl(HttpServletRequest request){
		String url = request.getRequestURI()+"?"+request.getQueryString();
		int index = url.lastIndexOf("&pc=");
		if(index!=-1){
			url = url.substring(0, index);
		}
		return url;
	}
	
	public String findByCategory(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		int pc = getPc(request);
		String url = getUrl(request);
		String cid = request.getParameter("cid");
		PageBean<Book> pb = bookService.findByCategory(cid, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/jsps/book/list.jsp";
	}
	
	public String findByAuthor(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		int pc = getPc(request);
		String url = getUrl(request);
		String author = request.getParameter("author");
		PageBean<Book> pb = bookService.findByAuthor(author, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/jsps/book/list.jsp";
	}
	
	public String findByPress(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		int pc = getPc(request);
		String url = getUrl(request);
		String press = request.getParameter("press");
		PageBean<Book> pb = bookService.findByPress(press, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/jsps/book/list.jsp";
	}
	
	public String findByBname(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		int pc = getPc(request);
		String url = getUrl(request);
		String bname = request.getParameter("bname");
		PageBean<Book> pb = bookService.findByBname(bname, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/jsps/book/list.jsp";
	}
	
	public String findByConbination(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		int pc = getPc(request);
		String url = getUrl(request);
		
		Book criteria = CommonUtil.toBean(Book.class, request.getParameterMap());
		PageBean<Book> pb = bookService.findByConbination(criteria, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/jsps/book/list.jsp";
	}
	
}
