package com.zwq.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zwq.user.domain.User;

import cn.itcast.jdbc.TxQueryRunner;

public class UserDao {
    private QueryRunner qr = new TxQueryRunner();
   
    /**
     * 添加注册的用户信息
     * @param user
     * @throws SQLException
     */
  public void add(User user) throws SQLException {
	  String sql = "insert into users values(?,?,?,?,?,?)";
	  Object[] params = {user.getUid(),user.getLoginname(),user.getLoginpass(),user.getEmail(),user.getStatus(),user.getActivationCode()};
	  qr.update(sql, params);
  }
    
  /**
   * 查询用户名是否注册
   * @param loginname
   * @return
   * @throws SQLException
   */
  public boolean ajaxValidateLoginname(String loginname) throws SQLException {
	  String sql = "select count(1) from users where loginname=?";
	  Number number = (Number) qr.query(sql, new ScalarHandler(),loginname);
	  if(number.intValue() == 0) {//未查询到，表示验证成功，返回true
		  return true;
	  }
	  return false;
  }
  
  
  /**
   * 查询邮箱是否注册
   * @param loginname
   * @return
   * @throws SQLException
   */
  public boolean ajaxValidateEmail(String email) throws SQLException {
	  String sql = "select count(1) from users where email=?";
	  Number number = (Number) qr.query(sql, new ScalarHandler(),email);
	  boolean b = number.intValue() == 0;
	  return b;
  }   
}
