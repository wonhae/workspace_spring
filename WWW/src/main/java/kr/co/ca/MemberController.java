package kr.co.ca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.MemberVO;
import kr.co.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService ms;
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public void insert(MemberVO vo) {
		ms.insert(vo);
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.GET)
	public void insert() {	
	}
	
}
