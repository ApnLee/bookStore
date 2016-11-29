package com.tyust.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.tyust.book.bean.Book;
import com.tyust.category.bean.Category;
import com.tyust.commons.CommonUtil;
import com.tyust.jdbc.TxQueryRunner;
import com.tyust.pager.Expression;
import com.tyust.pager.PageBean;
import com.tyust.pager.PageConstant;

public class BookDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public void delete(String bid) throws SQLException{
		String sql = "delete from t_book where bid=?";
		qr.update(sql,bid);
	}
	
	public void edit(Book book) throws SQLException{
		String sql = "update t_book "
				+ "set "
				+ "bname=?,author=?,price=?,"
				+ "currPrice=?,discount=?,"
				+ "press=?,publishtime=?,edition=?,"
				+ "pageNum=?,wordNum=?,printtime=?,"
				+ "booksize=?,paper=?,cid=? "
				+ "where bid=?";
		Object[] params = {book.getBname(),book.getAuthor(),
				book.getPrice(),book.getCurrPrice(),book.getDiscount(),
				book.getPress(),book.getPublishtime(),book.getEdition(),
				book.getPageNum(),book.getWordNum(),book.getPrinttime(),
				book.getBooksize(),book.getPaper(),book.getCategory().getCid(),
				book.getBid()};
		qr.update(sql,params);
	}
	
	public void add(Book book) throws SQLException{
		String sql = "insert into t_book "
				+ "(bid,bname,author,price,currPrice,"
				+ "discount,press,publishtime,edition,"
				+ "pageNum,wordNum,printtime,booksize,"
				+ "paper,cid,image_w,image_b) "
				+ "values "
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {book.getBid(),book.getBname(),book.getAuthor(),
				book.getPrice(),book.getCurrPrice(),book.getDiscount(),
				book.getPress(),book.getPublishtime(),book.getEdition(),
				book.getPageNum(),book.getWordNum(),book.getPrinttime(),
				book.getBooksize(),book.getPaper(),book.getCategory().getCid(),
				book.getImage_w(),book.getImage_b()};
		qr.update(sql,params);
	}
	
	public int findBookCountByCategory(String cid) throws SQLException{
		String sql = "select count(*) from t_book where cid=?";
		Number number = (Number) qr.query(sql, new ScalarHandler(),cid);
		return number == null ? 0 : number.intValue();
	}
	
	public Book findByBid(String bid) throws SQLException{
		String sql = "select * from t_book b,t_category c where b.cid=c.cid and b.bid=?";
		Map<String,Object> map = qr.query(sql, new MapHandler(),bid);
		Book book = CommonUtil.toBean(Book.class, map);
		Category category = CommonUtil.toBean(Category.class, map);
		if(map.get("pid")!=null){
			Category parent = new Category();
			parent.setCid((String) map.get("pid"));
			category.setParent(parent);
		}
		book.setCategory(category);
		return book;
	}
	
	public PageBean<Book> findByCategory(String cid, int pc) throws SQLException{
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("cid","=",cid));
		return findByCriteria(exprList, pc);
	}
	
	public PageBean<Book> findByBname(String bname, int pc) throws SQLException{
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("bname","like","%"+bname+"%"));
		return findByCriteria(exprList, pc);
	}
	
	public PageBean<Book> findByAuthor(String author, int pc) throws SQLException{
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("author","like","%"+author+"%"));
		return findByCriteria(exprList, pc);
	}
	
	public PageBean<Book> findByPress(String press, int pc) throws SQLException{
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("press","like","%"+press+"%"));
		return findByCriteria(exprList, pc);
	}
	
	public PageBean<Book> findByCombination(Book criteria , int pc) throws SQLException{
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("bname","like","%"+criteria.getBname()+"%"));
		exprList.add(new Expression("author","like","%"+criteria.getAuthor()+"%"));
		exprList.add(new Expression("press","like","%"+criteria.getPress()+"%"));
		return findByCriteria(exprList, pc);
	}
	
	public PageBean<Book> findByCriteria(List<Expression> exprList,int pc) throws SQLException{
		int ps = PageConstant.BOOK_PAGE_SIZE;
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
		String sql = "select count(*) from t_book"+whereSql;
		Number number = (Number) qr.query(sql, new ScalarHandler(),params.toArray());
		int tr = number.intValue();
		sql = "select * from t_book"+whereSql+" order by orderBy limit ?,?";
		params.add((pc-1)*ps); //当前页首行记录的下标
		params.add(ps);//每页记录数
		
		List<Book> beanList = qr.query(sql, new BeanListHandler<Book>(Book.class),params.toArray());
		PageBean<Book> pb = new PageBean<Book>();
		//其中pageBean没有url，由servlet完成
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
	}
}
