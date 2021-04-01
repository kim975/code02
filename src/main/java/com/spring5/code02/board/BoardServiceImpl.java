package com.spring5.code02.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring5.code02.reply.ReplyDAO;
import com.spring5.code02.vo.SearchVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	public BoardDAO boardDAO;
	
	@Autowired
	public ReplyDAO replyDAO;
	
	@Override
	public List<BoardVO> listArticles() {
		
		return boardDAO.listArticles();
	}
	
	@Override
	public List<BoardVO> listArticles(SearchVO searchVO) {
		
		return boardDAO.listArticles(searchVO);
	}
	
	@Override
	public int addArticle(BoardVO boardVO) {
		
		boardDAO.insertNewArticle(boardVO);
		return boardDAO.selectCurrentArticleNO();
	}

	@Override
	public BoardVO viewArticle(int articleNO) {
		
		boardDAO.updateViewCount(articleNO);
		return boardDAO.selectArticle(articleNO);
	}

	@Override
	public int modArticle(BoardVO boardVO) {
		
		return boardDAO.updateArticle(boardVO);
	}

	@Override
	public int removeArticle(int articleNO) {
		
		replyDAO.updateParentDelete(articleNO);
		return boardDAO.updateArticleDeleteFlag(articleNO);
	}
	
	// 추천
	@Override
	public int upRecommend(Map<String, Object> recommend) {
		
		if(!(boardDAO.selectIsRecommend(recommend))) {
			
			return boardDAO.insertRecommend(recommend);
		}
		
		return 0;
	}
	
	@Override
	public int countRecommend(int boardArticleNO) {

		
		return boardDAO.selectCountRecommend(boardArticleNO);
	}
}
