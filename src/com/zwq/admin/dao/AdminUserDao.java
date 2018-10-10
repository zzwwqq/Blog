package com.zwq.admin.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zwq.admin.domain.Admin;

import cn.itcast.jdbc.TxQueryRunner;

public class AdminUserDao {
	
	private QueryRunner qr = new TxQueryRunner();
	 public Admin findByAdminNameAndAdminPassword(String adminName,String adminPassword) {
		  String sql = "select * from admin where adminname=? And adminpassword=?";
		  Admin admin = new Admin();
		  try {
			  admin = qr.query(sql, new BeanHandler<Admin>(Admin.class),adminName,adminPassword);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		  return admin;		  
	  }
	 
	 public boolean ajaxValidateAdminName(String adminname) throws SQLException {
		  String sql = "select count(1) from admin where adminname=?";
		  Number number = (Number) qr.query(sql, new ScalarHandler(),adminname);
		  if(number.intValue() == 0) {
			  return true;
		  }
		  return false;
	  }
	 
	  /**
	   * 修改密码
	   * @param uid
	   * @param password
	 * @throws SQLException 
	   */
	  public void updatePassword(int adminId, String newAdminPassword) throws SQLException {
		  String sql = "update admin set adminpassword =? where adminId=?";
		  qr.update(sql, newAdminPassword,adminId);
	  }
	 
	  /**
	   * 用于修改密码
	   * @param uid
	   * @param oldpass
	   * @return
	 * @throws SQLException 
	   */
	  public boolean findByAdminIdAndPassword(int adminId,String oldpass) throws SQLException {
		  String sql = "select count(*) from admin where adminId=? and adminpassword=?";
		  Number number = (Number) qr.query(sql, new ScalarHandler(),adminId,oldpass);
		  return number.intValue()>0;	  
	  }
	 
	 
	 
	 
	
}
