<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/resources/include/top.jsp"/>
	<table class="table" style="width: 1100px; margin-left: auto; margin-right: auto;" >
		<tr class="table-secondary" style="text-align: center;">
			<td><strong>글번호</strong></td>
			<td><strong>제목</strong></td>
			<td><strong>조회수</strong></td>
			<td><strong>작성자</strong></td>
			<td><strong>작성일</strong></td>
		</tr>
		<!-- 등록된 글 없을 때 나오는 문장 작동안함 [] 이렇게 표시됨 -->
		<c:choose>
			
			<c:when test="${not empty articlesList}">
				<c:forEach var="article" items="${articlesList }">
					<tr align="center">
						<td width="5%">${article.articleNO }</td>
						<td align="left" width="35%">
							<span style="padding-right: 30px;"></span>
							<a class="cls1 " style="text-decoration:none;" href="/code02/board/viewArticle?articleNO=${article.articleNO }">${article.title }</a>
						</td>
						<td width="10%">${article.viewCount }</td>
						<td width="10%">${article.id }</td>
						<td width="10%">
							<fmt:formatDate value="${article.writeDate }"/>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr height="10">
					<td colspan="4">
						<p align="center">
							<b><span style="font-size: 9px;">등록된 글이 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="2" style="border: 0px solid;" >
				<form action="/code02/board/listArticles" method="get" >
					<div class="input-group mb-3">
						<select class="form-select" name="searchType">
							<option value="writer">아이디</option>
							<option value="article">제목</option>
						</select>
					  	<input type="text" class="form-control" name="searchValue">
					  	<input type="submit" class="btn btn-outline-secondary" value="검색">
					</div>
				</form>
			</td>
			<td colspan="2" style="text-align: right; border-bottom: 0px;"><a class="btn btn-secondary" href="/code02/board/articleForm">글쓰기</a></td>
		</tr>
	</table>
	
<jsp:include page="/resources/include/bottom.jsp"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

	$(function(){
	    var responseMessage = "<c:out value="${msg}" />";

	    if(responseMessage != ""){
	        alert(responseMessage);
	    }
	})
	
</script>
</body>
</html>