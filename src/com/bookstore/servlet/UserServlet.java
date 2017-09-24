package com.bookstore.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.entity.User;
import com.bookstore.service.UserService;
import com.bookstore.utils.commons.CommonUtil;


public class UserServlet extends MyServlet{

	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();
	
	public String quit(HttpServletRequest request,
					   HttpServletResponse response)
		               throws ServletException,IOException{
		request.getSession().invalidate();
		return "r:/jsps/user/login.jsp";
	}
	
	public String updatePassword(HttpServletRequest request,
								HttpServletResponse response)
					            throws ServletException,IOException{
		User formUser = CommonUtil.toBean(User.class, request.getParameterMap());
		User user = (User) request.getSession().getAttribute("sessionUser");
		if(user==null){
			request.setAttribute("msg", "您还未登录！");
			return "/jsps/user/login.jsp";
		}
		try {
			userService.updatePassword(user.getUid(), formUser.getLoginpass(), formUser.getNewpass());
			request.setAttribute("msg", "修改密码成功！");
			request.setAttribute("code", "success");
			return "/jsps/msg.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", formUser);
			return "/jsps/user/pwd.jsp";
		}
	}
	
	public String login(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception{
		User formUser = CommonUtil.toBean(User.class, request.getParameterMap());
		Map<String,String> errors =
				validateLogin(formUser,request.getSession());
		if(errors.size() > 0){
			request.setAttribute("user", formUser);
			request.setAttribute("errors", errors);
			return "/jsps/user/login.jsp";
		}
		User user = userService.login(formUser);
		if(user==null){
			request.setAttribute("msg", "用户名或密码错误！");
			request.setAttribute("user", formUser);
			return "/jsps/user/login.jsp";
		}else{
			if(!user.isStatus()){
				request.setAttribute("msg", "您还未激活，不能登录！");
				request.setAttribute("user", formUser);
				return "/jsps/user/login.jsp";
			}else{
				request.getSession().setAttribute("sessionUser", user);
				String loginname = user.getLoginname();
				loginname = URLEncoder.encode(loginname,"utf-8");
				Cookie cookie = new Cookie("loginname",loginname);
				cookie.setMaxAge(60*60*24*10);
				response.addCookie(cookie);
				return "/index.jsp";
			}
		}
	}
	
public Map<String,String> validateLogin(User formUser,HttpSession session){
		
		Map<String,String> errors = new HashMap<String,String>();
		String loginname = formUser.getLoginname();
		if(loginname==null || loginname.trim().isEmpty()){
			errors.put("loginname", "用户名不能为空！");
		}else if(loginname.length()<=3 || loginname.length()>20){
			errors.put("loginname", "用户名必须是4~20个字符！");
		}
		
		String loginpass = formUser.getLoginpass();
		if(loginpass==null || loginpass.trim().isEmpty()){
			errors.put("loginpass", "密码不能为空！");
		}else if(loginpass.length()<=3 || loginpass.length()>20){
			errors.put("loginpass", "密码必须是4~20个字符！");
		}
		
		String verifyCode = formUser.getVerifyCode();
		String vCode = (String) session.getAttribute("vCode");
		if(verifyCode==null || verifyCode.trim().isEmpty()){
			errors.put("verifyCode", "验证码不能为空！");
		}else if(!verifyCode.equalsIgnoreCase(vCode)){
			errors.put("verifyCode", "验证码错误！");
		}
		return errors;
	}
	
	public String regist(HttpServletRequest request,
							HttpServletResponse response)
							throws Exception{
		User formUser = CommonUtil.toBean(User.class, request.getParameterMap());
		Map<String,String> errors = validateRegist(formUser,request.getSession());
		if(errors.size() > 0){
			request.setAttribute("form", formUser);
			request.setAttribute("errors", errors);
			return "f:/jsps/user/regist.jsp";
		}
		userService.regist(formUser);
			
		request.setAttribute("code", "success");
		request.setAttribute("msg", "注册成功，请马上到邮箱激活！");
		return "f:/jsps/msg.jsp";	
	}
	
	public Map<String,String> validateRegist(User formUser,HttpSession session){
		
		Map<String,String> errors = new HashMap<String,String>();
		String loginname = formUser.getLoginname();
		if(loginname==null || loginname.trim().isEmpty()){
			errors.put("loginname", "用户名不能为空！");
		}else if(loginname.length()<=3 || loginname.length()>20){
			errors.put("loginname", "用户名必须是4~20个字符！");
		}else if(!userService.ajaxValidateLoginname(loginname)){
			errors.put("loginname", "该用户名已被注册！");
		}
		
		String loginpass = formUser.getLoginpass();
		if(loginpass==null || loginpass.trim().isEmpty()){
			errors.put("loginpass", "密码不能为空！");
		}else if(loginpass.length()<=3 || loginpass.length()>20){
			errors.put("loginpass", "密码必须是4~20个字符！");
		}
		
		String reloginpass = formUser.getReloginpass();
		if(reloginpass==null || reloginpass.trim().isEmpty()){
			errors.put("reloginpass", "确认密码不能为空！");
		}else if(!reloginpass.equals(loginpass)){
			errors.put("reloginpass", "两次密码输入不相同！");
		}
		
		String email = formUser.getEmail();
		if(email==null || email.trim().isEmpty()){
			errors.put("email", "Email不能为空！");
		}else if(!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")){
			errors.put("email", "Email格式错误！");
		}else if(!userService.ajaxValidateEmail(email)){
			errors.put("email", "该Email已被注册！");
		}
		
		String verifyCode = formUser.getVerifyCode();
		String vCode = (String) session.getAttribute("vCode");
		if(verifyCode==null || verifyCode.trim().isEmpty()){
			errors.put("verifyCode", "验证码不能为空！");
		}else if(!verifyCode.equalsIgnoreCase(vCode)){
			errors.put("verifyCode", "验证码错误！");
		}
		return errors;
	}
	
	public String activation(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException,IOException{
		String code = request.getParameter("activationCode");
		try {
			userService.activation(code);
			request.setAttribute("code", "success");
			request.setAttribute("msg", "恭喜您，激活成功，马上登录吧！");
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("code", "error");
		}
		return "/jsps/msg.jsp";
	}
	
	public String findUser(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{		
		User user = userService.findUserByLoginname(request.getParameter("loginname"));
		request.setAttribute("user", user);
		return "/jsps/user/user.jsp";
	}
	
	public String updateUser(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
		User user = new User();
		user.setUid(request.getParameter("id"));
		user.setLoginname(request.getParameter("loginname"));
		user.setEmail(request.getParameter("email"));
		user.setGender(request.getParameter("gender"));
		user.setNickname(request.getParameter("nickname"));
		
		userService.updateUser(user);
		request.getSession().setAttribute("sessionUser", user);
		String loginname = user.getLoginname();
		loginname = URLEncoder.encode(loginname,"utf-8");
		Cookie cookie = new Cookie("loginname",loginname);
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		return "/index.jsp";
	}
	
	public String ajaxValidateLoginname(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException,IOException{
		String loginname = request.getParameter("loginname");
		boolean b = userService.ajaxValidateLoginname(loginname);
		response.getWriter().println(b);
		return null;
	}
	
	public String ajaxValidateEmail(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException,IOException{
		String email = request.getParameter("email");
		boolean b = userService.ajaxValidateEmail(email);
		response.getWriter().println(b);
		return null;
	}
	
	public String ajaxValidateVerifyCode(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException,IOException{
		String verifyCode = request.getParameter("verifyCode");
		String vCode = (String) request.getSession().getAttribute("vCode");
		boolean b = verifyCode.equalsIgnoreCase(vCode);
		response.getWriter().println(b);
		return null;
	}
}
