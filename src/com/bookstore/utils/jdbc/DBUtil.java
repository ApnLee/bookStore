package com.bookstore.utils.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 该类负责管理数据库的连接
 * @author Administrator
 *
 */
public class DBUtil {
	//连接池
	private static BasicDataSource ds = new BasicDataSource();
	//用于保存每个线程借走的连接
	/*
	 * ThreadLocal内部是一个Map
	 * key:调用ThreadLocal功能的线程
	 * value:涉及到该线程使用的需要保存的数据
	 */
	private static ThreadLocal<Connection> threadLocal;
	/*
	 * 类的静态成员初始化工作应当放在静态块中完成
	 * 非静态成员一般在构造方法中初始化。
	 * 注意区分。
	 */
	//实例化连接池      饿汉式
	public static BasicDataSource getDataSource(){
		return ds;
	}
	static{
		//1 加载配置文件
		/*
		 * java.util.Properties
		 * 该类可以读取后缀名为.properties的
		 * 配置文件，并解析其中的配置项，将其以
		 * 类似Map的形式表示，解析后我们就可以
		 * 通过该类根据"="左面的内容获取对应的
		 * 右面的值。
		 */
		try{
			Properties prop = new Properties();
			prop.load(DBUtil.class.getClassLoader()
					.getResourceAsStream("config.properties"));
			String driverName = prop.getProperty("driverClassName");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			int maxActive = Integer.parseInt(
				prop.getProperty("maxActive")
			);
			int maxWait = Integer.parseInt(
				prop.getProperty("maxWait")	
			);
			
			//设置驱动
			ds.setDriverClassName(driverName);
			//设置URL
			ds.setUrl(url);
			//设置用户名
			ds.setUsername(username);
			//设置密码
			ds.setPassword(password);
			//设置最大连接数
			ds.setMaxActive(maxActive);
			//设置最大等待时间
			ds.setMaxWait(maxWait);			
			threadLocal = new ThreadLocal<Connection>();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 获取一个数据库的连接
	 * @return
	 * @throws 当获取连接失败时抛出该异常
	 */
	/**
	 * 它为null表示没有事务
	 * 它不为null表示有事务
	 * 当开启事务时，需要给它赋值
	 * 当结束事务时，需要给它赋值为null
	 * 并且在开启事务时，让dao的多个方法共享这个Connection
	 */
	public static Connection getConnection()throws Exception{
		try {
			/*
			 * Connection getConnection()
			 * 连接池提供该方法用来获取一个可用的空闲
			 * 连接。若连接池设置了最大等待时间，那么
			 * 当连接池没有可用连接时，该方法会进入阻塞
			 * ，阻塞时间是最大等待时间，在期间连接池若
			 * 有空闲连接该方法会立刻解除阻塞返回连接。
			 * 若超时后还没有可用连接，该方法会抛出超时
			 * 异常。
			 */
			Connection conn = threadLocal.get();//获取当前事物的连接
			if(conn != null){
				return conn;
			}
			return ds.getConnection();
			/*
			 * 将当前调用set方法的线程作为key,参数作为
			 * value存入ThreadLocal内部的Map中。
			 */
		} catch (Exception e) {
			//这里可以感知异常，并记录日志等操作
			//抛出异常是通知调用者这里失败了。
			throw new RuntimeException("获取数据库连接失败!",e);
		}
	}
	/*
	 * 开启事务	
	 */
	public static void beginTransaction() throws SQLException{
		Connection con = threadLocal.get();
		if(con!=null) throw new SQLException("已经开启了事务，不能重复开启！");
		con = ds.getConnection(); //如果con为空，表示未开启事务，赋值一个新链连,开启事务
		con.setAutoCommit(false);
		threadLocal.set(con);   //把当前事务连接放到threadLocal中
	}
	/*
	 * 提交事务
	 */
	public static void commitTransaction() throws SQLException{
		Connection con = threadLocal.get();
		if(con==null) throw new SQLException("没有事务不能提交！");
		con.commit();
		con.close();
		con = null;
		threadLocal.remove();  //从threadLocal中移除
	}
	/*
	 * 回滚事务
	 */
	public static void rollbackTransaction() throws SQLException{
		Connection con = threadLocal.get();
		if(con==null) throw new SQLException("没有事务不能回滚！");
		con.rollback();
		con.close();
		con=null;
		threadLocal.remove();
	}
		
	/**
	 * 关闭数据库连接
	 */
	public static void closeConnection(Connection con)throws SQLException{
		try {
			Connection conn = threadLocal.get();
			if(conn!=con){    //如果参数连接与当前事务连接不同，说明这个连接不是当前事务，可以关闭！
				if(con!=null && !con.isClosed()){  //如果参数连接不为空且没有关闭，关闭之！
					con.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(con);
		}
	}
}



