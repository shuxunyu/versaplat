package com.my.versaplat.uc.dao;

import java.util.Map;

import com.my.versaplat.uc.entity.User;

public interface UserDao {
	
	public User getUserInfo(Map param);
	
	public int updateLoginInfo(Map param);
	
}
