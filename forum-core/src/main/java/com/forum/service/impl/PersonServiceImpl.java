package com.forum.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.dao.IDao;
import com.forum.model.Person;
import com.forum.service.IPersonService;

@Service
public class PersonServiceImpl extends ServiceImpl<Person> implements IPersonService {
	
	@Autowired
	private IDao<Person> dao;
	
	public List<Person> queryByHql(String hql){
		return dao.query(hql);
	}

	@Override
	public boolean loginNameExist(Integer id,String loginName) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("account", loginName);
		String hql="from Person where lower(account)= lower(:account)";
		if(id!=null){
			hql+=" and id<>:id";
			map.put("id", id);
		}
		Person person=dao.get(hql, map);
		if(person==null){
			return true;
		}
		return false;
	}

	@Override
	public Person login(String loginName, String password) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("account", loginName);
		map.put("password", password);
		String hql="from Person where lower(account)=lower(:account) and password=:password";
		return dao.get(hql, map);
	}

	@Override
	public Person findById(Integer id) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		String hql="from Person where id=:id";
		List<Person> lists=dao.query(hql, map);
		if(lists!=null){
			return lists.get(0);
		}
		return null;
	}

}
