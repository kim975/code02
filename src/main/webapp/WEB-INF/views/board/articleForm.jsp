<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 창</title>
</head>
<body>
	<jsp:include page="/resources/include/top.jsp"/>
	<h1 style="text-align: center;">새 글 쓰기</h1>
	
	<form name="articleForm" method="post" enctype="multipart/form-data">
		<table class="table" style="width: 1000px; margin-left: auto; margin-right: auto; ">
			<tr>
				<td>작성자 :</td>
				<td colspan="2">
					<input type="text" style="width: 60%" maxlength="500" name="id" value="${loginInfo.id }" readonly>
				</td>
			</tr>
			<tr>
				<td>글제목 :</td>
				<td colspan="2">
					<input type="text" style="width: 60%" maxlength="500" name="title">
				</td>
			</tr>
			
			<tr>
				<td valign="top"><br>글내용 :</td>
				<td colspan="2">
					<textarea name="content" style="width: 60%; height: 400px; resize: none;" maxlength="4000" ></textarea>
				</td>
			</tr>
			
			<tr>
				<td>이미지파일 첨부 :</td>
				<td>
					<input type="file" name="imageFileName" onchange="readURL(this);">
				</td>
				<td><img id="preview" src="" width="200" height="100"></td>
			</tr>
			<tr style="text-align: center;">
				<td colspan="3">
					<input type="button" onclick="fn_sendArticle()" value="글쓰기">
					<input type="button" value="목록보기" onclick="backToList(this.form)">
				</td>
			</tr>
		</table>
	</form>
	
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

	function readURL(input) {
		
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function (e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	function backToList(obj) {
		obj.method = "get";
		obj.action = "/code02/board/listArticles";
		obj.submit();
	}
	
	function fn_sendArticle() {
		var articleForm = document.articleForm;
		var title = articleForm.title.value;
		var content = articleForm.content.value;
		
		if(title == "" || title.length == 0) {
			alert("제목을 입력해주세요");
			
		} else if(content == "" || content.length == 0) {
			alert("내용을 입력해주세요");
			
		} else {
			articleForm.method = "post";
			articleForm.action = "/code02/board/addArticle";
			articleForm.submit();
		} 
	}
	
</script>
</body>
</html>