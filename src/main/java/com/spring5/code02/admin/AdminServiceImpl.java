package com.spring5.code02.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring5.code02.board.BoardVO;
import com.spring5.code02.member.MemberVO;
import com.spring5.code02.reply.ReplyVO;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDAO adminDAO;

	public AdminServiceImpl() {
		super();
	}

	@Override
	public List<MemberVO> listMembers() {

		return adminDAO.selectAllMembersList();
	}
	
	@Override
	public int deleteMember(String id) {
		
		return adminDAO.deleteMember(id);
	}
	
	@Override
	public int deleteArticle(int articleNO) {
		
		return adminDAO.deleteArticle(articleNO);
	}

	@Override
	public List<BoardVO> listArticles() {
		
		return adminDAO.selectAllArticlesList();
	}
	
	@Override
	public List<ReplyVO> listReplys() {
		
		return adminDAO.selectAllReplysList();
	}
	
	@Override
	public int removeReply(int articleNO) {
		
		return adminDAO.updateRemoveReply(articleNO);
	}
	
	@Override
	public int deleteReply(int articleNO) {
		
		return adminDAO.deleteReply(articleNO);
	}
	
}
