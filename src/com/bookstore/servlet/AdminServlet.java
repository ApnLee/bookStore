package com.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Admin;
import com.bookstore.service.AdminService;

public class AdminServlet extends MyServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService = new AdminService();
	
	public String quit(HttpServletRequest request,
			   HttpServletResponse response)
            throws ServletException,IOException{
		request.getSession().invalidate();
		return "r:/adminjsps/login.jsp";
	}
	
	public String login(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException,IOException{
		String adminname = request.getParameter("adminname");
		String adminpwd = request.getParameter("adminpwd");
		if(adminname==null || adminname.trim().isEmpty()){
			request.setAttribute("msg", "管理员账户不能为空！");
			return "/adminjsps/login.jsp";
		}else if(adminpwd==null || adminpwd.trim().isEmpty()){
			request.setAttribute("msg", "密码不能为空！");
			request.setAttribute("adminname", adminname);
			return "/adminjsps/login.jsp";
		}
		Admin admin = adminService.findByAdminnameAndAdminpwd(adminname, adminpwd);
		if(admin==null){
			request.setAttribute("msg", "管理员账户或密码错误");
			request.setAttribute("admin", admin);
			return "/adminjsps/login.jsp";
		}else{
			request.getSession().setAttribute("sessionAdmin", admin);
			return "/adminjsps/admin/index.jsp";
		}
	}

}
