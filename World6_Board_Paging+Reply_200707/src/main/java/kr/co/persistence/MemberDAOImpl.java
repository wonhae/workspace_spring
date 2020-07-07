package kr.co.persistence;

import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.MemberDTO;

@Repository //이건 객체를 만드는 표시!어떤역할을 하는지 붙여주는것!  //service,controller 붙여도 돌아가지만 개념이 없는것. 
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession session;
	
	private final String NS="m.e.m";

	@Override
	public List<MemberDTO> list() {
		return session.selectList(NS+".list");  //selectlist vs selectOne 차이
		//->mapper로 가기 
	}

	@Override
	public void insert(MemberDTO dto) {
		session.insert(NS+".insert",dto);
		
	}

	@Override
	public MemberDTO read(String id) {
		return session.selectOne(NS+".read",id);  // . 빼고 실행하면 Mapped Statements collection does not contatin value for xxx 에러
	}

	@Override
	public MemberDTO updateui(String id) {
		return session.selectOne(NS+".updateui", id);
	}

	@Override
	public void update(MemberDTO dto) {
		session.update(NS+".update", dto);  //앞에 mapper로 가지는 이유는 session 자체에서 메서드 만들면 첫번째는 경로로 이렇게 만들어 줫기 때문. 
		
	}

	@Override
	public void delete(String id) {
		session.delete(NS + ".delete", id);
		
	}

	
	
	
}
