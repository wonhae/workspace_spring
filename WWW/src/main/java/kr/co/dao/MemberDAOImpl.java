package kr.co.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Autowired
	private SqlSession ss;
	
	private final String NS = "m.e.m";
	
	
	@Override
	public void insert(MemberVO vo) {
		ss.insert(NS+".insert", vo);
	}


	@Override
	public List<MemberVO> list() {
		return ss.selectList(NS+".list");
	}

}
