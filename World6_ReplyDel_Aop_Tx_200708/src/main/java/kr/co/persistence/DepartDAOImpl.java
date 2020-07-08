package kr.co.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.DepartDTO;

@Repository
@Transactional
public class DepartDAOImpl implements DepartDAO{

	@Autowired
	private SqlSession session;
	
	private final String NS="d.e.p";
	
	@Override
	public void insert(DepartDTO dto) {
		session.insert(NS + ".insert", dto);  //departMapper.xml 만들어주기 
		
	}

	@Override
	public List<DepartDTO> list() {
		return session.selectList(NS + ".list");
	}

	@Override
	public DepartDTO read(String did) {
		return session.selectOne(NS + ".read", did);
	}

	@Override
	public DepartDTO updateui(String did) {
		return session.selectOne(NS + ".updateui", did);
	}

	@Override
	public void update(DepartDTO dto) {
		session.update(NS + ".update",dto);
		
	}

	@Override
	public void delete(String did) {
		session.delete(NS + ".delete", did);
		
	}

	
}
