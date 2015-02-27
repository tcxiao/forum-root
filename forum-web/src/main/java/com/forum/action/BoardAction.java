package com.forum.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/board")
public class BoardAction {
	
	@RequestMapping(value="indexInit")
	public String indexInit(){
		System.out.println("======>面板首页");
		return "/board/index";
	}

}
