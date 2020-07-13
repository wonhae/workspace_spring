package kr.co.persistence;

import java.util.List;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;

public interface BoardDAO {
	
	void insert(BoardVO vo);
	
	void addAttatch(String fullname, int bno); //7.13 

	List<BoardVO> list();

	BoardVO read(int bno);

	BoardVO updateui(int bno);

	void increaseViewcnt(int bno);

	void update(BoardVO vo);

	void delete(int bno);

	PageTO<BoardVO> list(PageTO<BoardVO> to);

	List<BoardVO> searchlist(String searchType, String keyword);

	List<String> getAttach(Integer bno);

	List<String> updateAttach(Integer bno); //7.13 시도 


	
	
}
