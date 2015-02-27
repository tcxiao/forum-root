package com.forum.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forum.util.PageUtil;

@Repository("daoImpl")
public class DaoImpl<T> implements IDao<T> {
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		//return this.sessionFactory.openSession();
		return this.sessionFactory.getCurrentSession();
	}
	
	////////////////////////////
	
	public Serializable save(T o) {
		//log.info("save T:"+o+"");
        try{
            return this.getCurrentSession().save(o);
        }catch(RuntimeException re){
            throw re;
        }
	}

	public void saveOrUpdate(T o) {
		//log.info("saveOrUpdate T:"+o+"");
        try{
            this.getCurrentSession().saveOrUpdate(o);
        }catch(RuntimeException re){
            throw re;
        }
	}

	public void update(T o) {
		//log.info("update T:"+o+"");
        try{
            this.getCurrentSession().update(o);
        }catch(RuntimeException re){
            throw re;
        }
	}
	
	public int updateObject(Class<T> c, Map<String, Object> setParam, Map<String, Object> valParam){
		//log.info("update class:"+c.getName());
		try{
			String setKey = "";
			String setVal = "";
			if(setParam!=null && !setParam.isEmpty()){
				for (String key : setParam.keySet()) {
					setKey += key + "='"+setParam.get(key)+"' ";
				}
			}
			if(valParam!=null && !valParam.isEmpty()){
				for (String key : valParam.keySet()) {
					setVal += key + "='"+valParam.get(key)+"' ";
				}
			}
			if(StringUtils.isBlank(setKey) && StringUtils.isBlank(setVal)) return -1;
			String hql = "update "+ c.getName() + " set " + setKey + " where " + setVal;
			Query q = this.getCurrentSession().createQuery(hql);
			return q.executeUpdate();
		}catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public int updateObject(Class<T> c, Map<String, Object> setParam,String id, String [] valParam){
		//log.info("update class:"+c.getName()+" id:"+id);
		try{
			if(StringUtils.isBlank(id)) id= "ID";
			String setKey = "";
			String setVal = "";
			if(setParam!=null && !setParam.isEmpty()){
				for (String key : setParam.keySet()) {
					setKey += key + "='"+setParam.get(key)+"' ";
				}
			}
			if(valParam!=null){
				for (int i=0; i<valParam.length; i++) {
					if(i==(valParam.length-1)){
						setVal += id + "='"+valParam[i]+"' ";	
					}else{
						setVal += id + "='"+valParam[i]+"' or ";						
					}
				}
			}
			if(StringUtils.isBlank(setKey) && StringUtils.isBlank(setVal)) return -1;
			String hql = "update "+ c.getName() + " set " + setKey + " where " + setVal;
			Query q = this.getCurrentSession().createQuery(hql);
			return q.executeUpdate();
		}catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}

	public T get(Class<T> c, Serializable id) {
		//log.info("get T:"+c+", id:"+id);
        try{
            T t=(T) this.getCurrentSession().get(c, id);
            return t;
        }catch(RuntimeException re){
            throw re;
        }
	}

	public T get(String hql) {
		//log.info("get hql:"+hql);
        try{
            Query query = this.getCurrentSession().createQuery(hql);
            List<T> list = query.list();
            if(list==null || list.size()==0){
                return null;
            }else{
                return list.get(0);
            }
        }catch(RuntimeException re){
            throw re;
        }
	}
	
	public T get(String hql, Map<String, Object> params){
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	public List<T> query(String hql) {
		//log.info("query hql:"+hql+"");
        try{
        	Query query = this.getCurrentSession().createQuery(hql);
            List<T> list = query.list();
            if(list!=null && list.size()>0){
                return list;
            }

            return null;
        }catch(RuntimeException re){
            throw re;
        }
	}
	
	public List<T> query(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	public List<T> query(final String hql, final int page, final int rows) {
		//log.info("query hql:"+hql+", page:"+page+", rows:");
        try{
            Query query = this.getCurrentSession().createQuery(hql);
            query.setFirstResult(((page-1)*rows));
            query.setMaxResults(rows);
            List<T> list = query.list();
            if(list!=null && list.size()>0){
                return list;
            }

            return null;
        }catch(RuntimeException re){
            throw re;
        }
	}
	
	public List<T> query(String hql, Map<String, Object> params, int page, int rows){
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public List querySql(String sql) {
		//log.info("querySql sql: "+ sql);
        try{
        	Query sqlQuery = this.getCurrentSession().createSQLQuery(sql);
            List list = sqlQuery.list();
            if(list!=null && list.size()>0){
                return list;
            }

            return null;
        }catch(RuntimeException re){
            throw re;
        }
	}

	/**
     *  sysBase查询语句为: select id, name from table , 不能直接 select * from table 不然将报非法字符错误
     *  返回的是数组对象(object[])
     * @param sql
     * @param page 页数
     * @param rows 条数
     * @return
     */
	public List querySql(String sql, int page, int rows) {
		//log.info("querySql page, sql: "+ sql);
        try{
        	SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        	sqlQuery.setFirstResult((page-1)*rows);
            sqlQuery.setMaxResults(rows);
            List list = sqlQuery.list();
            if(list!=null && list.size()>0){
                return list;
            }

            return null;
        }catch(RuntimeException re){
            throw re;
        }
	}

	/**
    *
    * @param sql
    * @param listKey 需要查询的字段. 需要查询的字段, 字段名必须全部为大写, 这里全部转换成字符串形式查询出来的, 不能转换的请注意
    * @return
    */
	public List querySql(String sql, List<String> listKey) {
		//log.info("querySql by the listKey, sql:"+sql+"");
        try{
        	SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
            if(listKey!=null && listKey.size()>0){
                for(Object key: listKey){
                    sqlQuery.addScalar(key.toString(), StringType.INSTANCE);
                }
            }
            List list = sqlQuery.list();
            if(list!=null && list.size()>0){
                return list;
            }

            return null;
        }catch(RuntimeException re){
            throw re;
        }
	}

	/**
     * 实现本地查询并可根据属性名取值
     * @param sql
     * @param retable
     * @param tablename
     * @return
     */
	public List querySql(String sql, String retable, Class table) {
		//log.info("querySql by sql and classTable, sql: "+ sql);
        try{
        	SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        	sqlQuery.addEntity(table);
            List list = sqlQuery.list();
            if(list!=null && list.size()>0){
                return list;
            }

            return null;
        }catch(RuntimeException re){
            throw re;
        }
	}
	
	/**
     * 分页公共方法
     * @param pageSize 每页数据条数
     * @param startIndex 当前页的首条数据的编号
     * @param queryString
     * @param type 1表示Hibernate查询，2表示本地查询
     * @return
     */
	public PageUtil findPageByQuery(int pageSize, int startIndex, String queryString, int type) {
		//log.info("findPageByQuery sql: "+ queryString);
        try{
            
            Query query = null;
//            SQLQuery sqlQuery = null;
            if(type == 1){
              query = this.getCurrentSession().createQuery(queryString);
            }
//            if(type == 2){
//                sqlQuery = this.getCurrentSession().createSQLQuery(queryString);
//            }
            int totalCount = 0;
            totalCount = query.list().size();
            query.setFirstResult(startIndex);
            query.setMaxResults(pageSize);
            List items = query.list();
            PageUtil pageUtil = new PageUtil(items, totalCount, pageSize, startIndex);;

            if(null != pageUtil) {
                return pageUtil;
            }
            return null;
        }catch(RuntimeException re) {
            throw re;
        }
	}
	
	/**
	 * 分页公共方法
	 * @param pageSize 每页数据条数
	 * @param currentPage 当前页
	 * @param queryString HQL语句
	 * @return PageUtil 分页对象
	 */
	public PageUtil findPageByQuery(int pageSize, int currentPage, String queryString) {
		//log.info("findPageByQuery sql: "+ queryString);
		try{
			if(pageSize==0) pageSize = PageUtil.PAGESIZE;
			if(currentPage==0) currentPage = 1;
			
			Query query = getCurrentSession().createQuery(queryString);
			
			int totalCount = 0;
			int startIndex = (currentPage-1)*pageSize;
			totalCount = query.list().size();
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			List items = query.list();
			PageUtil pageUtil = new PageUtil(items, totalCount, pageSize, startIndex);;
			
			if(null != pageUtil) {
				return pageUtil;
			}
			return null;
		}catch(RuntimeException re) {
			throw re;
		}
	}

	
	public void delete(T o) {
		//log.info("delete T:"+o+"");
        try{
            this.getCurrentSession().delete(o);
        }catch(RuntimeException re){
            throw re;
        }
	}

	public int delete(String sql) {
		//log.info("delete for sql:"+ sql);
        try{
            int count = 0;
            Query query = this.getCurrentSession().createSQLQuery(sql);
            count = query.executeUpdate();

            return count;
        }catch(RuntimeException re){
            throw re;
        }
	}
	
	@Override
	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public int executeHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

}
