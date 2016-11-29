package testcase;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.tyust.jdbc.DBUtil;
import com.tyust.user.dao.UserDao;
import com.tyust.user.service.UserService;

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
}
