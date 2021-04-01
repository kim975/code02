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
	<table class="table" style="width: 1100px; margin-left: auto; margin-right: auto;">
		<tr class="table-secondary" style="text-align: center; background-color: lightgreen">
			<td><strong>글번호</strong></td>
			<td><strong>작성자</strong></td>
			<td><strong>제목</strong></td>
			<td><strong>작성일</strong></td>
			<td><strong>삭제여부</strong></td>
			<td><strong>삭제</strong></td>
		</tr>
		<!-- 등록된 글 없을 때 나오는 문장 작동안함 [] 이렇게 표시됨 -->
		<c:choose>
			<c:when test="${articlesList == null } || ${articlesList ==''}">
				<tr height="10">
					<td colspan="4">
						<p align="center">
							<b><span style="font-size: 9px;">등록된 글이 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${articlesList != null }">
				<c:forEach var="article" items="${articlesList }">
					<tr align="center">
						<td width="5%">${article.articleNO }</td>
						<td width="10%">${article.id }</td>
						<td align="left" width="35%">
							<span style="padding-right: 30px;"></span>
							<a class="cls1" href="/code02/board/viewArticle?articleNO=${article.articleNO }">${article.title }</a>
						</td>
						<td width="10%">
							<fmt:formatDate value="${article.writeDate }"/>
						</td>
						<td width="10%">${article.deleteFlag }</td>
						<td>
							<a href="/code02/admin/removeArticle?articleNO=${article.articleNO }">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		<tr>
			<td><a href="/code02/board/articleForm">글쓰기</a></td>
		</tr>
	</table>
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