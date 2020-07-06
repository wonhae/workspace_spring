package kr.co.persistence;

import java.util.List;

import kr.co.domain.MemberDTO;

public interface MemberDAO {

	List<MemberDTO> list();

	void insert(MemberDTO dto);

	MemberDTO read(String id);

	MemberDTO updateui(String id);

	void update(MemberDTO dto);

	void delete(String id);


	

}
