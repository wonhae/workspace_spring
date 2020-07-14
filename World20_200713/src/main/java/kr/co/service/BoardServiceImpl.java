package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.persistence.BoardDAO;
import kr.co.persistence.ReplyDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO bDao;
	@Autowired
	private ReplyDAO rDao;

	@Override
	public void insert(BoardVO vo) {  //7.13 업로드한 파일정보 추가! boardvo의 files 에 저장되어있음. 파일첨부 있을수도 없을수도
		bDao.insert(vo);
		
		String[] files = vo.getFiles();
		if(files != null) {  //첨부파일있으면
			for(String fullName : files) {
				bDao.addAttatch(fullName, vo.getBno());  //file이 fullname
			}
		}
	}

	@Override
	public List<BoardVO> list() {
		return bDao.list();
	}

	@Override
	public BoardVO read(int bno) {
		bDao.increaseViewcnt(bno); //조회수 증가시키는 것! //그땐 dao클래스 안에서 했음! 
		return bDao.read(bno);
	}

	@Override
	public BoardVO updateui(int bno) {		
		return bDao.updateui(bno);
	}

	@Override
	public void update(BoardVO vo) {
		bDao.update(vo);
	}

	@Override
	public void delete(int bno) {// 7/8  특정 bno를 참조하는 댓글들을 먼저 삭제	
		rDao.deleteByBno(bno);
		bDao.delete(bno);
	}

	@Override
	public PageTO<BoardVO> list(PageTO<BoardVO> to) { 
		
		return bDao.list(to);
	}

	@Override
	public List<BoardVO> searchlist(String searchType, String keyword) {
		return bDao.searchlist(searchType,keyword);
	}

	@Override
	public List<String> getAttach(Integer bno) {
		return bDao. getAttach(bno);
	}


	
		
	


	
	
	
}
