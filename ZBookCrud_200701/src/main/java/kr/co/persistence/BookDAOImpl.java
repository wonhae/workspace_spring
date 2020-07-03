package kr.co.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import kr.co.domain.BookDTO;

public class BookDAOImpl implements BookDAO {

	@Inject
	private SqlSession session;
	
	private final String NS = "b.o.o";

	@Override
	public void insert(BookDTO dto) {
		session.insert(NS + ".insert",dto);
		
	}
	
	
	
}
