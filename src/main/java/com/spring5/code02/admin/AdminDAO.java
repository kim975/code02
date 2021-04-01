package com.spring5.code02.admin;

import java.util.List;

import com.spring5.code02.board.BoardVO;
import com.spring5.code02.member.MemberVO;
import com.spring5.code02.reply.ReplyVO;

public interface AdminDAO {

	public List<MemberVO> selectAllMembersList();

	public int deleteMember(String id);

	public int deleteArticle(int articleNO);

	public List<BoardVO> selectAllArticlesList();

	public List<ReplyVO> selectAllReplysList();

	public int updateRemoveReply(int articleNO);

	public int deleteReply(int articleNO);

}
