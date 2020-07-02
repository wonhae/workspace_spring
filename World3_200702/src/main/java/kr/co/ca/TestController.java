package kr.co.ca;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TestController {

	
	@RequestMapping("t5")
	public void t5(@ModelAttribute("d4") String str) {
		System.out.println(str);
	}
	
	@RequestMapping("t4")
	public String t4(RedirectAttributes rtts) {
		rtts.addFlashAttribute("d4", "d4who");
		return "redirect:/t5";
	}
	
	
	@RequestMapping("t2")
	public String t2(Model model) {
		model.addAttribute("d2", "d2what");
		return "redirect:/t3";
	}
	
	@RequestMapping("t1")
	public String t1(Model model) {
		model.addAttribute("dd", "dddddd");
		return "t1";
	}
	
}
