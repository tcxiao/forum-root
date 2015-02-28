package com.forum.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.forum.model.Person;
import com.forum.service.IPersonService;
import com.forum.util.PageUtil;
import com.forum.util.Pagination;

@Controller
@RequestMapping(value="/user")
public class UserAction extends BaseAction{
	
	@Autowired
	private IPersonService personServiceImpl;
	
	@RequestMapping(value="query")
	public ModelAndView query(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView mv=new ModelAndView();
		PageUtil pageUtil=personServiceImpl.findPageByQuery(5, 0, "from Person");
		Pagination pagination=new Pagination(request, response);
		pagination.setRecordCount(pageUtil.getPageCount());
		mv.addObject("persons", pageUtil.getItems());
		mv.addObject("pagination", pagination);
		mv.setViewName("user/list");
		return mv;
	}
	
	@RequestMapping(value="view")
	public ModelAndView view(Integer id){
		ModelAndView mv=new ModelAndView();
		mv.addObject("person", personServiceImpl.findById(id));
		mv.setViewName("user/view");
		return mv;
	}
	
	@RequestMapping(value="addInit")
	public ModelAndView addInit(Integer id){
		ModelAndView mv=new ModelAndView();
		if(id!=null){
			mv.addObject("person",personServiceImpl.findById(id));
		}
		mv.setViewName("user/newOrEdit");
		return mv;
	}
	
	@RequestMapping("addOrModify")
	public @ResponseBody String addOrModify(Person person,HttpServletRequest request){
		boolean flag=true;
		String msg="修改成功";
		Person temp=null;
		if(person.getId()==null){
			temp=new Person();
			temp.setIpCreated(request.getRemoteAddr());
			temp.setDateLastActived(new Date());
			temp.setDateCreated(new Date());
			msg="添加成功";
		}else{
			temp=personServiceImpl.findById(person.getId());
		}
		temp.setAccount(person.getAccount());
		temp.setBirthday(person.getBirthday());
		temp.setEmail(person.getEmail());
		temp.setName(person.getName());
		temp.setSex(person.getSex());
		temp.setDateLastActived(new Date());
		try{
			personServiceImpl.saveOrUpdate(temp);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag=false;
			msg="操作失败";
		}
		return "{\"success\":"+ flag +",\"msg\":\""+ msg +"\"}";
	}
	

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete")
	public @ResponseBody String delete(String ids){
		boolean flag=true;
		String msg="删除成功";
		if(StringUtils.isNotBlank(ids)){
			try{
				String idStrs[]=ids.split(",");
				for(String info:idStrs){
					Person person=personServiceImpl.findById(Integer.parseInt(info));
					personServiceImpl.delete(person);
				}
			}catch (Exception e) {
				// TODO: handle exception
				flag=false;
				msg="删除失败";
			}
		}
		return "{\"success\":"+flag+",\"msg\":\""+ msg +"\"}";
	}

}
