package com.spring5.code02.reply;

import java.util.List;

public interface ReplyDAO {

	public int insertNewReply(ReplyVO replyVO);

	public List<ReplyVO> selectAllReply(int mainParentNO);

	public int updateReplyDeleteFlag(int articleNO);

	public int updateReply(ReplyVO replyVO);

	public List<ReplyVO> selectMyReply(String id);

	public int updateParentDelete(int mainParentNO);

	
}
