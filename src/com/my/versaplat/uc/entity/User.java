package com.my.versaplat.uc.entity;

import java.io.Serializable;

public class User implements Serializable {
	private int user_id;
	private String user_name;
	private String password;
	private int credit;
	private int type;
	private String last_visit;
	private String last_ip;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getLast_visit() {
		return last_visit;
	}
	public void setLast_visit(String last_visit) {
		this.last_visit = last_visit;
	}
	public String getLast_ip() {
		return last_ip;
	}
	public void setLast_ip(String last_ip) {
		this.last_ip = last_ip;
	}
	
	
	
}
