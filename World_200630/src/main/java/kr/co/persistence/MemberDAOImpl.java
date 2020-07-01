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
		// TODO Auto-generated method stub
		return session.selectList(NS+".list");  //selectlist vs selectOne 차이
		//->mapper로 가기 
	}

	@Override
	public void insert(MemberDTO dto) {
		session.insert(NS+".insert",dto);
		
	}
	
	
}
