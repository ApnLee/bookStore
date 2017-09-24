package com.bookstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {
	private String charset = "UTF-8";

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		if(request.getMethod().equalsIgnoreCase("get")){
			if(!(request instanceof GetRequest)){
				request = new GetRequest(request,charset);
			}
		}else{
			request.setCharacterEncoding(charset);
		}
		chain.doFilter(request, res);
	}

	@SuppressWarnings("null")
	@Override
	public void init(FilterConfig conf) throws ServletException {
		String charset = conf.getInitParameter("charset");
		if(charset!=null || !charset.isEmpty()){
			this.charset = charset;
		}
	}
}
