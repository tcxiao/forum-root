package com.forum.model;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
* Person 实体类
* @data Wed Dec 24 22:25:33 CST 2014
* @author lxb
*/ 
@Entity
@Table(name = "person")
public class Person extends BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1226765146594857553L;
	
	@Column(name="account",length=255)
	private String account;//
	
	@Column(name="birthday",length=255)
	private String birthday;//
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name="dateLastActived")
	private Date dateLastActived;//
	
	@Column(name="email",length=255)
	private String email;//
	
	@Column(name="ipCreated",length=255)
	private String ipCreated;//
	
	@Column(name="name",length=255)
	private String name;//
	
	@Column(name="password",length=255)
	private String password;//
	
	@Column(name="sex",length=255)
	private String sex;//
	
	@ManyToMany(mappedBy = "administrators")
	private Set<Board> boardsAdministrated = new HashSet<Board>();

	public Set<Board> getBoardsAdministrated() {
		return boardsAdministrated;
	}
	public void setBoardsAdministrated(Set<Board> boardsAdministrated) {
		this.boardsAdministrated = boardsAdministrated;
	}
	
	
	public String getAccount(){
		return account;
	}
	public void setAccount(String account){
		this.account=account;
	}

	public String getBirthday(){
		return birthday;
	}
	public void setBirthday(String birthday){
		this.birthday=birthday;
	}

	public Date getDateLastActived(){
		return dateLastActived;
	}
	public void setDateLastActived(Date dateLastActived){
		this.dateLastActived=dateLastActived;
	}

	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email=email;
	}

	public String getIpCreated(){
		return ipCreated;
	}
	public void setIpCreated(String ipCreated){
		this.ipCreated=ipCreated;
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}

	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password=password;
	}

	public String getSex(){
		return sex;
	}
	public void setSex(String sex){
		this.sex=sex;
	}

}

