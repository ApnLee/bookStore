package com.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Order;
import com.bookstore.service.OrderService;
import com.bookstore.utils.PageBean;

public class AdminOrderServlet extends MyServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderService orderService = new OrderService();
	
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

	public String findAll(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		int pc = getPc(request);
		String url = getUrl(request);
		//从当前session中获取
		PageBean<Order> pb = orderService.findAll(pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		
		return "/adminjsps/admin/order/list.jsp";
	}
	
	public String findByStatus(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		int pc = getPc(request);
		String url = getUrl(request);
		int status = Integer.parseInt(request.getParameter("status"));
		//从当前session中获取
		PageBean<Order> pb = orderService.findByStatus(status,pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		
		return "/adminjsps/admin/order/list.jsp";
	}
	
	public String load(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		request.setAttribute("order", order);
		String btn = request.getParameter("btn");
		request.setAttribute("btn", btn);
		return "/adminjsps/admin/order/desc.jsp";
	}
	
	public String cancel(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		int status = orderService.findStatus(oid);
		if(status!=1){
			request.setAttribute("code", "error");
			request.setAttribute("msg", "状态不对，不能取消！");
			return "/adminjsps/msg.jsp";
		}
		orderService.updateStatus(oid, 6);//设置状态为取消
		request.setAttribute("code", "success");
		request.setAttribute("msg", "订单已取消成功！");
		return "/adminjsps/msg.jsp";
	}
	/**
	 * 发货
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deliver(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		int status = orderService.findStatus(oid);
		if(status!=2){
			request.setAttribute("code", "error");
			request.setAttribute("msg", "状态不对，不能发货！");
			return "/adminjsps/msg.jsp";
		}
		orderService.updateStatus(oid, 3);//设置状态为等待确认
		request.setAttribute("code", "success");
		request.setAttribute("msg", "订单已发货！");
		return "/adminjsps/msg.jsp";
	}

}
