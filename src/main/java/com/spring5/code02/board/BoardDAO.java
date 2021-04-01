package com.spring5.code02.board;

import java.util.List;
import java.util.Map;

import com.spring5.code02.vo.SearchVO;

public interface BoardDAO {

	public List<BoardVO> listArticles(SearchVO searchVO);

	public int insertNewArticle(BoardVO boardVO);

	public BoardVO selectArticle(int articleNO);

	public int selectCurrentArticleNO();

	public int updateArticle(BoardVO boardVO);

	public int updateArticleDeleteFlag(int articleNO);

	public int updateViewCount(int articleNO);

	public int insertRecommend(Map<String, Object> recommend);

	public boolean selectIsRecommend(Map<String, Object> recommend);

	public int selectCountRecommend(int boardArticleNO);

	public List<BoardVO> listArticles();

}
