package kr.co.restcontroller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.domain.ReplyVO;
import kr.co.service.ReplyService;

@RestController
public class MyRestController {

	@Inject
	private ReplyService rService; //db작업 할거니까!
	
	@RequestMapping(value="/replies", method=RequestMethod.DELETE)
	public String delete(@RequestBody ReplyVO vo) {
		int i = rService.delete(vo.getRno());
		if(i == 1) {
			return "success";
		}
		return "fail";
	}
	
	@RequestMapping(value="/replies/{rno}" ,method = RequestMethod.PUT)  //수정
	public String update(@PathVariable("rno") int rno,@RequestBody ReplyVO vo){
		vo.setRno(rno);  //pathvariable 이용하면 이 단계 필요하다. 
		int i = rService.update(vo);
		if(i ==1) {
			return "success";
		}
		return "fail";
	}
	
	@RequestMapping(value="/replies/all/{bno}", method = RequestMethod.GET)  //url 주의깊게 보세요! 이런식으로 통신해야함! 
	public List<ReplyVO> list(@PathVariable("bno") int bno){  //////여기서왜 requestbody 안해줬지??
		List<ReplyVO> list = rService.list(bno);
		return list;
	}
	
	@RequestMapping(value="/replies", method = RequestMethod.POST)
	public String insert(@RequestBody ReplyVO vo) {
		int i =  rService.insert(vo);
		if(i == 1) {
			return "success";
		} else {
			return "fail";
		}
	}
	
}
