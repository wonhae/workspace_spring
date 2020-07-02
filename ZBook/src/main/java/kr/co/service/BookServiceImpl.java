package kr.co.service;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.domain.BookDTO;
import kr.co.persistence.BookDAO;


public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bDao;

	@Override
	public void insert(BookDTO dto) {
		bDao.insert(dto);
		
	}
	
	
	
	
}
