package com.spring5.code02.member;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface MemberController {

	public ModelAndView addMember(@ModelAttribute("member") MemberVO member);

	public String duplicateId(String id);
	
	public String duplicateEmail(@RequestParam("email") String email); 
	
	public String duplicatePhone(@RequestParam("phone") String phone);

	public ModelAndView login(@RequestParam HashMap<String, String> login,
							  HttpServletRequest request) throws Exception;

	public String logout(HttpServletRequest request);

	public ModelAndView myPage(HttpServletRequest request);

	public String modMember(MemberVO member,
							HttpServletRequest request);

	public String delMember(String id);

	public ModelAndView myArticle(HttpServletRequest request);

	public ModelAndView myReply(String id);

	public ModelAndView mainPage();

}
