<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	textarea {
		resize: none;
	}
</style>
</head>
<body>
	<jsp:include page="/resources/include/top.jsp"/>
	<div style="width:1000px; margin-left: auto; margin-right: auto;">
	<form name="articleForm" method="post" enctype="multipart/form-data">
		<table class="table">
			<tr class="table-secondary">
				<td width="20%" align="center">글번호</td>
				<td>
					<input type="text" value="${article.articleNO }" name="articleNO" disabled style="border:none;">
					<input type="hidden" name="articleNO" value="${article.articleNO }">
				</td>
				<td align="center">조회수</td>
				<td>${article.viewCount }</td>
			</tr>
			
			<tr>
				<td width="20%" align="center">작성자 아이디</td>
				<td colspan="3">
					<input type="text" value="${article.id }" name="id" id="i_id" disabled style="border: none; background: transparent;">
				</td>
			</tr>
			
			<tr>
				<td width="20%" align="center">글제목</td>
				<td colspan="3">
					<input type="text" value="${article.title }" name="title" id="i_title" disabled style="border: none; background: transparent; width: 100%;">
				</td>
			</tr>
			
			<tr>
				<td width="20%" align="center"><br>글내용</td>
				<td colspan="3">
					<textarea rows="20" cols="120" name="content" id="i_content" disabled style="resize: none;">${article.content }</textarea>
				</td>
			</tr>
			
			<tr>
				<td width="20%" align="center" rowspan="2" >이미지</td>
				<c:if test="${not empty article.imageFileName && article.imageFileName != 'null' }">
					<td colspan="3">
						<input type="hidden" name="originalFileName" value="${article.imageFileName }">
						<img width="200px" height="100px" src="/code02/download?imageFileName=${article.imageFileName }
									 			  &articleNO=${article.articleNO}" id="preview">
					</td>
				</c:if>
				<c:if test="${empty article.imageFileName || article.imageFileName == 'null' }">
					<td><img id="preview" src="" width="200" height="100"></td>
				</c:if>
			</tr>
			<tr>
				<td><input type="file" name="imageFileName" id="i_imageFileName" disabled onchange="readURL(this);"></td>
			</tr>
			
			<tr>
				<td width="20%" align="center">등록일자</td>
				<td colspan="3">
					<input type="text" value="${article.writeDate }" name="writeDate" id="i_writeDate" disabled>
				</td>
			</tr>
			
			<tr >
				<td colspan="4" align="center">
	 				<input type=button value="추천 ${conuntRecommend}" onClick="fn_recommend(this.form)">
	 				<input type="hidden" value="${loginInfo.id}" name="userId">
	 				<input type="hidden" value="${article.articleNO }" name="boardArticleNO">
				</td> 
			</tr>
			
			<tr id="tr_btn_modify" style="display: none;">
				<td colspan="4" align="center">
	 				<input type=button value="수정반영하기" onClick="fn_modify_article(this.form)">
					<input type=button value="취소" onClick="backToList(this.form)">
				</td>
			</tr>
			<c:if test="${loginInfo.id == article.id }">
				<tr id="tr_btn">
					<td colspan="4" align="center">
		 				<input type=button value="수정하기" onClick="fn_enable(this.form)">
						<input type=button value="삭제하기" onClick="fn_remove_article(this.form)">
					</td>
				</tr>
			</c:if>
			
		</table>
	</form>
	
	<br>
	
	<table class="table " style="width:1000px; margin-left: auto; margin-right: auto;">
		<tr>
			<td class="table-secondary" colspan="3" style="text-align: center;">댓글</td>
		</tr>
		<c:if test="${loginInfo.id != null}">
			<tr>
				<form id="reflyForm" enctype="multipart/form-data">
					<input type="hidden" name="mainParentNO" value="${article.articleNO }">
					<input type="hidden" name="id" value="${loginInfo.id }">
					
					<td width="15%" style="text-align: center;">
						<div >
							${loginInfo.id }
						</div>
					</td>
					<td >
						<div>
							<textarea name="content" rows="4" cols="70" style="width:100%; resize: none;" ></textarea>
						</div>
					</td>
					<td width="15%" style="text-align: center;">
						<div>
							<p><input type="button" onclick="addReply()" value="댓글 등록"></p>
						</div>
					</td>
				</form>
			</tr>
		</c:if>
		<c:if test="${replysList != null }">
			<c:set var="num" value="0" />
			<c:forEach var="replyList" items="${replysList }">
				<tr id="id${num }">
					<c:choose>
						<c:when test="${replyList.level > 1}">
							<form>
								<td style="width: 15%">
									<div style="margin-left: 40px">
										${replyList.id }<br>
										${replyList.writeDate }
									</div>
								</td>
								<td>
									<div>
										${replyList.content }
									</div>
								</td>
								<td>
									<div>
										<c:if test="${replyList.id == loginInfo.id }">
											<a href="">수정</a>
											<a href="">삭제</a>
										</c:if>
									</div>
								</td>
							</form>
						</c:when>
						<c:otherwise>
							<form method="get">
								<input type="hidden" value="${article.articleNO }" name="mainParentNO">
								<input type="hidden" value="${replyList.articleNO }" name="articleNO">
								<td>
									<div>
										${replyList.id }<br>
										${replyList.writeDate }
									</div>
								</td>
								<td>
									<div>
										<textarea rows="2" name="content" id="reply_content" disabled style="resize: none; width: 100%; background-color: white; border: none;">${replyList.content }</textarea>
										<%-- <input type="text" value="${replyList.content }" name="content" id="reply_content" disabled style="border:none; width: 100%; height:30px; background-color: white;"> --%> 
									</div>
								</td>
								
								<td  id="td_btn_modify" colspan="2" style="display: none;">
					 				<input type=button value="수정반영하기" onClick="fn_modify_reply(this.form)">
									<%-- <input type=button value="취소" onClick="backToList(${article.articleNO})"> --%>
									<input type=button value="취소" onClick="location.href='/code02/board/viewArticle?articleNO=${article.articleNO}';">
								</td>
								<td id="td_btn">
									<div>
										<%-- <input class="btn btn-secondry" type="button" onclick="openReply('id${num }')" value="댓글" style="padding: 2px;"> --%>
										<c:if test="${replyList.id == loginInfo.id }">
											<input type="button" onclick="fn_enable1(this.form)" value="수정">
											<input type="button" onclick="fn_remove_reply(this.form)" value="삭제">
										</c:if>
									</div>
								</td>
							</form>
						</c:otherwise>
					</c:choose>
				</tr>
				<c:set var="num" value="${num + 1}" />
			</c:forEach>
		</c:if>
	</table>
