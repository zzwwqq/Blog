package com.zwq.page.domain;

import java.util.List;

public class PageBean<T> {
	private int totalRecordsNum;//总记录数
	private int perPageRecordsNum;//每页记录数
	private int totalPageNum;//总页数 根据 总记录数和每页记录数求得
	private int currentPage;//当前页
	private List<T> beanList;//每页显示的数据集合
	
	/**
	 * 计算总页数
	 * @return
	 */
	public int getTotalPageNum() {
		this.totalPageNum = totalRecordsNum / perPageRecordsNum;
		return totalRecordsNum % perPageRecordsNum==0? this.totalPageNum : this.totalPageNum+1;
	}
	
	
	public int getTotalRecordsNum() {
		return totalRecordsNum;
	}
	public void setTotalRecordsNum(int totalRecordsNum) {
		this.totalRecordsNum = totalRecordsNum;
	}
	public int getPerPageRecordsNum() {
		return perPageRecordsNum;
	}
	public void setPerPageRecordsNum(int perPageRecordsNum) {
		this.perPageRecordsNum = perPageRecordsNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	
	
	
	
	
	
	
}
