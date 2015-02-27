package com.forum.service;

import java.util.List;

import com.forum.model.Person;

public interface IPersonService extends IService<Person> {
	
	public List<Person> queryByHql(String hql);
	
	/**
	 * 验证用户名是否存在,支持编辑的情况下验证
	 * @param loginName
	 * @return
	 */
	public boolean loginNameExist(Integer id,String loginName);
	
	/**
	 * 登陆
	 * @param loginName
	 * @param password
	 * @return
	 */
	public Person login(String loginName,String password);
	
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 */
	public Person findById(Integer id);
	
}
