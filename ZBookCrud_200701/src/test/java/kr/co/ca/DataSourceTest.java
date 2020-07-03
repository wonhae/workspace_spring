package kr.co.ca;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//db test 하기 위한 클래스! 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) //(root-context)//**는 폴더가 있을수도 없을 수도/ 이 환경설정 파일을 이용해 테스트한다. 
public class DataSourceTest { 
	//@ Autowired , injection
	//spring container 가 관리하는 객체들..bean  //datasource: javax.sql
	@Autowired
	private DataSource dataFactory; //자동으로 넣어준다. 
	
	//test 해야할 대상! 접근제한자 무조건 public void 무 파라미터 
	@Test
	public void testConnection() {
		Connection conn = null;
		
		try {
			conn = dataFactory.getConnection();
			System.out.print(":::::::::::::::");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
}
