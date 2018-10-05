package test.blog;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.zwq.blog.dao.BlogDao;
import com.zwq.blog.domain.Blog;

public class BlogTest {
	@Test
	public void testfindByBid() throws SQLException {
		Blog blog = new BlogDao().findByBidGetBlog(2);
		System.out.println(blog.getCreated_time());		
	}
	
	@Test
	public void testfindBlogList() throws SQLException {
		List<Blog> blogList = new BlogDao().findBlogList();
		for(Blog blog : blogList) {
			System.out.println(blog.getBid());
			System.out.println(blog.getTitle());
			System.out.println(blog.getContent());
			System.out.println(blog.getCategory_id());
			System.out.println(blog.getCreated_time());
		}
	}
		
}
