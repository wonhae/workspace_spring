package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.DepartDTO;
import kr.co.persistence.DepartDAO;

@Service  //transaction -> 서비스단에서 한다. 
@Transactional  // root-context에 설정할게 있기는한다...  무조건 붙이는 것으로 하기! 
public class DepartServiceImpl implements DepartService {

	//db 작업하니까 dao필요
	@Inject
	private DepartDAO dDAO;
	
	
	@Override
	public void insert(DepartDTO dto) {
		dDAO.insert(dto);		
	}


	@Override
	public List<DepartDTO> list() { 
		
		return dDAO.list();
	}


	@Override
	public DepartDTO read(String did) {
		return dDAO.read(did);
	}


	@Override
	public DepartDTO updateui(String did) {
		return dDAO.updateui(did);
	}


	@Override
	public void update(DepartDTO dto) {
		dDAO.update(dto);
		
	}


	@Override
	public void delete(String did) {
		dDAO.delete(did);
		
	}

}
