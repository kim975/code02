package com.spring5.code02.reply;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/reply")
public class ReplyControllerImpl implements ReplyController{
	
	private static String REPLY_IMAGE_REPO = "C:\\board\\reply_image";
	
	@Autowired
	public ReplyService replyService;
	
	@Override
	@PostMapping(value = "/addReply")
	public String addArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, String> articleMap = upload(request, response);
		
		String imageFileName = articleMap.get("imageFileName");
		String id = articleMap.get("id");
		int mainParentNO = Integer.parseInt(articleMap.get("mainParentNO"));
		
		
		ReplyVO replydVO = new ReplyVO();
		replydVO.setId(id);
		replydVO.setMainParentNO(mainParentNO);
		replydVO.setParentNO(0);
		replydVO.setContent(articleMap.get("content"));
		replydVO.setImageFileName(articleMap.get("imageFileName"));
		
		int articleNO = replyService.addReply(replydVO);
		
		if(imageFileName != null && imageFileName.length() != 0) {
			File srcFile = new File(REPLY_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
			File destDir = new File(REPLY_IMAGE_REPO + "\\" + articleNO);
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}
		return "redirect:/board/viewArticle?articleNO=" + mainParentNO;
	}
	
	
	@Override
	@GetMapping(value = "/modReply")
	public String modReply(@ModelAttribute ReplyVO replyVO) {
		
		replyService.modReply(replyVO);
		
		return "redirect:/board/viewArticle?articleNO="+replyVO.getMainParentNO();
	}
	
	@Override
	@GetMapping(value = "/delReply")
	public String delReply(@ModelAttribute ReplyVO replyVO) {
		
		replyService.delReply(replyVO.getArticleNO());
		
		return "redirect:/board/viewArticle?articleNO="+replyVO.getMainParentNO();
	}
	
	//대댓글용으로 만들기
	@Override
	@GetMapping(value = "/listReply")
	public ModelAndView listReply(@RequestParam String mainParentNO) {
		
		List<ReplyVO> replysList = new ArrayList<ReplyVO>();
		
		replysList = replyService.viewReply(Integer.parseInt(mainParentNO));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ReplysList", replysList);
		mav.setViewName("/reply/listArticles");
		
		return mav;
	}
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> articleMap = new HashMap<String, String>();
		
		String encoding = "utf-8";
		
		File currentDirPath = new File(REPLY_IMAGE_REPO);
		
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
