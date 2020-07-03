package kr.co.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession session;
	
	private final String NS = "b.o.a";
	
	@Override
	public void insert(BoardVO vo) {
		//여기서 bno 작업해도 상관없다 . 원래는 따로 메서드 만들어 서비스에서하려했는데
		Integer bno = session.selectOne(NS+".getBno");  //방금 NS+ 안썼는데 작동 되었음. mapper에서 id 가 하나라 인식해주긴함
		if(bno != null) {
			bno += 1;
		} else {  //딱 한번만 실행되는 코드! 
			bno = 1;
		}
		
		vo.setBno(bno);
		
		session.insert(NS+".insert", vo);
	}

	@Override
	public List<BoardVO> list() {
		return session.selectList(NS+".list");
	}

	@Override
	public BoardVO read(int bno) {
		return session.selectOne(NS+".read", bno);
	}
	
	@Override
	public void increaseViewcnt(int bno) {
		session.update(NS+".increaseViewcnt", bno); 		
	}

	@Override
	public BoardVO updateui(int bno) {
		return session.selectOne(NS + ".updateui", bno);
	}

	@Override
	public void update(BoardVO vo) {
		session.update(NS+".update", vo);
		
	}

	@Override
	public void delete(int bno) {
		session.delete(NS+".delete", bno);
		
	}

	
	
}
