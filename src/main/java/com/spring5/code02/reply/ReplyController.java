package com.spring5.code02.reply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface ReplyController {

	public String addArticle(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView listReply(String mainParentNO);

	public String modReply(ReplyVO replyVO);

	public String delReply(ReplyVO replyVO);

}
