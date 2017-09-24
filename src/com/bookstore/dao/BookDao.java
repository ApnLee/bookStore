package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.utils.Expression;
import com.bookstore.utils.PageBean;
import com.bookstore.utils.PageConstant;
import com.bookstore.utils.commons.CommonUtil;
import com.bookstore.utils.jdbc.TxQueryRunner;

public class BookDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 根据id删除图书
	 */
	public void delete(String bid) throws SQLException{
		String sql = "delete from s_book where bid=?";
		qr.update(sql,bid);
	}
	
	/**
	 * 编辑图书
	 */
	public void edit(Book book) throws SQLException{
		String sql = "update s_book "
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
	
	/**
	 * 添加图书
	 */
	public void add(Book book) throws SQLException{
		String sql = "insert into s_book "
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
	
	/**
	 * 查找同一类别图书的数量
	 */
	public int findBookCountByCategory(String cid) throws SQLException{
		String sql = "select count(*) from s_book where cid=?";
		Number number = (Number) qr.query(sql, new ScalarHandler(),cid);
		return number == null ? 0 : number.intValue();
	}
	
	/**
	 * 根据id查找
	 */
	public Book findByBid(String bid) throws SQLException{
		String sql = "select * from s_book b,s_category c where b.cid=c.cid and b.bid=?";
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
		String sql = "select count(*) from s_book"+whereSql;
		Number number = (Number) qr.query(sql, new ScalarHandler(),params.toArray());
		int tr = number.intValue();
		sql = "select * from s_book"+whereSql+" order by bid limit ?,?";
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
