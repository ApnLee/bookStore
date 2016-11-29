package com.tyust.cart.service;

import java.sql.SQLException;
import java.util.List;

import com.tyust.cart.bean.CartItem;
import com.tyust.cart.dao.CartItemDao;
import com.tyust.commons.CommonUtil;

public class CartItemService {
	private CartItemDao cartItemDao = new CartItemDao();
	
	public List<CartItem> loatCartItems(String cartItemIds) {
		try {
			return cartItemDao.loadCartItems(cartItemIds);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public CartItem updateQuantity(String cartItemId,int quantity){
		try {
			cartItemDao.updateQuantity(cartItemId, quantity);
			return cartItemDao.findByCartItemId(cartItemId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void batchDelete(String cartItemIds){
		try {
			cartItemDao.bacthDelete(cartItemIds);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void add(CartItem cartItem){
		try {
			CartItem _cartItem = cartItemDao.findByUidAndBid(cartItem.getUser().getUid(), 
					cartItem.getBook().getBid());
			if(_cartItem==null){
				cartItem.setCartItemId(CommonUtil.uuid());
				cartItemDao.addCartItem(cartItem);
			}else{
				int quantity = cartItem.getQuantity()+_cartItem.getQuantity();
				cartItemDao.updateQuantity(_cartItem.getCartItemId(), quantity);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<CartItem> myCart(String uid){
		try {
			return cartItemDao.findByUser(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
