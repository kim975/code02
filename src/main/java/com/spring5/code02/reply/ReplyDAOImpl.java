package com.spring5.code02.reply;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Autowired
	public SqlSession sqlSession;
	
	@Override
	public int insertNewReply(ReplyVO replyVO) {
		
		return sqlSession.insert("ReplyMapper.insertNewReply", replyVO);
	}
	
	@Override
	public List<ReplyVO> selectAllReply(int mainParentNO) {
		
		return sqlSession.selectList("ReplyMapper.selectAllReply", mainParentNO);
	}
	
	@Override
	public int updateReplyDeleteFlag(int articleNO) {
		
		return sqlSession.update("ReplyMapper.updateReplyDeleteFlag", articleNO);
	}
	
	@Override
	public int updateReply(ReplyVO replyVO) {
		
		return sqlSession.update("ReplyMapper.updateReply", replyVO);
	}
	
	@Override
	public List<ReplyVO> selectMyReply(String id) {
		
		return sqlSession.selectList("ReplyMapper.selectMyReply", id);
	}
	
	@Override
	public int updateParentDelete(int mainParentNO) {
		
		return sqlSession.update("ReplyMapper.updateParentDelete", mainParentNO);
	}
}
