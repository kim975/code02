package com.spring5.code02.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.web.servlet.ModelAndView;

public interface BoardController {

	public ModelAndView listArticles(HttpServletRequest request);

	public String articleForm(HttpServletRequest request);

	public ModelAndView viewArticle(String articleNO, HttpServletRequest request);

	public String addArticle(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public String modArticle(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView removeArticle(@PathParam("articleNO") String articleNO);

	public ModelAndView upRecommend(Map<String, Object> recommend);

}
