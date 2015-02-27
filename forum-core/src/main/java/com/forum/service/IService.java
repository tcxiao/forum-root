package com.forum.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.forum.util.PageUtil;

public interface IService<T> {
	
	public Serializable save(T o);

    public void delete(T o);

    public int delete(String sql);

    public void update(T o);
    
    public int updateObject(Class<T> c, Map<String, Object> setParaMap, Map<String, Object> valParamMap);
    
    public int updateObject(Class<T>  c, Map<String, Object> setParam,String id, String [] valParam);

    public void saveOrUpdate(T o);

    public T get(Class<T> c, Serializable id);

    public T get(String hql);
    
    public T get(String hql, Map<String, Object> params);

    public List<T> query(String hql);
    
    public List<T> query(String hql, Map<String, Object> params);

    public List<T> query(String hql, int page, int rows);
    
    public List<T> query(String hql, Map<String, Object> params, int page, int rows);

	public List querySql(String sql);

    public List querySql(String sql, int page, int rows);

    public List querySql(String sql, List<String> listKey);

    public List querySql(final String sql,final String retable,Class table);

    //public PageUtil findPageByQuery(int pageSize, int startIndex, String queryString,int type);
    
    public Long count(String hql);

	public Long count(String hql, Map<String, Object> params);

	public int executeHql(String hql);

	public int executeHql(String hql, Map<String, Object> params);
	
	/**
	 * 分页公共方法
	 * @param pageSize 每页数据条数
	 * @param currentPage 当前页
	 * @param queryString HQL语句
	 * @return PageUtil 分页对象
	 */
	public PageUtil findPageByQuery(int pageSize, int currentPage, String queryString);

}
