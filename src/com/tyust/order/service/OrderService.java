package com.tyust.order.service;

import java.sql.SQLException;

import com.tyust.jdbc.DBUtil;
import com.tyust.order.bean.Order;
import com.tyust.order.dao.OrderDao;
import com.tyust.pager.PageBean;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	
	public void updateStatus(String oid,int status){
		try {
			orderDao.updateStatus(oid, status);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int findStatus(String oid){
		try {
			return orderDao.findStatus(oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Order load(String oid){
		try {
			DBUtil.beginTransaction();
			Order order = orderDao.load(oid);
			DBUtil.commitTransaction();
			return order;
		} catch (SQLException e) {
			try {
				DBUtil.rollbackTransaction();
			} catch (SQLException e1) {
				
			}
			throw new RuntimeException(e);
		}
	}
	
	public void createOrder(Order order){
		try {
			DBUtil.beginTransaction();
			orderDao.add(order);
			DBUtil.commitTransaction();
		} catch (SQLException e) {
			try {
				DBUtil.rollbackTransaction();
			} catch (SQLException e1) {
				//不做处理
			}
			throw new RuntimeException(e);
		}
		
	}
	
	public PageBean<Order> myOrders(String uid,int pc){
		try {
			DBUtil.beginTransaction();
			PageBean<Order> pb = orderDao.findByUser(uid, pc);
			DBUtil.commitTransaction();
			return pb;
		} catch (SQLException e) {
			try {
				DBUtil.rollbackTransaction();
			} catch (SQLException e1) {
				//抛不出去，不用处理
			}
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查询所有订单
	 * @param pc
	 * @return
	 */
	public PageBean<Order> findAll(int pc){
		try {
			DBUtil.beginTransaction();
			PageBean<Order> pb = orderDao.findAll(pc);
			DBUtil.commitTransaction();
			return pb;
		} catch (SQLException e) {
			try {
				DBUtil.rollbackTransaction();
			} catch (SQLException e1) {
				//抛不出去，不用处理
			}
			throw new RuntimeException(e);
		}
	}
	
	public PageBean<Order> findByStatus (int stauts ,int pc){
		try {
			DBUtil.beginTransaction();
			PageBean<Order> pb = orderDao.findByStatus(stauts,pc);
			DBUtil.commitTransaction();
			return pb;
		} catch (SQLException e) {
			try {
				DBUtil.rollbackTransaction();
			} catch (SQLException e1) {
				//抛不出去，不用处理
			}
			throw new RuntimeException(e);
		}
	}
}
