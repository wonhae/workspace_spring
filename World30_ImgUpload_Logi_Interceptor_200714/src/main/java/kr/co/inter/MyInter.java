package kr.co.inter;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class MyInter extends HandlerInterceptorAdapter { //7.14

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandle::::::::::::::::::");
		
		HandlerMethod hmethod = (HandlerMethod) handler;
		Method method = hmethod.getMethod();
		System.out.println(hmethod.getBean());
		System.out.println(method);
		
		return true;  //false 일 경우  위에것 찍히고 여기서 중단!!!! 조건에 따라 true,false 되게 해줘야. 
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle::::::::::::::::::::::::::::::::");
		
		Object test = modelAndView.getModel().get("test");
		System.out.println("::::::::::::::test");
		
	}

	
	
}
