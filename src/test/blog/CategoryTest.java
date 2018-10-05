package test.blog;

import java.sql.SQLException;

import org.junit.Test;

import com.zwq.category.dao.CategoryDao;
import com.zwq.category.domain.Category;

public class CategoryTest {
	@Test
	public void testfindByCnameAndClevel() {
		try {
			Category category = new CategoryDao().findByCnameAndClevel("今天的故事", 20);
			System.out.println(category.getCid());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
