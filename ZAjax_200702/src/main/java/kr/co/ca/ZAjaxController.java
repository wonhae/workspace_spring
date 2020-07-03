package kr.co.ca;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.domain.MemberDTO;

@Controller
public class ZAjaxController {
	
	

	@ResponseBody
	@RequestMapping(value="a2", method = RequestMethod.POST)
	public MemberDTO a2(MemberDTO dto ) {
		
		return dto;
	}
	
	@RequestMapping(value="a2", method = RequestMethod.GET)
	public void a2() {
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="a1", method = RequestMethod.POST)
	public String[] a1(String[] thisisarray ) {
		for(String x : thisisarray) {
			System.out.println(x);
		}
		return thisisarray;
	}
	
	@RequestMapping(value="a1", method = RequestMethod.GET)
	public void a1() {
		
	}
}
