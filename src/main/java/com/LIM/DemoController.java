package com.LIM;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
	
	@RequestMapping("/")
	public String home() {
		//System.out.println("home() 호출됨 / Nook Inc 메인화면");
		return "index.html";
	}

}
