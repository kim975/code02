package com.spring5.code02.reply;

import java.util.List;

public interface ReplyService {

	public int addReply(ReplyVO replyVO);

	public List<ReplyVO> viewReply(int mainParentNO);

	public int delReply(int articleNO);

	public int modReply(ReplyVO replyVO);

	public List<ReplyVO> viewMyReply(String id);

	public int removedParentArticle(int mainParentNO);

}
