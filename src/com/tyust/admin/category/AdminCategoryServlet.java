package com.tyust.admin.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tyust.book.service.BookService;
import com.tyust.category.bean.Category;
import com.tyust.category.service.CategoryService;
import com.tyust.commons.CommonUtil;
import com.tyust.servlet.MyServlet;

public class AdminCategoryServlet extends MyServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryService categoryService = new CategoryService();
	private BookService bookService = new BookService();
	

	public String findAll(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		request.setAttribute("parents", categoryService.findAll());
		return "/adminjsps/admin/category/list.jsp";
	}
	
	public String addParent(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException{
		Category parent = CommonUtil.toBean(Category.class, request.getParameterMap());
		parent.setCid(CommonUtil.uuid());
		categoryService.add(parent);
		return findAll(request,response);
	}
	
	/**
	 * 添加二级分类第一步
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addChildPre(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		String pid = request.getParameter("pid");//获取父分类的id
		List<Category> parents = categoryService.findParents();
		request.setAttribute("pid", pid);
		request.setAttribute("parents", parents);
		return "/adminjsps/admin/category/add2.jsp";
	}
	
	public String addChild(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		Category child = CommonUtil.toBean(Category.class, request.getParameterMap());
		child.setCid(CommonUtil.uuid());
		String pid = request.getParameter("pid");
		Category parent = new Category();
		parent.setCid(pid);
		child.setParent(parent);
		
		categoryService.add(child);
		return findAll(request, response);
	}
	
	public String editParentPre(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		String cid = request.getParameter("cid");
		Category parent = categoryService.load(cid);
		request.setAttribute("parent", parent);
		return "/adminjsps/admin/category/edit.jsp";
	}
	
	public String editParent(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		Category category = CommonUtil.toBean(Category.class, request.getParameterMap());
		categoryService.edit(category);
		return findAll(request, response);
	}
	
	public String editChildPre(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		String cid = request.getParameter("cid");
		Category child = categoryService.load(cid);
		request.setAttribute("child", child);
		request.setAttribute("parents", categoryService.findParents());
		return "/adminjsps/admin/category/edit2.jsp";
	}
	
	public String editChild(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		Category category = CommonUtil.toBean(Category.class, request.getParameterMap());
		String pid = request.getParameter("pid");
		Category parent = new Category();
		parent.setCid(pid);
		category.setParent(parent);
		categoryService.edit(category);
		return findAll(request, response);
	}
	
	public String deleteParent(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		//获取一级分类的cid
		String cid = request.getParameter("cid");
		int number = categoryService.findChildrenByParent(cid);
		if(number > 0){
			request.setAttribute("msg", "该分类下还有子分类，不能删除！");
			return "/adminjsps/msg.jsp";
		}else{
			categoryService.delete(cid);
			return findAll(request, response);
		}
	}
	/**
	 * 删除二级分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteChild(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		String cid = request.getParameter("cid");
		int number = bookService.findBookCountByCategory(cid);
		if(number > 0){
			request.setAttribute("msg", "该分类下还存在图书，不能删除！");
			return "/adminjsps/msg.jsp";
		}else{
			categoryService.delete(cid);
			return findAll(request, response);
		}
	}

}
