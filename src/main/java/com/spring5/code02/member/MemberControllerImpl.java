package com.spring5.code02.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring5.code02.board.BoardService;
import com.spring5.code02.board.BoardVO;
import com.spring5.code02.reply.ReplyService;
import com.spring5.code02.reply.ReplyVO;
import com.spring5.code02.vo.SearchVO;

@Controller
public class MemberControllerImpl implements MemberController{

	@Autowired
	public MemberService memberService;
	
	@Autowired
	public BoardService boardService;
	
	@Autowired
	public ReplyService replyService;
	
	@Override
	@GetMapping(value = {"/", "mainPage"})
	public ModelAndView mainPage() {
		
		List<BoardVO> articlesList = new ArrayList<BoardVO>();
		
		articlesList = boardService.listArticles();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("articlesList", articlesList);
		mav.setViewName("/mainPage");
		
		return mav;
	}
	
	@GetMapping(value = "/member/loginForm")
	public String login() {
		return "member/loginForm";
	}
	
	@GetMapping(value = "/member/memberForm")
	public String memberFom() {
		return "member/memberForm";
	}
	
	@Override
	@PostMapping(value = "/member/addMember")
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member) {
		
		int result = memberService.addMember(member);
		
		ModelAndView mav = new ModelAndView();
		if(result == 1) {
			mav.setViewName("/member/successAddMember");
			mav.addObject("name", member.getName());
		} else {
			mav.setViewName("/member/failAddMember");
		}
		
		return mav;
	}
	
	@Override
	@PostMapping(value = "/member/login")
	public ModelAndView login(@RequestParam HashMap<String, String> login,
							  HttpServletRequest request) throws Exception {
		
		LoginVO loginVO =  memberService.loginMember(login);
		
		ModelAndView mav = new ModelAndView();
		
		if(loginVO == null) {
			mav.setViewName("member/loginForm");
			mav.addObject("message", "아이디, 비밀번호가 틀렸습니다.");
			
		} else {

			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", loginVO);
			session.setMaxInactiveInterval(1 * 60 * 60 *2);
			mav.setViewName("redirect:/mainPage");
		}
		return mav;
	}
	
	@Override
	@GetMapping(value = "/member/logout")
	public String logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		
		return "redirect:/mainPage";
	}
	
	@Override
	@GetMapping(value = "/member/myPage")
	public ModelAndView myPage(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		ModelAndView mav = new ModelAndView("/member/myPage");
		MemberVO memberVO;
		
		if(id != null) {
			
			memberVO =  memberService.selectMember(id);
		} else {
			
			HttpSession session = request.getSession();
			LoginVO loginVO = (LoginVO) session.getAttribute("loginInfo");
			
			memberVO = memberService.selectMember(loginVO.getId());
		}
		
		mav.addObject("memberVO", memberVO);
		
		return mav;
	}
	
	@Override
	@PostMapping(value = "/member/modMember")
	public String modMember(@ModelAttribute MemberVO member,
						    HttpServletRequest request) {
		
		memberService.modMember(member);
		
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute("loginInfo");
		
		if(!(loginVO.getId().equals("master"))) {
			
			loginVO.setName(member.getName());
			session.setAttribute("loginInfo", loginVO);
		}
		
		return "redirect:/member/myPage?id="+member.getId();
	}
	
	@Override
	@GetMapping(value = "/member/delMember")
	public String delMember(@RequestParam("id") String id) {
		
		memberService.delMember(id);
		
		return "redirect:/member/logout";
	}
	
	@Override
	@GetMapping(value = "/member/myWriteArticle")
	public ModelAndView myArticle(HttpServletRequest request){

		HttpSession session = request.getSession();
		
		LoginVO loginVO = (LoginVO) session.getAttribute("loginInfo");
		
		List<BoardVO> articlesList = new ArrayList<BoardVO>();
		
		SearchVO searchVO = new SearchVO("writer", loginVO.getId());
		
		articlesList = boardService.listArticles(searchVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("articlesList", articlesList);
		mav.setViewName("/member/listArticles");
		
		return mav;
	}
	
	@Override
	@GetMapping(value = "/member/myWriteReply")
	public ModelAndView myReply(@RequestParam String id){

		List<ReplyVO> replysList = new ArrayList<ReplyVO>();
		
		replysList = replyService.viewMyReply(id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("replysList", replysList);
		mav.setViewName("/member/listReply");
		
		return mav;
	}
	
	//중복 검사
	
	@Override
	@GetMapping(value = "/member/duplicateId")
	@ResponseBody
	public String duplicateId(@RequestParam("id") String id) {
		
		return memberService.duplicateId(id);
	}
	
	@Override
	@GetMapping(value = "/member/duplicateEmail")
	@ResponseBody
	public String duplicateEmail(@RequestParam("email") String email) {
		
		return memberService.duplicateEmail(email);
	}
	
	@Override
	@GetMapping(value = "/member/duplicatePhone")
	@ResponseBody
	public String duplicatePhone(@RequestParam("phone") String phone) {
		
		return memberService.duplicatePhone(phone);
	}
}
