package com.forum.model;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
* User 实体类
* @data Tue Dec 23 17:38:39 CST 2014
* @author lxb
*/ 
@Entity
@Table(name = "user")
public class User implements Serializable{

	private int userId;//主键
	private String userName;//用户姓名
	private String loginName;//登陆名
	private String loginPass;//登陆密码
	private Date userBirthday;//用户生日
	private byte userAge;//用户年龄
	private String userSex;//用户性别
	private int entId;//

	@Id
	@GeneratedValue
	@Column(name="user_id")
	public int getUserId(){
		return userId;
	}
	public void setUserId(int userId){
		this.userId=userId;
	}

	@Column(name="user_name",length=50)
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName=userName;
	}

	@Column(name="login_name",length=50)
	public String getLoginName(){
		return loginName;
	}
	public void setLoginName(String loginName){
		this.loginName=loginName;
	}

	@Column(name="login_pass",length=30)
	public String getLoginPass(){
		return loginPass;
	}
	public void setLoginPass(String loginPass){
		this.loginPass=loginPass;
	}

	@Column(name="user_birthday")
	public Date getUserBirthday(){
		return userBirthday;
	}
	public void setUserBirthday(Date userBirthday){
		this.userBirthday=userBirthday;
	}

	@Column(name="user_age")
	public byte getUserAge(){
		return userAge;
	}
	public void setUserAge(byte userAge){
		this.userAge=userAge;
	}

	@Column(name="user_sex",length=2)
	public String getUserSex(){
		return userSex;
	}
	public void setUserSex(String userSex){
		this.userSex=userSex;
	}

	@Column(name="ent_id")
	public int getEntId(){
		return entId;
	}
	public void setEntId(int entId){
		this.entId=entId;
	}

}

