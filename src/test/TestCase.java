package test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.bookstore.dao.UserDao;
import com.bookstore.entity.User;
import com.bookstore.service.UserService;
import com.bookstore.utils.jdbc.DBUtil;

public class TestCase {
	@Test
	public void test1() throws Exception{
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
	
	@Test
	public void testUserDao() throws SQLException{
		/*UserDao dao =new UserDao();*/
		UserService ser = new UserService();
		System.out.println(ser.ajaxValidateEmail("itcast_cxf@163.com"));
	}
	
	@Test
	public void testUser() throws SQLException{
		UserDao userDao = new UserDao();
		User user = userDao.findUserByLoginname("lp123456");
		System.out.println("loginname:"+user.getLoginname());
	}
}
