package com.versaplat.uc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping(value="/index2.html")
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping(value="/login.html")
	public String testAjax(){
		return "ajax";
	}
	
    @RequestMapping("/user/addUser.html")  
    public void addUser(String name, String age, HttpServletRequest request,  
            HttpServletResponse response) {  
        // 这里不能用单引号,无效,死的心都有  
        System.out.println("过来了");  
        String result = "{\"name\":\"" + name + "\"}";  
        PrintWriter out = null;  
        System.out.println(result);  
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {  
            out = response.getWriter();  
            out.write(result);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
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
