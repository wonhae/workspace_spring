package com.naver.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.BookDTO;
import kr.co.service.BookService;

@Controller
@RequestMapping("book")
public class BookController {

	@Inject
	private BookService bService;
	
	
	
	
	//list 부터 할 것! 
	
	
	@RequestMapping(value="/insert" , method = RequestMethod.POST)
	public String insert(BookDTO dto) {
		bService.insert(dto);
		return "redirect:/book/list";
	}

	
	@RequestMapping("/insert")
	public String insert() {
		return "/book/insert";
	}
	
}
