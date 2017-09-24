package com.bookstore.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;
import com.bookstore.entity.Evaluate;
import com.bookstore.entity.User;
import com.bookstore.service.BookService;
import com.bookstore.service.EvaluateService;
import com.bookstore.service.OrderService;
import com.bookstore.service.UserService;
import com.bookstore.utils.commons.CommonUtil;

public class EvaluateServlet extends MyServlet {
	private static final long serialVersionUID = 1L;

	private EvaluateService evaluateService = new EvaluateService();
	private OrderService orderService = new OrderService();
	private BookService bookService = new BookService();
	
	public String evaluate(HttpServletRequest request,HttpServletResponse response){
		Evaluate evaluate = new Evaluate();
		evaluate.setEvaluateId(CommonUtil.uuid());
		User user = (User) request.getSession().getAttribute("sessionUser");
		String uid = user.getUid();
		String bid = request.getParameter("bid");
		String evaluateDesc = request.getParameter("evaluate");
		
		evaluate.setUid(uid);
		evaluate.setBid(bid);
		evaluate.setEvaluateDesc(evaluateDesc);
		evaluate.setEvaluateTime(String.format("%tF %<tT", new Date()));
		
		evaluateService.evaluate(evaluate);
		
		String oid = request.getParameter("oid");
		orderService.updateStatus(oid, 4);//设置状态为交易成功
		request.setAttribute("code", "success");
		request.setAttribute("msg", "评价成功！");
		return "/jsps/book/evaluateMsg.jsp";
	}

}
