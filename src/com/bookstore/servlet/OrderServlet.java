package com.bookstore.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;
import com.bookstore.entity.CartItem;
import com.bookstore.entity.Logistics;
import com.bookstore.entity.Order;
import com.bookstore.entity.OrderItem;
import com.bookstore.entity.User;
import com.bookstore.service.BookService;
import com.bookstore.service.CartItemService;
import com.bookstore.service.OrderService;
import com.bookstore.utils.PageBean;
import com.bookstore.utils.PaymentUtil;
import com.bookstore.utils.commons.CommonUtil;

public class OrderServlet extends MyServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OrderService orderService = new OrderService();
	private CartItemService cartItemService = new CartItemService();
	private BookService bookService = new BookService();

	private int getPc(HttpServletRequest request) {
		int pc = 1;
		String param = request.getParameter("pc");
		if (param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch (RuntimeException e) {
			}
		}
		return pc;
	}

	private String getUrl(HttpServletRequest request) {
		String url = request.getRequestURI() + "?" + request.getQueryString();
		int index = url.lastIndexOf("&pc=");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}

	public String paymentPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("order", orderService.load(request.getParameter("oid")));
		return "/jsps/order/pay.jsp";
	}

	/**
	 * 支付方法
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String payment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("payment.properties"));
		/*
		 * 1. 准备13个参数
		 */
		String p0_Cmd = "Buy";// 业务类型，固定值Buy
		String p1_MerId = props.getProperty("p1_MerId");// 商号编码，在易宝的唯一标识
		String p2_Order = request.getParameter("oid");// 订单编码
		String p3_Amt = "0.01";// 支付金额 request.getParameter("total");
		String p4_Cur = "CNY";// 交易币种，固定值CNY
		String p5_Pid = "";// 商品名称
		String p6_Pcat = "";// 商品种类
		String p7_Pdesc = "";// 商品描述
		String p8_Url = props.getProperty("p8_Url");// 在支付成功后，易宝会访问这个地址。
		String p9_SAF = "";// 送货地址
		String pa_MP = "";// 扩展信息
		String pd_FrpId = request.getParameter("yh");// 支付通道
		String pr_NeedResponse = "1";// 应答机制，固定值1

		/*
		 * 2. 计算hmac 需要13个参数 需要keyValue 需要加密算法
		 */
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

		/*
		 * 3. 重定向到易宝的支付网关
		 */
		StringBuilder sb = new StringBuilder("https://www.yeepay.com/app-merchant-proxy/node");
		sb.append("?").append("p0_Cmd=").append(p0_Cmd);
		sb.append("&").append("p1_MerId=").append(p1_MerId);
		sb.append("&").append("p2_Order=").append(p2_Order);
		sb.append("&").append("p3_Amt=").append(p3_Amt);
		sb.append("&").append("p4_Cur=").append(p4_Cur);
		sb.append("&").append("p5_Pid=").append(p5_Pid);
		sb.append("&").append("p6_Pcat=").append(p6_Pcat);
		sb.append("&").append("p7_Pdesc=").append(p7_Pdesc);
		sb.append("&").append("p8_Url=").append(p8_Url);
		sb.append("&").append("p9_SAF=").append(p9_SAF);
		sb.append("&").append("pa_MP=").append(pa_MP);
		sb.append("&").append("pd_FrpId=").append(pd_FrpId);
		sb.append("&").append("pr_NeedResponse=").append(pr_NeedResponse);
		sb.append("&").append("hmac=").append(hmac);

		response.sendRedirect(sb.toString());
		return null;
	}

	/**
	 * 回馈方法 当支付成功时，易宝会访问这里 用两种方法访问： 1. 引导用户的浏览器重定向(如果用户关闭了浏览器，就不能访问这里了) 2.
	 * 易宝的服务器会使用点对点通讯的方法访问这个方法。（必须回馈success，不然易宝服务器会一直调用这个方法）
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String back(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 1. 获取12个参数
		 */
		String p1_MerId = req.getParameter("p1_MerId");
		String r0_Cmd = req.getParameter("r0_Cmd");
		String r1_Code = req.getParameter("r1_Code");
		String r2_TrxId = req.getParameter("r2_TrxId");
		String r3_Amt = req.getParameter("r3_Amt");
		String r4_Cur = req.getParameter("r4_Cur");
		String r5_Pid = req.getParameter("r5_Pid");
		String r6_Order = req.getParameter("r6_Order");
		String r7_Uid = req.getParameter("r7_Uid");
		String r8_MP = req.getParameter("r8_MP");
		String r9_BType = req.getParameter("r9_BType");
		String hmac = req.getParameter("hmac");
		/*
		 * 2. 获取keyValue
		 */
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("payment.properties"));
		String keyValue = props.getProperty("keyValue");
		/*
		 * 3. 调用PaymentUtil的校验方法来校验调用者的身份 >如果校验失败：保存错误信息，转发到msg.jsp >如果校验通过： *
		 * 判断访问的方法是重定向还是点对点，如果要是重定向 修改订单状态，保存成功信息，转发到msg.jsp *
		 * 如果是点对点：修改订单状态，返回success
		 */
		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid,
				r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
		if (!bool) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "无效的签名，支付失败！（你不是好人）");
			return "f:/jsps/order/msg.jsp";
		}
		if (r1_Code.equals("1")) {
			orderService.updateStatus(r6_Order, 2);
			if (r9_BType.equals("1")) {
				req.setAttribute("code", "success");
				req.setAttribute("msg", "恭喜，支付成功！");
				return "f:/jsps/order/msg.jsp";
			} else if (r9_BType.equals("2")) {
				resp.getWriter().print("success");
			}
		}
		return null;
	}

	public String confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		request.setAttribute("order", order);

		int status = orderService.findStatus(oid);
		if (status != 3) {
			request.setAttribute("code", "error");
			request.setAttribute("msg", "状态不对，不能确认收货！");
			return "/jsps/order/msg.jsp";
		}
		orderService.updateStatus(oid, 5);// 设置状态为请评价
		request.setAttribute("code", "success");
		request.setAttribute("msg", "恭喜，收货成功！");
		return "/jsps/receiveMsg.jsp";
	}

	public String cancel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		int status = orderService.findStatus(oid);
		if (status != 1) {
			request.setAttribute("code", "error");
			request.setAttribute("msg", "状态不对，不能取消！");
			return "/jsps/order/msg.jsp";
		}
		orderService.updateStatus(oid, 6);// 设置状态为取消
		request.setAttribute("code", "success");
		request.setAttribute("msg", "您的订单已取消成功！");
		return "/jsps/order/msg.jsp";
	}

	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		request.setAttribute("order", order);
		String btn = request.getParameter("btn");
		request.setAttribute("btn", btn);
		return "/jsps/order/desc.jsp";
	}

	public String createOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cartItemIds = request.getParameter("cartItemIds");
		List<CartItem> cartItemList = cartItemService.loatCartItems(cartItemIds);
		// 创建order
		Order order = new Order();
		order.setOid(CommonUtil.uuid());
		order.setOrdertime(String.format("%tF %<tT", new Date()));
		order.setStatus(1);
		order.setConsignee(request.getParameter("consignee"));
		order.setPhone(request.getParameter("phone"));
		order.setAddress(request.getParameter("address"));
		User owner = (User) request.getSession().getAttribute("sessionUser");
		order.setOwner(owner);
		BigDecimal total = new BigDecimal("0");
		for (CartItem cartItem : cartItemList) {
			total = total.add(new BigDecimal(cartItem.getSubtotal() + ""));
		}
		order.setTotal(total.doubleValue());
		// 创建List<OrderItem>
		// 一个CartItem对应一个OrderItem
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for (CartItem cartItem : cartItemList) {
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
		// 保存订单并转发
		request.setAttribute("order", order);
		// 删除购物车里的生成订单的条目
		cartItemService.batchDelete(cartItemIds);
		return "/jsps/order/ordersucc.jsp";
	}

	public String myOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pc = getPc(request);
		String url = getUrl(request);
		// 从当前session中获取
		User user = (User) request.getSession().getAttribute("sessionUser");
		PageBean<Order> pb = orderService.myOrders(user.getUid(), pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);

		return "/jsps/order/list.jsp";
	}

	public String evaluateOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		request.setAttribute("order", order);
		String btn = request.getParameter("btn");
		request.setAttribute("btn", btn);
		return "/jsps/order/evaluate.jsp";
	}

	public String evaluateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		request.setAttribute("order", order);
		String bid = request.getParameter("bid");
		Book book = bookService.load(bid);
		request.setAttribute("book", book);
		return "/jsps/book/evaluateBook.jsp";
	}
	
	public String logisticsTracking(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("shipperCode", "YD");
		request.setAttribute("logisticCode", "3967962492154");
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		request.setAttribute("order", order);

		List<Logistics> list = orderService.logisticsJsonToList();
		Logistics logisticsNew = list.get(0);
		request.setAttribute("logisticsNew", logisticsNew);
		
		List<Logistics> logisticsList = list.subList(1, list.size());
		request.setAttribute("logisticsList", logisticsList);

		return "/jsps/order/logistics.jsp";
	}

}
