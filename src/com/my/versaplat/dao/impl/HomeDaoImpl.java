package com.my.versaplat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.my.versaplat.dao.HomeDao;
import com.my.versaplat.entity.Category;
import com.my.util.BaseDao;
import com.my.util.DBUtil;

@Repository("homeDao")  
@Transactional 
public class HomeDaoImpl extends BaseDao implements HomeDao {

	@Override
	public List getCategoryList(Map param) {
		List clist = DBUtil.getList("getCategroyList", param, sqlMapClient);
		return clist;
	}

}
