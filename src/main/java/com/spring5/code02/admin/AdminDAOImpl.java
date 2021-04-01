package com.spring5.code02.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring5.code02.board.BoardVO;
import com.spring5.code02.member.MemberVO;
import com.spring5.code02.reply.ReplyVO;

@Repository
public class AdminDAOImpl implements AdminDAO{

	@Autowired
	private SqlSession sqlSession;

	public AdminDAOImpl() {
		super();
	}
	
	@Override
	public List<MemberVO> selectAllMembersList() {

		return sqlSession.selectList("AdminMapper.selectAllMembersList");
	}
	
	@Override
	public int deleteMember(String id) {
		return sqlSession.delete("AdminMapper.deleteMember", id);
	}
	
	@Override
	public int deleteArticle(int articleNO) {
		
		return sqlSession.delete("AdminMapper.deleteArticle", articleNO);
	}

	@Override
	public int deleteReply(int articleNO) {
		
		return sqlSession.delete("AdminMapper.deleteReply", articleNO);
	}
	
	@Override
	public List<BoardVO> selectAllArticlesList() {

		return sqlSession.selectList("AdminMapper.selectAllArticlesList");
	}
	
	@Override
	public List<ReplyVO> selectAllReplysList() {
		
		return sqlSession.selectList("AdminMapper.selectAllReplysList");
	}
	
	@Override 
	public int updateRemoveReply(int articleNO) {
		
		return sqlSession.update("AdminMapper.updateRemoveReply", articleNO);
	}
	
}
