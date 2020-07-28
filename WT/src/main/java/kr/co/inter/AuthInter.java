package kr.co.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInter extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Object login = session.getAttribute("login");
		System.out.println("dkfwdkjfw");
		if(login == null) {
			
			String uri = request.getRequestURI();
			String querystring = request.getQueryString();
			if(querystring == null) {
				querystring = "";
			}else {
				querystring= "?" + querystring;
			}
			
			if(request.getMethod().equals("GET")) {
				String path = uri + querystring;
				session.setAttribute("path", path);
			}
			
			
			response.sendRedirect("/member/login");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	
}
