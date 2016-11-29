package com.tyust.cart.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.tyust.book.bean.Book;
import com.tyust.cart.bean.CartItem;
import com.tyust.commons.CommonUtil;
import com.tyust.jdbc.TxQueryRunner;
import com.tyust.user.bean.User;

public class CartItemDao {
	private QueryRunner qr = new TxQueryRunner();
	
	private String toWhere(int len){
		StringBuilder sb = new StringBuilder("cartItemId in(");
		for(int i=0; i<len; i++){
			sb.append("?");
			if(i < len-1){
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}
	
	public List<CartItem> loadCartItems(String cartItemIds) throws SQLException{
		Object[] cartItemIdArray = cartItemIds.split(",");
		String whereSql = toWhere(cartItemIdArray.length);
		String sql = "select * from t_cartitem c,t_book b where b.bid=c.bid and "+whereSql;
		return toCartItemList(qr.query(sql, new MapListHandler(),cartItemIdArray));
	}
	
	public CartItem findByCartItemId(String cartItemId) throws SQLException{
		String sql = "select * from t_cartitem c,t_book b where c.bid=b.bid and c.cartItemId=?";
		Map<String,Object> map = qr.query(sql, new MapHandler(),cartItemId);
		return toCartItem(map);
	}
	
	public void bacthDelete(String cartItemIds) throws SQLException{
		//需要把cartItemIds转换成一个数组
		Object[] cartItemIdArray = cartItemIds.split(",");
		String whereSql = toWhere(cartItemIdArray.length);
		String sql = "delete from t_cartitem where "+whereSql;
		qr.update(sql, cartItemIdArray);
	}
	
	public CartItem findByUidAndBid(String uid,String bid) throws SQLException{
		String sql = "select * from t_cartitem where uid=? and bid=?";
		Map<String,Object> map = qr.query(sql, new MapHandler(),uid,bid);
		CartItem cartItem = toCartItem(map);
		return cartItem;
	}
	
	public void updateQuantity(String cartItemId,int quantity) throws SQLException{
		String sql = "update t_cartitem set quantity=? where cartItemId=?";
		qr.update(sql,quantity,cartItemId);
	}
	
	public void addCartItem(CartItem cartItem) throws SQLException{
		String sql = "insert into t_cartitem (cartItemId,quantity,bid,uid) "
				+ "values (?,?,?,?)";
		Object[] params = {cartItem.getCartItemId(),cartItem.getQuantity(),
				cartItem.getBook().getBid(),cartItem.getUser().getUid()};
		qr.update(sql,params);
	}
	
	public CartItem toCartItem(Map<String,Object> map){
		if(map==null || map.size()==0){
			return null;
		}
		CartItem cartItem = CommonUtil.toBean(CartItem.class, map);
		Book book = CommonUtil.toBean(Book.class, map);
		User user = CommonUtil.toBean(User.class, map);
		cartItem.setUser(user);
		cartItem.setBook(book);
		return cartItem;
	}
	
	public List<CartItem> toCartItemList(List<Map<String,Object>> mapList){
		List<CartItem> cartItemList = new ArrayList<CartItem>();
		for(Map<String,Object> map : mapList){
			CartItem cartItem = toCartItem(map);
			cartItemList.add(cartItem);
		}
		return cartItemList;
	}
	
	public List<CartItem> findByUser(String uid) throws SQLException{
		String sql = "select * from t_cartitem c,t_book b where c.bid=b.bid and uid=? order by c.orderBy";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),uid);
		return toCartItemList(mapList);
	}
}
