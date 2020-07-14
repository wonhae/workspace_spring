package kr.co.ca;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value="/intertest2" , method = RequestMethod.GET)//7.14
	public void intertest2(Model model) {
		model.addAttribute("test", "test");
	}
	
	
	@RequestMapping(value="/intertest1") //7.14
	public void intertest1() {
		System.out.println("intertest1>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	
	@RequestMapping("test5")
	public void test5(@ModelAttribute("str2") String mm) { //@ModelAttribute("str2") 하면 여기서 곧바로 사용가능,jsp 에서도 바로 가능   //String str2, Model model ,  System.out.println(str2);->null 나옴 
		System.out.println(mm); //void니까 test5.jsp로간다!! 
		
	}
	//**비교
	@RequestMapping("test4")
	public String test4(RedirectAttributes rtts ) { //주소창에 깔끔. test5로 바뀜  //2번째 바인딩하는 방법 : RedirectAttributes -addFlashAttribute
		rtts.addFlashAttribute("str2", "who"); //주소창에 보이면 안된다. 
		return "redirect:/test5"; //test5핸들러로 간다! 
	}
	
	
	//**비교
	@RequestMapping("test2")
	public String test2(Model model) {
		model.addAttribute("str1", "what"); //queryString 형태! 
		return "redirect:/test3"; // 주소창에  test3?str1=what//감추고 싶다면...
	}
	
	
	@RequestMapping("test1")
	public String test1(Model model) {
		model.addAttribute("msg", "~~~~~~~");
		return "test1"; //주소창에 깔끔하다.  "test1"일 경우 dispatcher 방식이라고 이해해도되긴한다.  이경우 RedirectAttributes안쓴다. 
	} 
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
