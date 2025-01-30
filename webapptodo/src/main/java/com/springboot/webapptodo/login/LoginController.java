package com.springboot.webapptodo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
	

	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String welcomePage(@RequestParam String name,@RequestParam String password,ModelMap model){
		
		
		return "welcome";
		
	}
}
