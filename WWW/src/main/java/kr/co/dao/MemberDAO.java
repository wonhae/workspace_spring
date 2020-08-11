package kr.co.dao;

import java.util.List;

import kr.co.domain.MemberVO;

public interface MemberDAO {

	void insert(MemberVO vo);

	List<MemberVO> list();

	
	
}
