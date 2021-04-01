package com.spring5.code02.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring5.code02.board.BoardVO;
import com.spring5.code02.member.MemberVO;
import com.spring5.code02.reply.ReplyVO;



@Controller
@RequestMapping(value = "/admin")
public class AdminControllerImpl implements AdminController{

	@Autowired
	public AdminService adminService;
	
	@Override
	@GetMapping(value = "/adminMainPage")
	public String adminMainPage() {
		return "/admin/adminMainPage";
	}
	
	@Override
	@GetMapping(value = "/memberList")
	public ModelAndView memberList() {
		
		List<MemberVO> membersList = adminService.listMembers();
		ModelAndView mav = new ModelAndView("/admin/listMembers");
		mav.addObject("membersList", membersList);
		
		return mav;
	}
	
	@Override
	@GetMapping(value = "/articleList")
	public ModelAndView articleList() {
		
		List<BoardVO> articlesList = adminService.listArticles();
		ModelAndView mav = new ModelAndView("/admin/listArticles");
		mav.addObject("articlesList", articlesList);
		
		return mav;
	}
	
	@Override
	@GetMapping(value = "/removeMember")
	public String removeMember(@RequestParam String id) {
		
		adminService.deleteMember(id);
		return "redirect:/admin/memberList";
	}
	
	@Override
	@GetMapping(value = "/removeArticle")
	public String removeArticle(@RequestParam int articleNO) {
		
		adminService.deleteArticle(articleNO);
		return "redirect:/admin/articleList";
	}
	
	@Override
	@GetMapping(value = "/replyList") 
	public ModelAndView replyList(){
		
		List<ReplyVO> replyList = adminService.listReplys();
		ModelAndView mav = new ModelAndView("/admin/listReply");
		mav.addObject("replysList", replyList);
		return mav;
		
	}
	
	@Override
	@GetMapping(value = "/removeReply")
	public String removeReply(@RequestParam int articleNO) {
		
		adminService.removeReply(articleNO);
		
		return "redirect:/admin/replyList";
	}
	
	@Override
	@GetMapping(value = "/deleteReply")
	public String deleteReply(@RequestParam int articleNO) {
		
		adminService.deleteReply(articleNO);
		
		return "redirect:/admin/replyList";
	}
	
	
	/*
	//탈퇴한 회원 보는 기능 XXXXXX
	@Override
	@GetMapping(value = "/deleteMemberList")
	public ModelAndView memberList(@RequestParam String tag) {
		
		List<MemberVO> membersList = adminService.listMembers();
		ModelAndView mav = new ModelAndView("/admin/listMembers");
		mav.addObject("membersList", membersList);
		
		return mav;
	}
	*/
	
}
