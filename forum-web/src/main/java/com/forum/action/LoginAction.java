package com.forum.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.forum.model.Person;
import com.forum.model.User;
import com.forum.service.IPersonService;
import com.forum.service.IUserService;
import com.forum.util.MD5Util;

@Controller
public class LoginAction {
	
	@Autowired
	private IUserService userServiceImpl;
	
	@Autowired
	private IPersonService personServiceImpl;
	
	/**
	 * 登陆
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("login")
	public String login(@RequestParam(value="username")String username,
			String password,HttpServletRequest request){
		String resultPageURL = InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
		UsernamePasswordToken utoken=new UsernamePasswordToken(username, MD5Util.calc(password));
		utoken.setRememberMe(true);
		Integer.parseInt("sss");
		Subject currentUser = SecurityUtils.getSubject();
		try {  
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
            System.out.println("对用户[" + username + "]进行登录验证..验证开始");  
            currentUser.login(utoken);  
            System.out.println("对用户[" + username + "]进行登录验证..验证通过");  
            resultPageURL = resultPageURL+"user/query.ac";  
        }catch(UnknownAccountException uae){  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");  
            request.setAttribute("message_login", "未知账户");  
        }catch(IncorrectCredentialsException ice){  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");  
            request.setAttribute("message_login", "密码不正确");  
        }catch(LockedAccountException lae){  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");  
            request.setAttribute("message_login", "账户已锁定");  
        }catch(ExcessiveAttemptsException eae){  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");  
            request.setAttribute("message_login", "用户名或密码错误次数过多");  
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");  
            ae.printStackTrace();  
            request.setAttribute("message_login", "用户名或密码不正确");  
        }  
		if(currentUser.isAuthenticated()){
			 System.out.println("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");  
		}else{
			utoken.clear();
		}
		return resultPageURL;
	}
	
	@RequestMapping(value="logout")
	public String logout(){
		SecurityUtils.getSubject().logout();
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"loginInit.json";
	}
	
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public ModelAndView register(Person person,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		if(person!=null){
			person.setPassword(MD5Util.calc(person.getPassword()));
			person.setDateCreated(new Date());
			person.setDateLastActived(new Date());
			person.setIpCreated(request.getRemoteAddr());
			personServiceImpl.save(person);
			request.getSession().setAttribute("regiInfo", "<b>"+ person.getAccount() +"</b>，注册成功！");
			request.getSession().setMaxInactiveInterval(60);
			mav.setViewName("redirect:/loginInit.ac");
		}else{
			mav.setViewName("registerInit.json");
		}
		
		return mav;
	}
	
	/**
	 * 登陆初始化
	 * @return
	 */
	@RequestMapping(value="loginInit",method = RequestMethod.GET)
	public String loginInit(){
		return "login";
	}
	
	
	/**
	 * 注册初始化
	 * @return
	 */
	@RequestMapping(value="registerInit",method=RequestMethod.GET)
	public String registerInit(){
		return "register";
	}
	
	@RequestMapping(value="checkLoginName")
	public @ResponseBody String checkLoginName(Integer id,String account){
		boolean flag=personServiceImpl.loginNameExist(id, account);
		String str="";
		if(flag){
			str="{\"success\":true,\"info\":\"可以使用\"}";
		}else{
			str="{\"success\":false,\"info\":\"已经存在\"}";
		}
		return str;
	}
	
	
}
