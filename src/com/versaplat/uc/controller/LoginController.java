package com.versaplat.uc.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.versaplat.uc.entity.User;
import com.versaplat.uc.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/index.html")
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping(value="/loginCheck.html")
	public ModelAndView loginCheck(HttpServletRequest request, String username, String password){
		boolean isValidUser = userService.hasMatchUser(username, password);
		if(!isValidUser){
			return new ModelAndView("login","error","用户名或密码错误！");
		} else {
			User user = userService.findUserByName(username);
			userService.loginSuccess(user.getUser_id()+"", request.getRemoteAddr(), new Date()+"");
			request.getSession().setAttribute("user", user);
			return new ModelAndView("main");
			
		}
		
		
		
		
		
	}
}