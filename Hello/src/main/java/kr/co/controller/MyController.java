package kr.co.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {
	@RequestMapping(value = "/show", method=RequestMethod.GET)
	public String show() {
		return "show";
	}
	
//	mvc 컨트롤러  web-inf/views/insert.jsp
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Model model, String name) { // HttpServletRequest request 할필요 없다 스프링에서
		// 임시방편 한글나오게 하기위해
		name = toKor(name);

		model.addAttribute("name", name);
		return "insert";
	}

	private String toKor(String name) {
		try {
			return new String(name.getBytes("8859_1"), "UTF-8"); // 넘겨받는다.
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	
}
