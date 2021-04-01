package com.spring5.code02.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring5.code02.member.LoginVO;
import com.spring5.code02.reply.ReplyService;
import com.spring5.code02.reply.ReplyVO;
import com.spring5.code02.vo.SearchVO;

@Controller
@RequestMapping(value = "/board")
public class BoardControllerImpl implements BoardController{

	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
	public HttpSession session;
	
	@Autowired
	public BoardService boardService;
	
	@Autowired
	public ReplyService replyService;
	
	@Override
	@GetMapping(value = "/articleForm")
	public String articleForm(HttpServletRequest request) {
		
		session = request.getSession();
		
		LoginVO loginVO = (LoginVO) session.getAttribute("loginInfo");
		if(loginVO == null) {
			return "redirect:/board/listArticles";

		}
		
		return "/board/articleForm";
	}
	
	@Override
	@PostMapping(value = "/addArticle")
	public String addArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, String> articleMap = upload(request, response);
		
		String imageFileName = articleMap.get("imageFileName");
		
		BoardVO boardVO = new BoardVO();
		boardVO.setId(articleMap.get("id"));
		boardVO.setTitle(articleMap.get("title"));
		boardVO.setContent(articleMap.get("content"));
		boardVO.setImageFileName(articleMap.get("imageFileName"));
		
		int articleNO = boardService.addArticle(boardVO);
		
		if(imageFileName != null && imageFileName.length() != 0) {
			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
			File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}
		return "redirect:/board/listArticles";
	}
	
	@Override
	@GetMapping(value = "/listArticles")
	public ModelAndView listArticles(HttpServletRequest request) {
		
		List<BoardVO> articlesList = new ArrayList<BoardVO>();
		
		SearchVO searchVO =  seachType(request);
		
		articlesList = boardService.listArticles(searchVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("articlesList", articlesList);
		mav.setViewName("/board/listArticles");
		
		String callType = (String) request.getParameter("callType");
		
		
		
		if(callType != null && callType.equals("delete")) {
			mav.addObject("msg", "삭제되었습니다.");
		}
		
		return mav;
	}
	
	@Override
	@GetMapping(value = "/viewArticle")
	public ModelAndView viewArticle(@RequestParam("articleNO") String articleNO,
									HttpServletRequest request) {
		
		//List<ReplyVO> replysList = new ArrayList<ReplyVO>();
		
		List<ReplyVO> replysList = replyService.viewReply(Integer.parseInt(articleNO));
		BoardVO boardVO = boardService.viewArticle(Integer.parseInt(articleNO));
		int conuntRecommend = boardService.countRecommend(Integer.parseInt(articleNO));
		
		String callType = (String) request.getParameter("callType");
		
		ModelAndView mav = new ModelAndView();
		
		if(callType != null && callType.equals("mod")) {
			mav.addObject("msg", "수정되었습니다.");
		} else if (callType != null && callType.equals("recommend")) {
			mav.addObject("msg", "이미 추천했습니다.");
		}
		
		mav.addObject("article", boardVO);
		mav.addObject("replysList", replysList);
		mav.addObject("conuntRecommend", conuntRecommend);
		mav.setViewName("/board/viewArticle");
		
		return mav;
	}
		
	@Override
	@PostMapping(value = "/modArticle")
	public String modArticle(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String, String> articleMap = upload(request, response);
		
		int articleNO = Integer.parseInt(articleMap.get("articleNO"));
		String title = articleMap.get("title");
		String content = articleMap.get("content");
		String imageFileName = articleMap.get("imageFileName");
		
		BoardVO boardVO = new BoardVO();
		boardVO.setArticleNO(articleNO);
		boardVO.setTitle(title);
		boardVO.setContent(content);
		boardVO.setImageFileName(imageFileName);
		
		boardService.modArticle(boardVO);
		
		if(imageFileName != null && imageFileName.length() != 0) {
			
			String originalFileName = articleMap.get("originalFileName");
			
			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
			File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
			
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
			
			File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + originalFileName);
			oldFile.delete();
		}
		 
		return "redirect:/board/viewArticle?articleNO=" + articleNO + "&callType=mod";
	}
		
	@Override
	@PostMapping(value = "/removeArticle")
	public ModelAndView removeArticle(@RequestParam("articleNO") String strArticleNO) {
		
		int articleNO = Integer.parseInt(strArticleNO);
		boardService.removeArticle(articleNO);
		
		ModelAndView mav = new ModelAndView("redirect:/board/listArticles?callType=delete");
		return mav;
	}
	
	@Override
	@GetMapping(value = "/upRecommend")
	public ModelAndView upRecommend(@RequestParam Map<String, Object> recommend) {
		
		
		int success = boardService.upRecommend(recommend);
		
		ModelAndView mav = new ModelAndView("redirect:/board/viewArticle?articleNO=" + recommend.get("articleNO"));
		
		if(success == 0) {
			mav.addObject("callType", "recommend");
		}
		
		return mav;
	}
	
	private SearchVO seachType(HttpServletRequest request) {
		
		String searchType = (String) request.getParameter("searchType");
		String searchValue = (String) request.getParameter("searchValue");
		
		SearchVO searchVO = new SearchVO();
		
		if(searchType == null) {
			return null;
		}
		
		if("".equals(searchType) || "".equals(searchValue)) {
			return null;
		}
		
		if(searchType.equals("writer")) {
			
			searchVO.setSearchType(searchType);
			searchVO.setSearchValue(searchValue);
		} else if(searchType.equals("article")){

			searchVO.setSearchType(searchType);
			searchVO.setSearchValue(searchValue);
		}
		
		return searchVO;
	}
	
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> articleMap = new HashMap<String, String>();
		
		String encoding = "utf-8";
		
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			
			for (int i=0; i<items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					
				} else {
					System.out.println("파라미터 이름 : " + fileItem.getFieldName());
					System.out.println("파일 이름 : " + fileItem.getFieldName());
					System.out.println("파일 크기 : " + fileItem.getFieldName());
					articleMap.put(fileItem.getFieldName(), fileItem.getName());
					
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						
						String fileName = fileItem.getName().substring(idx + 1);
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}
	
}

