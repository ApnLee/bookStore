package com.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8547590661238780899L;

	@Override
	protected void service(HttpServletRequest request,
						   HttpServletResponse response)
					       throws ServletException, IOException {
		
		/*if(request.getMethod().equalsIgnoreCase("get")){
			request = new GetRequest(request);
		}else{
			request.setCharacterEncoding("UTF-8");
		}
		request.setCharacterEncoding("UTF-8");*/
		response.setContentType("text/html;charset=UTF-8");
		String methodName = request.getParameter("method");
		Method method = null;
		try{
			method = this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
		}catch(Exception e){
			throw new RuntimeException("您调用的方法"+methodName+"不存在！",e);
		}
		try{
			String result = (String)method.invoke(this, request,response);
			if(result!=null && !result.trim().isEmpty()){
				int index = result.indexOf(":");
				if(index==-1){
					request.getRequestDispatcher(result).forward(request, response);
				}else{
					String[] str = result.split(":");
					if("r".equals(str[0])){
						response.sendRedirect(request.getContextPath()+str[1]);
					}else if("f".equals(str[0])){
						request.getRequestDispatcher(str[1]).forward(request, response);
					}
				}
			}
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
