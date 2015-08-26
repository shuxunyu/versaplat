package com.my.versaplat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.versaplat.dao.HomeDao;

@Service
public class HomeService {
	
	@Autowired
	private HomeDao homeDao;

	@Transactional(propagation=Propagation.REQUIRED) 
	public List getCategoryList(Map param) {
		return homeDao.getCategoryList(param);
		
	}

}
