package com.forum.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.forum.dao.IDao;
import com.forum.service.IService;
import com.forum.util.PageUtil;

public class ServiceImpl<T> implements IService<T> {
	
	@Autowired
	private IDao<T> dao;

	@Override
	public void delete(T o) {
		dao.delete(o);
	}

	@Override
	public int delete(String sql) {
		return dao.delete(sql);
	}

	/*@Override
	public PageUtil findPageByQuery(int pageSize, int startIndex,
			String queryString, int type) {
		return dao.findPageByQuery(pageSize, startIndex, queryString, type);
	}*/


	@Override
	public T get(Class<T> c, Serializable id) {
		// TODO Auto-generated method stub
		return dao.get(c, id);
	}

	@Override
	public T get(String hql) {
		// TODO Auto-generated method stub
		return dao.get(hql);
	}

	@Override
	public List<T> query(String hql) {
		// TODO Auto-generated method stub
		return dao.query(hql);
	}

	@Override
	public List<T> query(String hql, int page, int rows) {
		// TODO Auto-generated method stub
		return dao.query(hql, page, rows);
	}

	@Override
	public List querySql(String sql) {
		// TODO Auto-generated method stub
		return dao.querySql(sql);
	}

	@Override
	public List querySql(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		return dao.querySql(sql, page, rows);
	}

	@Override
	public List querySql(String sql, List<String> listKey) {
		// TODO Auto-generated method stub
		return dao.querySql(sql, listKey);
	}

	@Override
	public List querySql(String sql, String retable, Class table) {
		// TODO Auto-generated method stub
		return dao.querySql(sql, retable, table);
	}

	@Override
	public Serializable save(T o) {
		// TODO Auto-generated method stub
		return dao.save(o);
	}

	@Override
	public void saveOrUpdate(T o) {
		// TODO Auto-generated method stub
		dao.saveOrUpdate(o);
	}

	@Override
	public void update(T o) {
		// TODO Auto-generated method stub
		dao.update(o);
	}

	@Override
	public Long count(String hql) {
		// TODO Auto-generated method stub
		return dao.count(hql);
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.count(hql, params);
	}

	@Override
	public int executeHql(String hql) {
		// TODO Auto-generated method stub
		return dao.executeHql(hql);
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.executeHql(hql, params);
	}
	
	/**
	 * 分页公共方法
	 * @param pageSize 每页数据条数
	 * @param currentPage 当前页
	 * @param queryString HQL语句
	 * @return PageUtil 分页对象
	 */
	@Override
	public PageUtil findPageByQuery(int pageSize, int currentPage, String queryString){
		return dao.findPageByQuery(pageSize, currentPage, queryString);
	}

	@Override
	public T get(String hql, Map<String, Object> params) {
		return dao.get(hql, params);
	}

	@Override
	public List<T> query(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.query(hql, params);
	}

	@Override
	public List<T> query(String hql, Map<String, Object> params, int page,
			int rows) {
		// TODO Auto-generated method stub
		return dao.query(hql, params, page, rows);
	}

	@Override
	public int updateObject(Class<T> c, Map<String, Object> setParaMap,
			Map<String, Object> valParamMap) {
		// TODO Auto-generated method stub
		return dao.updateObject(c, setParaMap, valParamMap);
	}

	@Override
	public int updateObject(Class<T> c, Map<String, Object> setParam,
			String id, String[] valParam) {
		// TODO Auto-generated method stub
		return dao.updateObject(c, setParam, id, valParam);
	}

}
