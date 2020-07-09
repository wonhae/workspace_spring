package com.naver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.util.Utils;

@Controller
public class MyController {
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public void test(String name) {  //예전엔 HttpServletRequest request 이렇게 했음. ->받을땐 request.getparameter(dd)
//		name = Utils.toKor(name);
		System.out.println(name);
	}
	
	
	@RequestMapping(value = "good", method = RequestMethod.GET)
	public void good(Model model) { //jsp에 넘겨준때 전에는 request or appli or session.setattribute 
		//model = request와 유사하다. 
		model.addAttribute("test", "test입니다");  // good.jsp 만들어서 $ {test} 입력 
		
	}
	
	@RequestMapping(value = "world", method= RequestMethod.GET) //post방식안하는 이유" form tag 만들고 그래야해서 
	public void world() {  // void일 경우 uri 가 view 페이지의 이름이 된다.
		
	}
	
	@RequestMapping(value = "hello", method = RequestMethod.GET) // /넣어도되고 안넣어도되고. get방식 //hello에 대해 서비스 해준다
    //uri 와 mvc 핸들러의 이름은 같다. 
	public String hello() {
		return "hello";   //반환형의 의미 = view page 의 의미   ->이상태에서 바로 실행시 404에러 ->hello.jsp만들것
	}
}
