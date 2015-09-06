package com.versaplat.uc.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.versaplat.uc.dao.LoginLogDao;
import com.versaplat.uc.dao.UserDao;
import com.versaplat.uc.entity.User;

@Service
public class UserService {
	
	@Autowired
	private LoginLogDao loginLogDao;
	
	@Autowired
	private UserDao userDao;
	
	public boolean hasMatchUser(String username, String password) {
		boolean isMatchUser = false;
		Map param = new HashMap();
		param.put("user_name", username);
		param.put("password", password);
		User user = userDao.getUserInfo(param);
		if(user!=null){
			if(user.getUser_name()!=null){
				isMatchUser = true;
			}
		}
		return isMatchUser;
	}
	
	public User findUserByName(String username){
		Map param = new HashMap();
		param.put("user_name", username);
		return userDao.getUserInfo(param);
	}
	
	public void loginSuccess(String userid, String lastIp, String lastVisit){
		Map param = new HashMap();
		param.put("user_id", userid);
		param.put("last_ip", lastIp);
		param.put("last_visit", lastVisit);
		param.put("ip", lastIp);
		param.put("login_time", lastVisit);
		userDao.updateUserInfo(param);
		loginLogDao.insertLoginLog(param);
		
	}
}
