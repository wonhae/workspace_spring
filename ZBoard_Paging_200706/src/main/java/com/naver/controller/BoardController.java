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
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void list(Model model, String curPage){
		int page = -1;
		if(curPage == null) {
			page = 1;
		} else {
			page = Integer.parseInt(curPage);
		}
		
		PageTO<BoardVO> pageTo = new PageTO<BoardVO>(page);
		
		pageTo= bService.list(pageTo);
		
		model.addAttribute("list", pageTo.getList());
		model.addAttribute("pageTo", pageTo);
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public String insert(BoardVO vo) {
		bService.insert(vo);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.GET) //mvc 핸들러!//입력화면 갔다옴
	public void insert() {		
	}
	
}
