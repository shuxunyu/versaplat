package com.my.versaplat.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.versaplat.service.HomeService;

@Controller
public class HomeController {
	
	private static Logger log = Logger.getLogger(HomeController.class);
	
	@Autowired
	private HomeService homeService;
	/*** 
     * 首页 返回至/page/home.jsp页面 
     * @return 
     */  
    @RequestMapping("index")  
    public ModelAndView index(){  
        //创建模型跟视图，用于渲染页面。并且指定要返回的页面为home页面  
        ModelAndView mav = new ModelAndView("home");
        Map param = new HashMap();
        List list = homeService.getCategoryList(param);
        return mav;  
    }  
    
    /*** 
     * 用户登陆 
     * <p>注解配置，只允许POST提交到该方法 
     * @param username 
     * @param password 
     * @return 
     */  
    @RequestMapping(value="login",method=RequestMethod.POST)  
    public ModelAndView login(String username,String password){  
        //验证传递过来的参数是否正确，否则返回到登陆页面。  
        if(this.checkParams(new String[]{username,password})){  
            //指定要返回的页面为succ.jsp  
            ModelAndView mav = new ModelAndView("succ");  
            //将参数返回给页面  
            mav.addObject("username",username);  
            mav.addObject("password", password);  
            return mav;  
        }  
        return new ModelAndView("home");  
    }  
      
    /*** 
     * 验证参数是否为空 
     * @param params 
     * @return 
     */  
    private boolean checkParams(String[] params){  
        for(String param:params){  
            if(param==""||param==null||param.isEmpty()){  
                return false;  
            }  
        }  
        return true;  
    }  
}
