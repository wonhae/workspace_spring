package kr.co.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AOPTestServiceImpl implements AOPTestService {

	//얘네가 joinpoints
	@Override
	public void aoptest() {
			//aop 이용하여 for문 제외  안보이게 만들것이다! 즉 여기는 코어코드만 남는다.
			long start = System.currentTimeMillis();
			
			for(int i = 0; i < 100000;  i ++) {
				System.out.println(i);
			}
			
			long end = System.currentTimeMillis();
			System.out.println(end-start);
		}

	@Override //aop 적용한 코드 
	public void aoptest2() { 
		for(int i = 0; i < 10;  i ++) {
			System.out.println(i);
		}
		
	}

	@Override
	public void aoptest3() { //몇초 걸리는지 확인~~ 
		for(int i = 0; i < 100000;  i ++) {
			System.out.println(i);
		}
		
	}
		

}
