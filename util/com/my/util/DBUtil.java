package com.my.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @author hqq
 * 封装常用数据库操作
 */
public class DBUtil {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(DBUtil.class);
	
	/**
	 * 插入一条记录
	 * @param sqlId
	 * @param param
	 * @param sqlMapClient
	 * @return 保存失败返回-1(int类型)
	 */
	public static Object insert(String sqlId,Object param,SqlMapClient sqlMapClient){
		Object obj = null;
		try {
			obj = sqlMapClient.insert(sqlId, param);
		} catch (Exception e) {
			obj = -1;
			log.error("出错了！",e);
		}
		return obj;
	}
	/**
	 * 更新一条记录
	 * @param sqlId
	 * @param param
	 * @param sqlMapClient
	 * @return 成功 or 失败
	 */
	public static boolean update(String sqlId,Object param,SqlMapClient sqlMapClient){
		boolean flag = false;
		try {
			if(param == null){
				sqlMapClient.update(sqlId);
			}else{
				sqlMapClient.update(sqlId, param);
			}
			flag = true;
		} catch (Exception e) {
			log.error("出错了！",e);
		}
		return flag;
	}
	/**
	 * 删除记录
	 * @param sqlId
	 * @param param
	 * @param sqlMapClient
	 * @return 成功 or 失败
	 */
	public static boolean delete(String sqlId,Object param,SqlMapClient sqlMapClient){
		boolean flag = false;
		try {
			if(param == null){
				sqlMapClient.delete(sqlId);
			}else{
				sqlMapClient.delete(sqlId, param);
			}
			flag = true;
		} catch (Exception e) {
			log.error("出错了！",e);
		}
		return flag;
	}
	/**
	 * 返回单条记录的查询
	 * @param sqlId
	 * @param param
	 * @param sqlMapClient
	 * @return Map
	 */
	public static Map getMap(String sqlId,Object param,SqlMapClient sqlMapClient){
		Map map = null;
		try {
			if(param == null){
				map = (Map) sqlMapClient.queryForObject(sqlId);
			}else{
				map = (Map) sqlMapClient.queryForObject(sqlId, param);
			}
		} catch (Exception e) {
			log.error("出错了！",e);
		}
		return map;
	}
	/**
	 * 返回单条记录的查询
	 * @param sqlId
	 * @param param
	 * @param sqlMapClient
	 * @return Object
	 */
	public static Object getObject(String sqlId,Object param,SqlMapClient sqlMapClient){
		Object obj = null;
		try {
			if(param == null){
				obj = sqlMapClient.queryForObject(sqlId);
			}else{
				obj = sqlMapClient.queryForObject(sqlId, param);
			}
		} catch (Exception e) {
			log.error("出错了！",e);
		}
		return obj;
	}
	/**
	 * 返回多条记录的查询
	 * @param sqlId
	 * @param param
	 * @param sqlMapClient
	 * @return
	 */
	public static List<Map> getList(String sqlId,Object param,SqlMapClient sqlMapClient){
		List<Map> list = null;
		try {
			if(param == null){
				list = sqlMapClient.queryForList(sqlId);
			}else{
				list = sqlMapClient.queryForList(sqlId, param);
			}
		} catch (Exception e) {
			log.error("出错了！",e);
		}
		return list;
	}
	/**
	 * 获取分页列表
	 * @param condition 查询条件<Map<key,value>>
	 * @param orderBy 排序字段及排序方向，如无需排序，则传 null
	 * @param showPage 页数，如小于1，则取默认值 1 （1表示第1页）
	 * @param pageSize 页大小，如小于1，则取默认值10
	 * @param countSqlId 查询符合条件总记录数的sql名称，对应xml配置文件中的id
	 * @param dataSqlId 查询数据的sql名称，对应xml配置文件中的id
	 * @return Map(
    	[totalPages] => 总页数
    	[totalRows] => 符合条件的总记录数
    	[count] => 符合条件的总记录数
    	[nowPage] => 当前显示页码
    	[pageSize] => 页大小
    	[data] => 当前页码的数据，格式如下：（总体是一个List，list里面是map，每一个map对应一行记录）
    	List<
    		Map{
    			列名1 => 值
    			列名2 => 值
    			}
    		>
	 */
	public static Map getPage(Map<String,Object> condition, String orderBy, int showPage,int pageSize,
			String countSqlId,String dataSqlId, SqlMapClient sqlMapClient){

		//数据合法性校验
		if(countSqlId == null || "".equals(countSqlId)){
			log.error("getCountState不能为空！");
			return null;
		}
		if(dataSqlId == null || "".equals(dataSqlId)){
			log.error("getDataState不能为空！");
			return null;
		}
		if(showPage <= 0){
			showPage = 1;
		}
		if(pageSize <= 0){
			pageSize = 10;
		}
		if(condition == null){
			condition = new HashMap<String,Object>();
		}
		Map<String,Object> returnMap = new HashMap<String, Object>();
		try {
			
			//总记录数
			int totalRows = Integer.parseInt(sqlMapClient.queryForObject(countSqlId, condition).toString());
			
			//总页数（模运算，也叫取余运算：7%3=1    ； 除法运算：7/3=2）
			int totalPages = (totalRows%pageSize==0?totalRows/pageSize:totalRows/pageSize+1);
			
			//计算偏移量，即起始记录行号
			int offset = (showPage-1)*pageSize;
			
			//组装查询条件
			condition.put("_order", orderBy);
			
			Map<String,Integer> limitPara = new HashMap<String,Integer>();
			limitPara.put("_pstart", offset);
			limitPara.put("_psize", pageSize);
			
			condition.put("_limit", limitPara);
			
			//查询数据
			List<Map<String,Object>> list = sqlMapClient.queryForList(dataSqlId, condition);

			//组装返回值
			if(list.size()>0){
				returnMap.put("data", list);
				returnMap.put("totalPages", totalPages);
				returnMap.put("totalRows", totalRows);
				returnMap.put("count", totalRows);
				returnMap.put("nowPage", showPage);
				returnMap.put("pageSize", pageSize);
			}
		} catch (Exception e) {
			log.error("出错了！",e);
		}
		return returnMap;
	}
}
