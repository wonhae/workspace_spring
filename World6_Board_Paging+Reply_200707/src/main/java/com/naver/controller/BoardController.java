package com.naver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	//검색하면 나오는 별도의 창 가도록! 7/6일 
	@RequestMapping(value="/searchlist", method = RequestMethod.GET)  //pageto를 상속하면서 searchType=title&keyword=12e가 추가되어있는 검색to를 만들어주면 검색페이징처리 가능!!
	public String searchlist(Model model, String searchType, String keyword) { //void 처리해도 됐음! //String searchType, String keyword 이거자체를 map으로 받아도됨(컨트롤러에선 그냥 string으로하는편)
		
		List<BoardVO> list = bService.searchlist(searchType,keyword);  //dao impl 에는 null 대신 딱 하나만 넣을 수 있다! 우리는 2개 가지고 가려고하는데 이런상황에서는  map! 
		
		model.addAttribute("list", list);
		//System.out.println(list);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		
		return "board/searchlist";
	}
	
	@RequestMapping(value="delete/{bno}", method = RequestMethod.GET)
	public String delete(@PathVariable("bno") int bno) {
		
		bService.delete(bno);
		
		return "redirect:/board/list";
	}
	
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(BoardVO vo) {
		
		bService.update(vo);
		System.out.println("::::::::::::::");
		return "redirect:/board/read/" + vo.getBno(); //몇번글을 읽어오겠다!!
	}
	
	@RequestMapping(value="/update/{bno}", method=RequestMethod.GET)
	public String update(@PathVariable("bno") int bno, Model model) {
		BoardVO vo = bService.updateui(bno);  //bno 가 primarykey 이기 때문에 데이터는 1개 나옴 
		model.addAttribute("vo", vo);
		return "/board/update";
	}
	
	
	@RequestMapping(value="/read/{bno}", method = RequestMethod.GET)  //pathvariable 사용시  ->반환형string으로 
	public String read(@PathVariable("bno") int bno , Model model) {
		BoardVO vo = bService.read(bno); //serviceimpl에서 조회수 증가시키는 메서드 만들 것! 
		model.addAttribute("vo", vo);
		return "/board/read";
	}
	
	//페이징처리 7월6일 
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void list(Model model, String curPage){
		//parameter에서 int curpage 하고  System.out.println(curpage); ->에러
		int page = -1;
		if(curPage == null) {
			page = 1;
		}else {
			page = Integer.parseInt(curPage);
		}
		
		PageTO<BoardVO> to = new PageTO<BoardVO>(page);				
		
		//PageTO<BoardVO> pt = bService.list(to);
		to = bService.list(to);  //기존대로 List<BoardVO> list 로 그냥 받으면 안됨! 위에 아래 둘중에 하나 
		
		model.addAttribute("to", to);	//아래것 안쓰고 이거 하나만 할거면 list.jsp에서 {to.list }로 바꿀것! 
		model.addAttribute("list", to.getList());	//list.jsp에서 {list로 그냥 두고 싶으면 } 이 코드도 추가해주기! 
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public String insert(BoardVO vo) {

//		for (int i = 0; i < 20; i ++) {
//			vo.setTitle(i+"e");
//			bService.insert(vo);
//		}
		
		bService.insert(vo);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.GET) //mvc 핸들러!//입력화면 갔다옴
	public void insert() {		
	}
	
}
