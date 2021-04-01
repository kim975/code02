package com.spring5.code02.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring5.code02.vo.SearchVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	public SqlSession sqlSession;
	
	@Override
	public List<BoardVO> listArticles() {

		return sqlSession.selectList("BoardMapper.selectMainArticleList");
	}
	
	@Override
	public List<BoardVO> listArticles(SearchVO searchVO) {
		
		return sqlSession.selectList("BoardMapper.selectArticleList", searchVO);
	}
	
	@Override
	public int insertNewArticle(BoardVO boardVO) {
		
		return sqlSession.insert("BoardMapper.insertNewArticle", boardVO);
	}
	
	@Override
	public int selectCurrentArticleNO() {
		
		return sqlSession.selectOne("BoardMapper.selectCurrentArticleNO");
	}
	
	@Override
	public BoardVO selectArticle(int articleNO) {
		
		return sqlSession.selectOne("BoardMapper.selectArticle", articleNO);
	}

	@Override
	public int updateArticle(BoardVO boardVO) {

		return sqlSession.update("BoardMapper.updateArticle", boardVO);
	}

	@Override
	public int updateArticleDeleteFlag(int articleNO) {
		
		return sqlSession.update("BoardMapper.updateArticleDeleteFlag", articleNO);
	}
	
	@Override
	public int updateViewCount(int articleNO) {
		
		return sqlSession.update("BoardMapper.updateViewCount", articleNO);
	}
	
	
	// 추천 
	
	@Override
	public int insertRecommend(Map<String, Object> recommend) {
		
		return sqlSession.insert("BoardMapper.insertRecommend", recommend);
	}
	
	@Override
	public boolean selectIsRecommend(Map<String, Object> recommend) {
		
		return sqlSession.selectOne("BoardMapper.selectIsRecommend", recommend);
	}
	
	@Override
	public int selectCountRecommend(int boardArticleNO) {
		
		return sqlSession.selectOne("BoardMapper.selectCountRecommend", boardArticleNO);
	}

	
	
}
