package com.spring5.code02.admin;

import org.springframework.web.servlet.ModelAndView;

public interface AdminController {

	public String adminMainPage();

	public ModelAndView memberList();

	//public ModelAndView memberList(String tag);
	
	public String removeMember(String id);

	public String removeArticle(int articleNO);

	public ModelAndView articleList();

	public ModelAndView replyList();

	public String removeReply(int articleNO);

	public String deleteReply(int articleNO);

}
