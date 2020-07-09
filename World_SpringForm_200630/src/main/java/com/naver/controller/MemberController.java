package com.naver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.MemberDTO;
import kr.co.service.MemberService;

@Controller
@RequestMapping("member")  //클래스 단위에서도 붙일 수 있다. member/insert /update 같이~ 
public class MemberController {

	
	@Autowired   // or Inject/ 스프링컨테이너가 가지고 있는거 곧바로 받으려면.. 단위테스트할때 본 것 
	private MemberService memberService;  //spring은 인터페이스 기반. 1. 인터페이스 먼저 만들고 그걸 구현하는 2. 그걸 구현하는 클래스 만들기. ->autowired 들어갔기때문에 어딘가 객체 만들어져있음. 이 타입에 맞는 객체가 하나 있어야한다.
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void list(Model model) {
		List<MemberDTO> list = memberService.list();
		//view로 보내줘야함.Model 사용
		model.addAttribute("list", list);
	}
	
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(MemberDTO dto) { //String id, 이렇게 써도됨
		
		memberService.insert(dto);
		
		return "redirect:/member/list";  //입력후에는 list.do로 가서 db들렸다. 
	}
	
	//주소창에 치면 나오게
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert() {
		return "member/insert";  //return에 member 폴더 만든 이유는 관리하기 위해서 // 입력할 때  return "member/insert"와 상관없이 위에 requestmapping해놓은것 땜에  member/insert 라고 주소창에 
	}
	
}
