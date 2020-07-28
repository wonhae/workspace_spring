package com.naver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auth")
public class AuthController {

//	@RequestMapping(value="/callback", method = RequestMethod.GET)
//	public void callback() {		
//	}
	
	@RequestMapping(value="/naver", method = RequestMethod.GET)
	public void naver() {		
	}
	
	@RequestMapping(value="/naver_callback", method = RequestMethod.GET)
	public void callback() {		
	}
	
	@RequestMapping(value="/kakao", method = RequestMethod.GET)
	public void kakao() {		
	}
	
	@RequestMapping(value="/kakao_logout", method = RequestMethod.GET)
	public void kakao_logout() {		
	}
	
	@RequestMapping(value="/commonlogin", method = RequestMethod.GET)
	public void commonlogin() {		
	}
	
	@ResponseBody
	@RequestMapping(value="/kakao/callback", method = RequestMethod.GET)
	public String kakao_callback(String code) {
		//
		
		
		return "/auth/kakao_callback"+code;
	}
}
