package com.versaplat.uc.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.versaplat.uc.dao.LoginLogDao;
import com.versaplat.util.BaseDao;
import com.versaplat.util.DBUtil;

@Repository("loginLogDao")
@Transactional
public class LoginLogDaoImpl extends BaseDao implements LoginLogDao {

	@Override
	public void insertLoginLog(Map param) {
		int id = (int) DBUtil.insert("UC.insertLoginLog", param, sqlMapClient);
	}

}
