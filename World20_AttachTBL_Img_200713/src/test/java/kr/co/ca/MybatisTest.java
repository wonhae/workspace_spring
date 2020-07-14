package kr.co.ca;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//mybatis test 하기 위한 클래스! 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) //(root-context)//**는 폴더가 있을수도 없을 수도/ 이 환경설정 파일을 이용해 테스트한다. 
public class MybatisTest { 
	
	@Inject
	private SqlSessionFactory sqlFactory; //실제작업할땐 sqlFactory로 사용못하고 session. 이렇게 사용한다. 그떄마다 쓰기 힘들어서 root에서 bean만들기 
	
	//test 해야할 대상! 접근제한자 무조건 public void 무 파라미터 
	@Test
	public void testSession() {
		SqlSession session = null;  //우리가 아는 세션 객체와 전혀 다른 것! 
		
		try {
			session = sqlFactory.openSession();
			System.out.print(":::::::::::::::");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
}
