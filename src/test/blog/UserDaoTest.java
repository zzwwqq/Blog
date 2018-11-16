package test.blog;

import java.sql.SQLException;

import org.junit.Test;

import com.zwq.user.dao.UserDao;
import com.zwq.user.domain.User;

public class UserDaoTest {

	@Test
	public void testqqBind() {
		User user1 = new User();
		User user2 = new User();
		user1.setOpenid("1");
		user1.setFigureurl_qq_2("2");
		user1.setFigureurl_2("3");
		user1.setGender("男");
		user2.setLoginname("zwq");
		user2.setLoginpass("123zwq");
		try {
			int affect = new UserDao().qqBind(user1, user2);
			System.out.println("受影响的行数："+affect);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
