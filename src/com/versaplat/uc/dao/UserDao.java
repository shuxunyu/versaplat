package com.versaplat.uc.dao;

import java.util.Map;

import com.versaplat.uc.entity.User;

public interface UserDao {
	
	public User getUserInfo(Map param);
	
	public boolean updateUserInfo(Map param);
	
}
