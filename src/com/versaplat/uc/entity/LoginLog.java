package com.versaplat.uc.entity;

import java.io.Serializable;

public class LoginLog implements Serializable {
	private int login_log_id;
	private int user_id;
	private String ip;
	private String login_time;
	public int getLogin_log_id() {
		return login_log_id;
	}
	public void setLogin_log_id(int login_log_id) {
		this.login_log_id = login_log_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	
	
	
}
