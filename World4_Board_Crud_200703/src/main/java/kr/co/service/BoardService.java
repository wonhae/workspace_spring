package kr.co.service;

import java.util.List;

import kr.co.domain.BoardVO;

public interface BoardService {
	
	void insert(BoardVO vo);  //dao와 유사하므로 그냥 복붙(복사하는게 좋다)

	List<BoardVO> list();

	BoardVO read(int bno);

	BoardVO updateui(int bno);

	void update(BoardVO vo);

	void delete(int bno);

	
}
