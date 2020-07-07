package kr.co.persistence;

import java.util.List;

import kr.co.domain.ReplyVO;

public interface ReplyDAO {

	int insert(ReplyVO vo);
	
	List<ReplyVO> list(int bno); //특정 게시글에 대한 댓글만 가져와야한다! 
}
