package com.spring5.code02.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring5.code02.member.LoginVO;

public class AdminInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean masterFlag = false;
		   
	    HttpSession session = request.getSession();
	    
	    LoginVO loginVO = (LoginVO) session.getAttribute("loginInfo");
	    
        if(loginVO != null && loginVO.getId().equals("master")){
          
          masterFlag = true;
       
        } else {
      
          response.sendRedirect(request.getContextPath()+"/member/loginForm");
          masterFlag = false;
        }
 
        return masterFlag;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

}
