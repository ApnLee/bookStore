package com.tyust.cart.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tyust.book.bean.Book;
import com.tyust.cart.bean.CartItem;
import com.tyust.cart.service.CartItemService;
import com.tyust.commons.CommonUtil;
import com.tyust.servlet.MyServlet;
import com.tyust.user.bean.User;

public class CartItemServlet extends MyServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CartItemService cartItemService = new CartItemService();
	
	public String loadCartItems(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String cartItemIds = request.getParameter("cartItemIds");
		double total = Double.parseDouble(request.getParameter("total"));
		List<CartItem> cartItems = cartItemService.loatCartItems(cartItemIds);
		request.setAttribute("cartItems", cartItems);
		request.setAttribute("total", total);
		request.setAttribute("cartItemIds", cartItemIds);
		return "/jsps/cart/showitem.jsp";
	}
	
	public String updateQuantity(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String cartItemId = request.getParameter("cartItemId");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		CartItem cartItem = cartItemService.updateQuantity(cartItemId, quantity);
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"quantity\"").append(":").append(cartItem.getQuantity());
		sb.append(",");
		sb.append("\"subtotal\"").append(":").append(cartItem.getSubtotal());
		sb.append("}");
		response.getWriter().print(sb);
		return "";
	}
	
	public String batchDelete(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String cartItemIds = request.getParameter("cartItemIds");
		cartItemService.batchDelete(cartItemIds);
		return myCart(request,response);
	}
	
	public String add(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		Map<String,String[]> map = request.getParameterMap();
		CartItem cartItem = CommonUtil.toBean(CartItem.class,map);
		Book book = CommonUtil.toBean(Book.class, map);
		User user = (User) request.getSession().getAttribute("sessionUser");
		cartItem.setBook(book);
		cartItem.setUser(user);
		//调用service，完成添加
		cartItemService.add(cartItem);
		return myCart(request,response);
	}
	
	public String myCart(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sessionUser");
		String uid = user.getUid();
		List<CartItem> cartItemList = cartItemService.myCart(uid);
		request.setAttribute("cartItemList", cartItemList);
		return "/jsps/cart/list.jsp";
	}

}
