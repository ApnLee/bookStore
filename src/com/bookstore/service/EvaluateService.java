package com.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.bookstore.dao.EvaluateDao;
import com.bookstore.entity.Evaluate;


public class EvaluateService {
	
	private EvaluateDao evaluateDao = new EvaluateDao(); 
	
	public List<Evaluate> findEvaluateByUid(String uid){
		
		try {
			return evaluateDao.findEvaluateByUid(uid);
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
	}
	
	public List<Evaluate> findEvaluateByBid(String bid){
		
		try {
			return evaluateDao.findEvaluateByBid(bid);
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
	}
	
	public void evaluate(Evaluate evaluate) {
		
		try {
			evaluateDao.evaluate(evaluate);
		} catch (SQLException e) {
		
			throw new RuntimeException(e);
		}
	}
}
