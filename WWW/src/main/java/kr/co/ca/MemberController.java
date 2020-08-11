package kr.co.ca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.MemberVO;
import kr.co.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {  //h1234에 작업 

	@Autowired
	private MemberService ms;
	
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public void read(String id, Model model) {
		//여기부터!  스프링 3,4회차 ! 

	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public void list(Model model) {
		List<MemberVO> list = ms.list();
		model.addAttribute("list", list);
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public String insert(MemberVO vo) {
		ms.insert(vo);
		return "redirect:/member/list";
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.GET)
	public String insert() {	
		return "member/insert";
	}
	
}
