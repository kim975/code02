package com.spring5.code02.board;

import java.util.List;
import java.util.Map;

import com.spring5.code02.vo.SearchVO;

public interface BoardService {

	public List<BoardVO> listArticles();
	
	public List<BoardVO> listArticles(SearchVO searchVO);

	public int addArticle(BoardVO boardVO);

	public BoardVO viewArticle(int articleNO);

	public int modArticle(BoardVO boardVO);

	public int removeArticle(int articleNO);

	public int upRecommend(Map<String, Object> recommend);

	public int countRecommend(int boardArticleNO);

}
