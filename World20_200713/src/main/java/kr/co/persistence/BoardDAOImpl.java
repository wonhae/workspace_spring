package kr.co.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;

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

	@Override
	public PageTO<BoardVO> list(PageTO<BoardVO> to) { //반환형이 PageTO<BoardVO> 여서 session.selectlist 쓰면 안됨!
		
		// offset 어디서부터, limit 몇개?? 오라클의 인텍스는 1부터 시작! mybatis 입장..은 기존 프로그램을 위한것! ->0부터 시작할 것!  / 기존에는startNum = 1 or 11, endNum=10 or 20
		// 시작점? curPage, perPage로 인해 결정! 
		RowBounds rowBounds = new RowBounds(to.getStartNum()-1, to.getPerPage());
		
		List<BoardVO> list = session.selectList(NS+".list", null, rowBounds);  //null인이유 넘겨줄건 숫자에 불과 , 검색어 필요할 때 그때 넣어줄 것! //rowbounds 사용안하고 기존대로 사용하면 mapper 바껴야함. 
		
		to.setList(list);
		
		//amount 추가! 
		Integer amount = session.selectOne(NS+".getAmount");
		if(amount != null) { 
			to.setAmount(amount);
		} else {
			to.setAmount(0);
		}
	
		

		return to;
	}

	@Override
	public List<BoardVO> searchlist(String searchType, String keyword) { //넣는건 두갠데 자리는 하나 : class 하나 만들거나  map 이용/ 항상 class 를 만들어줄수 없음. 자주 쓰는거만 class 만들것! 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return session.selectList(NS +".searchlist",map);  //나중에 페이징처리할꺼면 rowBounds도 들어간다! 
	}

	@Override //7.13
	public void addAttatch(String fullname, int bno) {
		Integer id = session.selectOne(NS+".getId");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("fullname", fullname);
		map.put("bno", bno);
		
		session.insert(NS+".addAttatch", map); // 3개 넣어야 하는데 하나: dtoclass 만들거나 map 
	}

	
	
}
