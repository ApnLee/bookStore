package com.bookstore.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bookstore.dao.OrderDao;
import com.bookstore.entity.Logistics;
import com.bookstore.entity.Order;
import com.bookstore.utils.KdniaoTrackQueryAPI;
import com.bookstore.utils.PageBean;
import com.bookstore.utils.jdbc.DBUtil;

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
	
	public List<Logistics> logisticsJsonToList(){
		KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
		String result = null;
		String shipperCode = "YD";
		String logisticCode = "3967962492154";
		try {
			result = api.getOrderTracesByJson(shipperCode, logisticCode);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println(result);
		//转换成json对象
		JSONObject jsonObject=JSONObject.fromObject(result);
        //System.out.println(jsonObject.get("Traces"));
		
        //json转成list
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("Traces"));
        System.out.println("JSONArray:"+jsonArray);
        
        Iterator<Object> it = jsonArray.iterator();
        List<Logistics> logisticsList=new ArrayList<Logistics>();
        while (it.hasNext()) {
        	JSONObject ob = (JSONObject) it.next();
        	Logistics logistics = null;
            if(ob.getString("AcceptTime")!=null){
            	logistics=new Logistics();
                logistics.setAcceptTime(ob.getString("AcceptTime"));
            }
            if(ob.getString("AcceptStation")!=null){
            	logistics.setAcceptStation(ob.getString("AcceptStation"));
            }
            if(logistics!=null){
            	logisticsList.add(logistics);
            }
            System.out.println("logistics.AcceptTime:"+logistics.getAcceptTime());
			System.out.println("logistics.AcceptStation:"+logistics.getAcceptStation());
		}
	
        Collections.reverse(logisticsList);
		return logisticsList;
		
	}
}
