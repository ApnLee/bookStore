package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bookstore.entity.Category;
import com.bookstore.utils.commons.CommonUtil;
import com.bookstore.utils.jdbc.TxQueryRunner;

public class CategoryDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public Category toCategory(Map<String,Object> map){
		Category category = CommonUtil.toBean(Category.class, map);
		String pid = (String) map.get("pid");
		if(pid!=null){
			Category parent = new Category();
			parent.setCid(pid);
			category.setParent(parent);
		}
		return category;
	}
	
	private List<Category> toCategoryList(List<Map<String, Object>> mapList) {
		List<Category> categoryList = new ArrayList<Category>();
		for(Map<String,Object> map : mapList){
			Category c = toCategory(map);
			categoryList.add(c);
		}
		return categoryList;
	}
	
	public List<Category> findAll() throws SQLException{
		/*String sql = "select * from t_category where pid is null order by orderBy";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
		List<Category> parents = toCategoryList(mapList);*/
		List<Category> parents = findParent();
		for(Category parent : parents){
			List<Category> children = findByParent(parent.getCid());
			parent.setChildren(children);
		}
		
		return parents;
	}
	
	public List<Category> findParent() throws SQLException{
		String sql = "select * from s_category where pid is null order by cname";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
		List<Category> parents = toCategoryList(mapList);
		
		return parents;
	}

	public List<Category> findByParent(String pid) throws SQLException {
		String sql = "select * from s_category where pid=? order by cname";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),pid);
		return toCategoryList(mapList);
	}
	
	public void add(Category category) throws SQLException{
		String sql = "insert into s_category (cid,cname,pid,`desc`) "
				+ "values (?,?,?,?)";
		//一级分类没有pid
		String pid = null;
		if(category.getParent()!=null){
			pid=category.getParent().getCid();
		}
		Object[] params = {category.getCid(),category.getCname(),
				pid,category.getDesc()};
		qr.update(sql,params);
	}
	
	public Category load(String cid) throws SQLException{
		String sql = "select * from s_category where cid=?";
		return toCategory(qr.query(sql, new MapHandler(),cid));
	}
	
	public void edit(Category category) throws SQLException{
		String sql = "update s_category set cname=?,pid=?,`desc`=? where cid=?";
		String pid = null;
		if(category.getParent()!=null){
			pid = category.getParent().getCid();
		}
		Object[] params = {category.getCname(),pid,category.getDesc(),category.getCid()};
		qr.update(sql,params);
	}
	
	public int findChildrenyParent(String pid) throws SQLException{
		String sql = "select count(*) from s_category where pid=?";
		Number number = (Number) qr.query(sql, new ScalarHandler(),pid);
		return number == null ? 0 : number.intValue();
	}
	
	public void delete(String cid) throws SQLException{
		String sql = "delete from s_category where cid=?";
		qr.update(sql,cid);
	}

}
