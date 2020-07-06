package kr.co.ca;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.MemberDTO;

@Controller
public class TestController {
	
	@ RequestMapping(value="rt4", method = RequestMethod.GET)
	public void rt4(Model model) {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		list.add(new MemberDTO("m001", "kim", 33));
		list.add(new MemberDTO("m002", "lee", 34));
		list.add(new MemberDTO("m003", "park", 35));
		model.addAttribute("list", list); // 일반컨트롤러에서 작업했던 것 중 <% 넣었던것. 거기에 안넣고 여기에 넣음 
	}
	
	@RequestMapping(value="rt3", method = RequestMethod.GET)
	public void rt3() {
		
	}
	
	@RequestMapping(value="rt2", method= RequestMethod.GET)
	public void rt2() {
		
	}
	
	@RequestMapping(value="/rt1")
	public void rt1() {
		
	}
	
}