</div>
<jsp:include page="/resources/include/bottom.jsp"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>	
<script type="text/javascript">

	$(function(){
	    var responseMessage = "<c:out value="${msg}" />";
	
	    if(responseMessage != ""){
	        alert(responseMessage);
	    }
	})
	
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
		obj.action = "/code02/board/viewArticle?articleNO=${article.articleNO}";
		obj.submit();
	}
	
	function fn_enable(obj) {
		document.getElementById("i_title").disabled = false;
		document.getElementById("i_content").disabled = false;
		document.getElementById("i_imageFileName").disabled = false;
		document.getElementById("i_title").style.border = "1px solid #000000";
		document.getElementById("tr_btn_modify").style.display = "table-row";
		document.getElementById("tr_btn").style.display = "none";
	}
	
	function fn_enable1(obj) {
		var content = document.getElementById("reply_content"); 
		
		content.disabled = false;
		//content.style.background-color = "gray";
		content.style.border = "1px solid #000000";
		document.getElementById("td_btn_modify").style.display = "table-row";
		document.getElementById("td_btn").style.display = "none";
	}
	
	function fn_modify_article(obj) {
		obj.action = "/code02/board/modArticle";
		obj.submit();
		
	}
	
	function fn_remove_article(obj) {
		obj.encoding = "application/x-www-form-urlencoded";
		obj.action = "/code02/board/removeArticle";
		obj.submit();
		
	}
	
	
	function fn_reply_form(obj) {
		obj.encoding = "application/x-www-form-urlencoded";
		obj.action = "/pro17/board170308/replyForm";
		obj.submit();
		
	}
	
	function addReply() {
		
		var form = document.getElementById("reflyForm");
		
		//var board = form.comment_board.value;
		//var id = form.comment_id.value;
		var content = form.content.value;
		
		if(!content) {
			alert("내용을 입력하세요.");
			return false;
			
		} else {
			form.method = "post";
			form.action = "/code02/reply/addReply";
			form.submit();
			
		}
	}	
	
	function fn_modify_reply(obj) {
		obj.action = "/code02/reply/modReply";
		obj.submit();
		
	}
	
	function fn_remove_reply(obj) {
		obj.encoding = "application/x-www-form-urlencoded";
		obj.action = "/code02/reply/delReply";
		obj.submit();
		
	}
	
	function fn_recommend(obj) {
		obj.method = "get";
		obj.action = "/code02/board/upRecommend";
		obj.submit();
	}
	
	/* function openReply(obj) {
		 alert('#'+obj);  
		 $('#'+obj).after('<c:if test="${loginInfo.id != null}">'
			+'<tr>'
			+'	<form id="reflyForm" enctype="multipart/form-data">'
			+'	<input type="hidden" name="mainParentNO" value="${article.articleNO }">'
			+'	<input type="hidden" name="id" value="${loginInfo.id }">'
				+'	<td>'
				+'	<div >'
				+'		${loginInfo.id }'
				+'	</div>'
				+'	</td>'
				+'	<td >'
				+'		<div>'
				+'			<textarea name="content" rows="4" cols="70" style="width:100%; resize: none;" ></textarea>'
				+'		</div>'
				+'	</td>'
				+'	<td width="15%" style="text-align: center;">'
				+'		<div>'
				+'			<p><input type="button" onclick="addReply()" value="댓글 등록"></p>'
				+'		</div>'
				+'		</td>'
				+'	</form>'
				+'	</tr>'
				+'	</c:if>');
	} */
	/* function fn_remove_article(url, articleNO) {
		
		var form = document.createElement("form");
		form.setAttribute("method" , "post");
		form.setAttribute("action" , url);
		var articleNOInput = document.createElement("input");
		articleNOInput.setAttribute("type","hidden");
		articleNOInput.setAttribute("name","articleNO");
		articleNOInput.setAttribute("value",articleNO);
		
		form.appendChild(articleNOInput);
		document.body.appendChild(form);
		form.submit();
	} */
</script>
</body>
</html>