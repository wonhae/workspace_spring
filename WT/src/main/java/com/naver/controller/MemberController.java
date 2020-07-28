package com.naver.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.service.MemberService;

@Controller
@RequestMapping("member")  //클래스 단위에서도 붙일 수 있다. member/insert /update 같이~   // /member 이렇게 해도됨 
@SessionAttributes({"login"})  // 7.14 {"login"}모델객체에 키값이 login이 있으면, 모델에 저장됐던 값을 session에 바인딩 시켜라!! 배열이라서{}  spring에서 로그인. 여러개 session에 넣을 수 있다는 것.배열.  SessionAttributes({"id","pw"}) 이런식
public class MemberController {

	
	@Autowired   // or Inject/ 스프링컨테이너가 가지고 있는거 곧바로 받으려면.. 단위테스트할때 본 것 
	private MemberService memberService;  //spring은 인터페이스 기반. 1. 인터페이스 먼저 만들고 그걸 구현하는 2. 그걸 구현하는 클래스 만들기. ->autowired 들어갔기때문에 어딘가 객체 만들어져있음. 이 타입에 맞는 객체가 하나 있어야한다.
	
	
	
	//7.14 로그아웃
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/member/list";
	}
	
	
	// 7.14 구현
	@RequestMapping(value="/loginpost", method = RequestMethod.POST )
	public String loginpost(LoginDTO login, Model model, HttpSession session) {  // 나중에 여기서 작업하지말고 별도의 인터셉터만드세요. 
		
		MemberDTO dto = memberService.loginpost(login);  //db로~~ id,pw 일치한걸 dto로 받는다. id를 통해서 회원인지 안니지 확인, 주인인지여부를 pw를 통해 확인 
		
		if(dto != null) {
			model.addAttribute("login", dto);  //위에 @ SessionAttributes({"login"})를 붙이면 dto는 null값을 가지면 안된다! 
			String path = (String) session.getAttribute("path");
			if(path != null) {
				return "redirect:"+path;
			} 
				return "redirect:/member/list";
			
		}else {
			return "redirect:/member/login";
		}
	}
	
	// 7.14 로그인 구현 
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public void login() {
		//member폴더의 login.jsp로 간다! 
	}
	
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id) {
		
		memberService.delete(id);
		
		return "redirect:/member/list";
	}
	
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(MemberDTO dto) {
		
		memberService.update(dto);
		
		return "redirect:/member/list";
	}
	
	
	//update 에 뿌려준 데이터~ 
	@RequestMapping(value="/updateui/{id}", method=RequestMethod.GET)  //"/update/m001"  //{id}: id값 동적으로 바뀝니다.,데이터입니다 ->받아오기 
	public String updateui(@PathVariable("id") String id, Model model) { //변수를 받아와서  String에 저장한다.  
		MemberDTO dto = memberService.updateui(id);
		model.addAttribute("dto", dto);
		return "member/update"; 
	}
	

	@RequestMapping(value ="/read",method=RequestMethod.GET)  ////    "/read/{id}??
	public void read(String id, Model model) { // String id or MemberDTO dto 
		MemberDTO dto = memberService.read(id);
		model.addAttribute("dto", dto);    			 //void 니까 그냥 read로 간다. 			
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void list(Model model) {
		List<MemberDTO> list = memberService.list();
		//view로 보내줘야함.Model 사용
		model.addAttribute("list", list);  
	}
	
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(MemberDTO dto) { //String id, 이렇게 써도됨
		
		memberService.insert(dto);
		
		return "redirect:/member/list";  //입력후에는 list.do로 가서 db들렸다.  // jsp로 갈 수 있는 이유 : /WEB-INF/views/ + .jsp 덕분에!
	}
	
	//주소창에 치면 나오게
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert() {
		return "member/insert";  //return에 member 폴더 만든 이유는 관리하기 위해서 // 입력할 때  return "member/insert"와 상관없이 위에 requestmapping해놓은것 땜에  member/insert 라고 주소창에 
	}
	
}
