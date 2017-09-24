package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bookstore.entity.Book;
import com.bookstore.entity.Order;
import com.bookstore.entity.OrderItem;
import com.bookstore.utils.Expression;
import com.bookstore.utils.PageBean;
import com.bookstore.utils.PageConstant;
import com.bookstore.utils.commons.CommonUtil;
import com.bookstore.utils.jdbc.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 查询订单状态
	 * @param oid
	 * @return
	 * @throws SQLException
	 */
	public int findStatus(String oid) throws SQLException{
		String sql = "select status from s_order where oid=?";
		Number number = (Number) qr.query(sql, new ScalarHandler(),oid);
		return number.intValue();
	}
	
	/**
	 * 修改订单状态
	 * @param oid
	 * @param status
	 * @throws SQLException
	 */
	public void updateStatus(String oid,int status) throws SQLException{
		String sql = "update s_order set status=? where oid=?";
		qr.update(sql,status,oid);
	}
	
	public Order load(String oid) throws SQLException{
		String sql = "select * from s_order where oid=?";
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class),oid);
		loadOrderItem(order);
		return order;
	}
	
	public void add(Order order) throws SQLException{
		String sql = "insert into s_order values (?,?,?,?,?,?,?,?)";
		Object[] params = {order.getOid(),order.getOrdertime(),order.getTotal(),
				order.getStatus(),order.getConsignee(),order.getPhone(),
				order.getAddress(),order.getOwner().getUid()};
		qr.update(sql, params);
		
		sql = "insert into s_orderitem values (?,?,?,?,?,?,?,?)";
		int len = order.getOrderItemList().size();
		Object[][] objs = new Object[len][];
		for(int i=0; i<len; i++){
			OrderItem item = order.getOrderItemList().get(i);
				objs[i] = new Object[]{item.getOrderItemId(),
						item.getQuantity(),item.getSubtotal(),
						item.getBook().getBid(),item.getBook().getBname(),
						item.getBook().getCurrPrice(),
						item.getBook().getImage_b(),order.getOid()};
		}
		qr.batch(sql, objs);
	}
	
	public PageBean<Order> findByStatus(int status, int pc) throws SQLException{
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("status","=",status+""));
		return findByCriteria(exprList, pc);
	}
	
	public PageBean<Order> findByUser(String uid, int pc) throws SQLException{
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("uid","=",uid));
		return findByCriteria(exprList, pc);
	}
	
	/**
	 * 查询所有订单
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Order> findAll(int pc) throws SQLException{
		List<Expression> exprList = new ArrayList<Expression>();
		return findByCriteria(exprList, pc);
	}
	
	public PageBean<Order> findByCriteria(List<Expression> exprList,int pc) throws SQLException{
		int ps = PageConstant.ORDER_PAGE_SIZE;
		StringBuilder whereSql = new StringBuilder(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		for(Expression expr : exprList){
			whereSql.append(" and ").append(expr.getName())
			.append(" ").append(expr.getOperator()).append(" ");
			if(!expr.getOperator().equals("is null")){
				whereSql.append("?");
				params.add(expr.getValue());
			}
		}
		String sql = "select count(*) from s_order"+whereSql;
		Number number = (Number) qr.query(sql, new ScalarHandler(),params.toArray());
		int tr = number.intValue();
		sql = "select * from s_order"+whereSql+" order by ordertime desc limit ?,?";
		params.add((pc-1)*ps); //当前页首行记录的下标
		params.add(ps);//每页记录数
		
		List<Order> beanList = qr.query(sql, new BeanListHandler<Order>(Order.class),params.toArray());
		//虽然获取了所有订单，但是并没有获取订单条目
		for(Order order : beanList){
			loadOrderItem(order);
		}
		PageBean<Order> pb = new PageBean<Order>();
		//其中pageBean没有url，由servlet完成
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
	}
	
	/**
	 * 为制定的order加载它的所有订单条目
	 * @param order
	 * @throws SQLException 
	 */
	private void loadOrderItem(Order order) throws SQLException {
		String sql = "select * from s_orderitem where oid=?";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),order.getOid());
		List<OrderItem> orderItemList = toOrderItemList(mapList);
		order.setOrderItemList(orderItemList);
	}

	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(Map<String,Object> map : mapList){
			OrderItem orderItem = toOrderItem(map);
			orderItemList.add(orderItem);
		}
		return orderItemList;
	}

	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtil.toBean(OrderItem.class, map);
		/*Order order = CommonUtil.toBean(Order.class, map);*/
		Book book = CommonUtil.toBean(Book.class, map);
		orderItem.setBook(book);
		/*orderItem.setOrder(order);*/
		return orderItem;
	}
}
