package com.versaplat.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {
	
	@RequestMapping(value="/index.*")
	public String  indexPage(){
		return "front/index";
	}
}
