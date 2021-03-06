package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.persistence.MemberDAO;

@Service //객체가 된다. 
public class MemberServiceImpl implements MemberService {
	
	
	@Inject  //spring 컨테이너가 이 타입으로 된 객체를 가지고 있다는 뜻! 
	private MemberDAO memberDAO;

	@Override
	public void insert(MemberDTO dto) {
		memberDAO.insert(dto);
		
	}

	@Override
	public List<MemberDTO> list() {		
		return memberDAO.list();
	}

	@Override
	public MemberDTO read(String id) {
		return memberDAO.read(id);
	}

	@Override
	public MemberDTO updateui(String id) {	
		return memberDAO.updateui(id);
	}

	@Override
	public void update(MemberDTO dto) {
		memberDAO.update(dto);
	}

	@Override
	public void delete(String id) {
		memberDAO.delete(id);
		
	}

	@Override
	public MemberDTO loginpost(LoginDTO login) {
		return memberDAO.loginpost(login);
	}
	
}
