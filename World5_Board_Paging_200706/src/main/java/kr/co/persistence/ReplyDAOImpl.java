package kr.co.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Autowired
	private SqlSession session;

	private final String NS = "r.e.p";
	
	@Override
	public int insert(ReplyVO vo) { //rno 누락(기존rno에 +1 해서 받기로) 
		Integer rno = session.selectOne(NS+".getRno");  //rno 가져오는것!
		vo.setRno(rno); 
		return session.insert(NS+".insert", vo);
	}

	@Override
	public List<ReplyVO> list(int bno) {
		return session.selectList(NS+".list", bno);
	}
	
	

}
