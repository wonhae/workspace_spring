package kr.co.service;

import java.util.List;

import kr.co.domain.MemberVO;

public interface MemberService {

	void insert(MemberVO vo);

	List<MemberVO> list();

}
