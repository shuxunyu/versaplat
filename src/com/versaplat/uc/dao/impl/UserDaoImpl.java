package com.versaplat.uc.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.versaplat.uc.dao.UserDao;
import com.versaplat.uc.entity.User;
import com.versaplat.util.BaseDao;
import com.versaplat.util.DBUtil;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public User getUserInfo(Map param) {
		User user = (User) DBUtil.getObject("UC.getUserInfo", param, sqlMapClient);
		return user;
	}

	@Override
	public boolean updateUserInfo(Map param) {
		boolean isUpdate = DBUtil.update("UC.updateUserInfo", param, sqlMapClient);
		return isUpdate;
	}

}
