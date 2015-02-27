package com.forum.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.forum.model.Person;
import com.forum.service.IPersonService;

public class MyRealm extends AuthorizingRealm {
	
	 @Autowired
	 private IPersonService personService;
	
	/** 
     * 为当前登录的Subject授予角色和权限 
     * @see 经测试:本例中该方法的调用时机为需授权资源被访问时 
     * @see 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache 
     * @see 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache 
     * @see 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache 
     */  
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		String userName=String.valueOf(super.getAuthenticationCacheKey(principals));
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		if(userName!=null&&"admin".equals(userName)){
			//添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色    
            simpleAuthorInfo.addRole("admin");  
            //添加权限  
            simpleAuthorInfo.addStringPermission("admin:manage");  
            System.out.println("已为用户[admin]赋予了[admin]角色和[admin:manage]权限");
            return simpleAuthorInfo;
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken uToken=(UsernamePasswordToken)token;
		Person person=personService.login(uToken.getUsername(), String.valueOf(uToken.getPassword()));
		if(person!=null){
			AuthenticationInfo authInfo=new SimpleAuthenticationInfo(person.getAccount(), 
					person.getPassword(), getName());
			this.setSession("personInfo", person);
			return authInfo;
		}
		return null;
	}
	
	/** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    } 

}
