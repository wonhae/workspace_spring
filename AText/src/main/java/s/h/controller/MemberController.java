package s.h.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import s.h.domain.LoginDTO;
import s.h.service.MemberService;

@Controller
@RequestMapping("member")
@SessionAttributes({"login"})
public class MemberController {

	@Inject
	private MemberService mService;
	
	@RequestMapping(value="/loginpost", method = RequestMethod.POST)
	public String loginpost(LoginDTO dto) {
		
		return "redirect:/member/list";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public void login() {		
	}
	
	
}
