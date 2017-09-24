package com.bookstore.service;

import java.sql.SQLException;

import com.bookstore.dao.AdminDao;
import com.bookstore.entity.Admin;

public class AdminService {
	private AdminDao adminDao = new AdminDao();
	
	public Admin findByAdminnameAndAdminpwd(String adminname, String adminpwd){
		try {
			return adminDao.findByAdminnameAndAdminpwd(adminname, adminpwd);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
