package com.spring5.code02.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring5.code02.member.LoginVO;

public class LoginMemberInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		response.setContentType("text/html;charset=utf-8");
		boolean loginFlag = false;
		   
	    HttpSession session = request.getSession();
	    
	    LoginVO loginVO = (LoginVO) session.getAttribute("loginInfo");
	    
        if(loginVO != null ){
          
          loginFlag = true;
       
        } else {
      
          PrintWriter out = response.getWriter();
          out.println("<script>alert('로그인을 해주세요');history.go(-1);</script>");
          out.flush();

          //response.sendRedirect(request.getContextPath()+"/member/loginForm");
          loginFlag = false;
        }
 
        return loginFlag;
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
