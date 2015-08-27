package com.versaplat.uc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.versaplat.uc.dao.LoginLogDao;
import com.versaplat.uc.dao.UserDao;

@Service
public class UserService {
	
	@Autowired
	private LoginLogDao loginLogDao;
	
	@Autowired
	private UserDao userDao;
	
	
}
