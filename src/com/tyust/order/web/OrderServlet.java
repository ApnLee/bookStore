package com.tyust.order.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tyust.cart.bean.CartItem;
import com.tyust.cart.service.CartItemService;
import com.tyust.commons.CommonUtil;
import com.tyust.order.bean.Order;
import com.tyust.order.bean.OrderItem;
import com.tyust.order.service.OrderService;
import com.tyust.pager.PageBean;
import com.tyust.servlet.MyServlet;
import com.tyust.user.bean.User;

public class OrderServlet extends MyServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OrderService orderService = new OrderService();
	private CartItemService cartItemService = new CartItemService();
	
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
	
	public String paymentPre(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("order", orderService.load(request.getParameter("oid")));
		return "/jsps/order/pay.jsp";
	}
	
	public String confirm(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		int status = orderService.findStatus(oid);
		if(status!=3){
			request.setAttribute("code", "error");
			request.setAttribute("msg", "状态不对，不能确认收货！");
			return "/jsps/msg.jsp";
		}
		orderService.updateStatus(oid, 4);//设置状态为确认收货
		request.setAttribute("code", "success");
		request.setAttribute("msg", "恭喜，交易成功！");
		return "/jsps/msg.jsp";
	}
	
	public String cancel(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		int status = orderService.findStatus(oid);
		if(status!=1){
			request.setAttribute("code", "error");
			request.setAttribute("msg", "状态不对，不能取消！");
			return "/jsps/msg.jsp";
		}
		orderService.updateStatus(oid, 5);//设置状态为取消
		request.setAttribute("code", "success");
		request.setAttribute("msg", "您的订单已取消成功！");
		return "/jsps/msg.jsp";
	}
	
	public String load(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		request.setAttribute("order", order);
		String btn = request.getParameter("btn");
		request.setAttribute("btn", btn);
		return "/jsps/order/desc.jsp";
	}
	
	public String createOrder(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String cartItemIds = request.getParameter("cartItemIds");
		List<CartItem> cartItemList = cartItemService.loatCartItems(cartItemIds);
		//创建order
		Order order = new Order();
		order.setOid(CommonUtil.uuid());
		order.setOrdertime(String.format("%tF %<tT", new Date()));
		order.setStatus(1);
		order.setAddress(request.getParameter("address"));
		User owner = (User) request.getSession().getAttribute("sessionUser");
		order.setOwner(owner);
		BigDecimal total = new BigDecimal("0");
		for(CartItem cartItem : cartItemList){
			total = total.add(new BigDecimal(cartItem.getSubtotal()+""));
		}
		order.setTotal(total.doubleValue());
		//创建List<OrderItem>
		//一个CartItem对应一个OrderItem
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(CartItem cartItem : cartItemList){
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderItemId(CommonUtil.uuid());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setBook(cartItem.getBook());
			orderItem.setOrder(order);
			orderItemList.add(orderItem);
		}
		order.setOrderItemList(orderItemList);
		orderService.createOrder(order);
		//保存订单并转发
		request.setAttribute("order", order);
		//删除购物车里的生成订单的条目
		cartItemService.batchDelete(cartItemIds);
		return "/jsps/order/ordersucc.jsp";
	}
	
	public String myOrders(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		int pc = getPc(request);
		String url = getUrl(request);
		//从当前session中获取
		User user = (User) request.getSession().getAttribute("sessionUser");
		PageBean<Order> pb = orderService.myOrders(user.getUid(), pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		
		return "/jsps/order/list.jsp";
	}
	
	

}
