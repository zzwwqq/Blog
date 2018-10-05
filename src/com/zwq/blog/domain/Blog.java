package com.zwq.blog.domain;

import java.sql.Timestamp;

public class Blog {
	private int bid;
	private String title;
	private String content;
	private int category_id;
	private Timestamp created_time;
	

	public int getBid() {
		return bid;
	}


	public void setBid(int bid) {
		this.bid = bid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getCategory_id() {
		return category_id;
	}


	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	public Timestamp getCreated_time() {
		return created_time;
	}


	public void setCreated_time(Timestamp created_time) {
		this.created_time = created_time;
	}


	public Blog() {
		super();
	}

	
}
