package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.dao.MemberDAO;
import kr.co.domain.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	@Override
	public void insert(MemberVO vo) {
		dao.insert(vo);
		
	}

	@Override
	public List<MemberVO> list() {
		return dao.list();
	}

}
