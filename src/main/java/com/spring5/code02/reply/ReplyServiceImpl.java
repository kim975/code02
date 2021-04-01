package com.spring5.code02.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	public ReplyDAO replyDAO;
	
	@Override
	public int addReply(ReplyVO replyVO) {
		
		return replyDAO.insertNewReply(replyVO);
	}
	
	@Override
	public List<ReplyVO> viewReply(int mainParentNO) {
		
		return replyDAO.selectAllReply(mainParentNO);
	}
	
	@Override
	public int delReply(int articleNO) {
		
		return replyDAO.updateReplyDeleteFlag(articleNO);
	}
	
	@Override
	public int modReply(ReplyVO replyVO) {
		
		return replyDAO.updateReply(replyVO);
	}
	
	@Override
	public List<ReplyVO> viewMyReply(String id) {
		
		return replyDAO.selectMyReply(id);
	}
	
	@Override
	public int removedParentArticle(int mainParentNO) {
		
		return replyDAO.updateParentDelete(mainParentNO);
	}
}
