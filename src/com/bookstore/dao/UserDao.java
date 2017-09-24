package com.bookstore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bookstore.entity.User;
import com.bookstore.utils.jdbc.TxQueryRunner;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public boolean findByUidAndLoginpass(String uid, String loginpass) throws SQLException{
		String sql = "select count(*) from s_user where uid=? and loginpass=?";
		Number number = (Number) qr.query(sql, new ScalarHandler(),uid,loginpass);
		return number.intValue()>0;
	}
	
	public void updateLoginpass(String uid, String loginpass) throws SQLException{
		String sql = "update s_user set loginpass=? where uid=?";
		qr.update(sql,loginpass,uid);
	}
	
	public User findByLoginnameAndLoginpass(String loginname, String loginpass) throws SQLException{
		String sql = "select * from s_user where loginname=? and loginpass=?";
		return qr.query(sql, new BeanHandler<User>(User.class), loginname, loginpass);
	}
	
	public User findByCode(String code) throws SQLException{
		String sql = "select * from s_user where activationCode=?";
		return qr.query(sql, new BeanHandler<User>(User.class), code);
	}
	
	public void updateStatus(String uid, boolean status) throws SQLException{
		String sql = "update s_user set status=? where uid=?";
		qr.update(sql,status,uid);
	}
	
	public boolean ajaxValidateLoginname(String loginname) throws SQLException{
		String sql = "select count(1) from s_user where loginname=?";
		Number number = (Number) qr.query(sql, new ScalarHandler(),loginname);
		return number.intValue()==0;
	}
	
	public boolean ajaxValidateEmail(String email) throws SQLException{
		String sql = "select count(1) from s_user where email=?";
		Number number = (Number) qr.query(sql, new ScalarHandler(),email);
		return number.intValue()==0;
	}
	
	public void add(User user) throws SQLException{
		String sql = "insert into s_user "
				+ "(uid,loginname,loginpass,email,status,activationCode,gender,nickname) "
				+ "values (?,?,?,?,?,?,null,null)";
		Object[] params = {user.getUid(),user.getLoginname(),
				user.getLoginpass(),user.getEmail(),user.isStatus(),
				user.getActivationCode()}; 
		qr.update(sql,params);
	}
	
	public User findUserByLoginname(String loginname) throws SQLException{
		String sql = "select * from s_user where loginname = ?";
		return qr.query(sql, new BeanHandler<User>(User.class), loginname);
	}
	
	public void updateUser(User user) throws SQLException{
		String sql = "update s_user set loginname=?,email=?,gender=?,nickname=? where uid=?";
		Object[] params = {user.getLoginname(),user.getEmail(),user.getGender(),user.getNickname(),user.getUid()};
		qr.update(sql,params);
	}
	
	public User findUserByUid(String uid) throws SQLException{
		String sql = "select * from s_user where uid = ?";
		return qr.query(sql, new BeanHandler<User>(User.class), uid);
	}
	
}
