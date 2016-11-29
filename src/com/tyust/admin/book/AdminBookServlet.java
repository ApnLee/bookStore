package com.tyust.admin.book;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tyust.book.bean.Book;
import com.tyust.book.service.BookService;
import com.tyust.category.bean.Category;
import com.tyust.category.service.CategoryService;
import com.tyust.commons.CommonUtil;
import com.tyust.pager.PageBean;
import com.tyust.servlet.MyServlet;

public class AdminBookServlet extends MyServlet {
	private static final long serialVersionUID = 1L;

	private CategoryService service = new CategoryService();
	private BookService bookService = new BookService();
	
	public String delete(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		Book book = bookService.load(bid);
		String savepath = this.getServletContext().getRealPath("/");
		new File(savepath,book.getImage_w()).delete();
		new File(savepath,book.getImage_b()).delete();
		bookService.delete(bid);
		request.setAttribute("msg", "删除成功！");
		return "/adminjsps/msg.jsp";
	}
	
	public String edit(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
		Book book = CommonUtil.toBean(Book.class, map);
		Category category = CommonUtil.toBean(Category.class, map);
		book.setCategory(category);
		bookService.edit(book);
		request.setAttribute("msg", "修改图书成功！");
		return "/adminjsps/msg.jsp";
	}
	
	public String findCategoryAll(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> parents = service.findAll();
		request.setAttribute("parents", parents);
		return "/adminjsps/admin/book/left.jsp";
	}
	
	
	public String load(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		Book book = bookService.load(bid);
		request.setAttribute("book", book);
		
		request.setAttribute("parents", service.findParents());
		String pid = book.getCategory().getParent().getCid();
		request.setAttribute("children", service.findByParent(pid));
		return "/adminjsps/admin/book/desc.jsp";
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
		return "/adminjsps/admin/book/list.jsp";
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
		return "/adminjsps/admin/book/list.jsp";
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
		return "/adminjsps/admin/book/list.jsp";
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
		return "/adminjsps/admin/book/list.jsp";
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
		return "/adminjsps/admin/book/list.jsp";
	}
	
	public String addPre(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> parents = service.findParents();
		request.setAttribute("parents", parents);
		return "/adminjsps/admin/book/add.jsp";
	}
	
	public String ajaxFindChildren(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		List<Category> children = service.findByParent(pid);
		String json = toJson(children);
		response.getWriter().print(json);
		return null;
	}
	
	private String toJson(Category category){
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"cid\"").append(":").append("\"").append(category.getCid()).append("\"");
		sb.append(",");
		sb.append("\"cname\"").append(":").append("\"").append(category.getCname()).append("\"");
		sb.append("}");
		return sb.toString();
	}
	
	private String toJson(List<Category> categoryList){
		StringBuilder sb = new StringBuilder("[");
		for(int i=0; i<categoryList.size(); i++){
			sb.append(toJson(categoryList.get(i)));
			if(i<categoryList.size()-1){
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}

}
