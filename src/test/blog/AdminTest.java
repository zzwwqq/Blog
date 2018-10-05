package test.blog;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;

import com.zwq.admin.dao.AdminBlogDao;
import com.zwq.blog.domain.Blog;

public class AdminTest {
	@Test
	public void testUpdateBlogByBid() {
		Blog blog = new Blog();
		blog.setBid(2);
		blog.setCategory_id(1);
		blog.setTitle("国庆长假前2天 北京非法“一日游”投诉降9成");
		blog.setCreated_time(new Timestamp(new Date().getTime()));
		blog.setContent("北京晨报讯（记者 吴婷婷）国庆黄金周期间，本市相关部门和各区以整治北京非法“一日游”为重点，严厉打击“六黑”乱象。北京晨报记者了解到，国庆假期前三天，旅游部门共接到一起“一日游”相关投诉，较去年下降九成。昨天，市旅游委、市公安局环食药旅总队、市工商局、市城管执法局、市交通执法总队、市公园管理中心等市假日办成员单位及昌平区相关部门执法人员，组成联合检查组，对昌平区璟翠玉城、润泽玉苑及定陵等非法“一日游”高发频发和影响大的重点点位开展联合执法检查及景区假日安全服务保障。联合检查组重点围绕旅游购物店营业资质及经营行为的规范性、旅游团队的规范性及景区旅游秩序、客流量、安全应急预案等问题进行检查，未发现违规经营等情况。市旅游委相关负责人介绍，昨天，全市各部门、各区针对旅游市场秩序共出动执法人员3037人次，车辆478台次，检查旅游车73辆，检查导游68人次，检查各类旅游、经营企业718家，累计向来京游客推送北京旅游提示短信84.7万条，全市无重大旅游投诉发生。本市持续高压打击非法“一日游”，成效明显。市旅游委相关负责人介绍，10月1日至3日，市旅游委共收到非法“一日游”相关投诉1件，这起投诉是因为旅行社大巴车未接上游客，导致该名游客未能与同伴一起出游。接到投诉后，市旅游委迅速联系上旅行社，大约一个小时后，旅行社派了另外一辆车将游客接走，完成既定旅游行程。此外，市旅游委继续联合相关部门组成暗访组开展随团暗访，昨天共派出市级跟团暗访组2个，对“一日游”行程中出现的违法违规问题进行取证并依法进行严肃处理。");
		try {
			new AdminBlogDao().updateBlogByBid(blog);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}
}
