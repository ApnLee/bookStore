package com.bookstore.utils.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

//QueryRunner中提供对sql语句操作的API
//该类简单化了SQL查询，它与ResultSetHandler组合在一起使用可以完成大部分的数据库操作，能够大大减少编码量。
public class TxQueryRunner extends QueryRunner {

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		Connection con;
		try {
			con = DBUtil.getConnection();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		int[] result = super.batch(con, sql, params);
		DBUtil.closeConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		Connection con;
		try {
			con = DBUtil.getConnection();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		T result = super.query(con, sql, rsh, params);
		DBUtil.closeConnection(con);
		return result;
	}
	
	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection con;
		try {
			con = DBUtil.getConnection();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		T result = super.query(con, sql, rsh);
		DBUtil.closeConnection(con);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		Connection con;
		try {
			con = DBUtil.getConnection();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		int result = super.update(con, sql);
		DBUtil.closeConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		Connection con;
		try {
			con = DBUtil.getConnection();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		int result = super.update(con, sql, param);
		DBUtil.closeConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		Connection con;
		try {
			con = DBUtil.getConnection();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		int result = super.update(con, sql, params);
		DBUtil.closeConnection(con);
		return result;
	}
}