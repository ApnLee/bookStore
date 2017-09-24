package com.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Category;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	
	public void delete(String cid){
		try {
			categoryDao.delete(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int findChildrenByParent(String pid){
		try {
			return categoryDao.findChildrenyParent(pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void edit(Category category){
		try {
			categoryDao.edit(category);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Category load(String cid){
		try {
			return categoryDao.load(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void add(Category category){
		try {
			categoryDao.add(category);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Category> findAll(){
		try {
			return categoryDao.findAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Category> findParents(){
		try {
			return categoryDao.findParent();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Category> findByParent(String pid){
		try {
			return categoryDao.findByParent(pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
