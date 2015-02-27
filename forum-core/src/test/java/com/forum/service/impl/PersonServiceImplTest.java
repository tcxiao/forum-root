package com.forum.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.forum.model.Person;
import com.forum.model.User;
import com.forum.service.IPersonService;
import com.forum.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:applicationContext.xml")
public class PersonServiceImplTest {
	
	@Autowired
	private IPersonService personServiceImpl;
	
	@Autowired
	private IUserService userServiceImpl;
	
	@Test
	public void getTest(){
		/*List<User> lists=userServiceImpl.query("from User");
		System.out.println(lists.size());*/
	}

}
