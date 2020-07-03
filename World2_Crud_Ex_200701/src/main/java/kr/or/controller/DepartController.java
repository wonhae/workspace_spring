package kr.or.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.DepartDTO;
import kr.co.service.DepartService;

@Controller  //<context:component-scan base-package="kr.or.controller" /만들것
@RequestMapping("depart")
public class DepartController { //이것도 imple ~ controller 하는경우도 있음 (어노테이션 안써도됨)
	
	@Inject //db 작업 할것이기 때문에 DepartService 필용하다. 
	private DepartService dService;
	
	
	//delete
	@RequestMapping(value="/delete/{did}", method = RequestMethod.GET)
	public String delete(@PathVariable("did") String did) {
		dService.delete(did);
		return "redirect:/depart/list";
	}
	
	//update
	@RequestMapping(value="/update", method= RequestMethod.POST)
	public String update(DepartDTO dto) {
		dService.update(dto);
		return "redirect:/depart/list";  //controller로 다시 가게 하는것! 
	}
	
	//updateui (db들려서  update.jsp 에 뿌려줄 것! )
	@RequestMapping(value="/update/{did}")     
	public String updateui(@PathVariable("did") String did, Model model) { //model객체는 습적으로 칠 것. jsp에뿌려줘야 하니까 바인딩할 것 필요 
		DepartDTO dto =  dService.updateui(did);
		model.addAttribute("dto", dto);
		return "/depart/update";
	}
	 
	//read
	@RequestMapping(value="/read/{did}")
	public String read(@PathVariable("did") String did, Model model) {
		DepartDTO dto = dService.read(did);  //db에서 읽어와야함
		model.addAttribute("dto", dto);
		return "/depart/read";  //디스패쳐 방식! 
	}
	
	//list
	@RequestMapping(value="/list")
	public void list(Model model) {  //->void 이지만 view 값은 depart/list.jsp
		List<DepartDTO> list = dService.list(); //db들렸다가 여기까지 온다. 
		model.addAttribute("list", list); //para 하나짜리도있다. value : 이것의 키는 List 첫글자 소문자로 쓴다.
	}
	
	//insert
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public String insert(DepartDTO dto) {
		//data 전달받았나 확인하는 방법 
		System.out.println(dto.toString());
		System.out.print(dto.getDid() + " : ");
		System.out.print(dto.getDname());
		
		dService.insert(dto);
		
		return "redirect:/depart/list";  
	}
	
	//껍데기! insert.jsp로 가는 것! 
	@RequestMapping("/insert") //"/insert"은 get방식이다! 
	public String insert() {
		return "depart/insert"; // insert.jsp 로감~  
	}
	
	
}
