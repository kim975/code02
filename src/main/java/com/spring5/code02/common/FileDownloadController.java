package com.spring5.code02.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileDownloadController {
	
	private static String ARTICLE_IMAFE_REPO = "C:\\board\\article_image";
	
	@GetMapping("/download")
	protected void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String imageFileName = (String) request.getParameter("imageFileName");
		String articleNO = request.getParameter("articleNO");
		
		System.out.println("imageFileName = " + imageFileName);
		
		OutputStream out =response.getOutputStream();
		String path = ARTICLE_IMAFE_REPO + "\\" + articleNO + "\\" +imageFileName;
		File imageFile = new File(path);
		
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment;filename=" + imageFileName);
		FileInputStream in = new FileInputStream(imageFile);
		
		byte[] buffer = new byte[1024 * 8];
		while(true) {
			int count = in.read(buffer);
			if(count == -1) {
				break;
			}
			
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}

}
