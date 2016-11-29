package com.tyust.admin.admin.service;

import java.sql.SQLException;

import com.tyust.admin.admin.bean.Admin;
import com.tyust.admin.admin.dao.AdminDao;

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
