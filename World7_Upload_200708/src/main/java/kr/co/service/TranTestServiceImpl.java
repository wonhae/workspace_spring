package kr.co.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.BoardVO;
import kr.co.persistence.BoardDAO;

@Service
@Transactional
public class TranTestServiceImpl implements TranTestService {

	@Inject 
	private BoardDAO bDao;
	
	
	@Override
	public void insertNupdate1(BoardVO vo) {
		bDao.insert(vo);	
		System.out.println(4/0); //arithmetic exception
		bDao.update(vo); //update 하려면 bno 필요하다. 
	}

	
	
}
