package com.spring5.code02.admin;

import java.util.List;

import com.spring5.code02.board.BoardVO;
import com.spring5.code02.member.MemberVO;
import com.spring5.code02.reply.ReplyVO;

public interface AdminService {

	public List<MemberVO> listMembers();

	public int deleteMember(String id);

	public int deleteArticle(int articleNO);

	public List<BoardVO> listArticles();

	public List<ReplyVO> listReplys();

	public int removeReply(int articleNO);

	public int deleteReply(int articleNO);

}
