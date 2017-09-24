package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.bookstore.entity.Book;
import com.bookstore.entity.CartItem;
import com.bookstore.entity.Evaluate;
import com.bookstore.entity.User;
import com.bookstore.utils.commons.CommonUtil;
import com.bookstore.utils.jdbc.TxQueryRunner;

public class EvaluateDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public List<Evaluate> findEvaluateByUid(String uid) throws SQLException{
		String sql = "select * from s_evaluate where uid = ?";
		List<Evaluate> evaluateList = qr.query(sql,new BeanListHandler<Evaluate>(Evaluate.class), uid);
		return evaluateList;
	}
	
	public Evaluate toEvaluate(Map<String,Object> map){
		if(map==null || map.size()==0){
			return null;
		}
		Evaluate evaluate = CommonUtil.toBean(Evaluate.class, map);
		Book book = CommonUtil.toBean(Book.class, map);
		User user = CommonUtil.toBean(User.class, map);
		evaluate.setUser(user);
		evaluate.setBook(book);
		return evaluate;
	}
	
	public List<Evaluate> toEvaluateList(List<Map<String,Object>> mapList){
		List<Evaluate> evaluateList = new ArrayList<Evaluate>();
		for(Map<String,Object> map : mapList){
			Evaluate evaluate = toEvaluate(map);
			evaluateList.add(evaluate);
		}
		return evaluateList;
	}
	
	public List<Evaluate> findEvaluateByBid(String bid) throws SQLException{
		String sql = "select * from s_evaluate e,s_user u where e.uid=u.uid and bid=? order by e.evaluateTime";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),bid);
		return toEvaluateList(mapList);
	}
	
	public void evaluate(Evaluate evaluate) throws SQLException{
		String sql = "insert into s_evaluate(evaluateId,uid,bid,evaluateDesc,evaluateTime) values(?,?,?,?,?)";
		Object[] params = {evaluate.getEvaluateId(),evaluate.getUid(),evaluate.getBid(),
						   evaluate.getEvaluateDesc(),evaluate.getEvaluateTime()}; 
		qr.update(sql,params);
	}
}
