<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MY CODE</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous"> -->
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
					<td colspan="5">
						<p align="center">
							<b><span style="font-size: 9px;">등록된 글이 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr class="table-borderless">
			<td colspan="5" class="table-borderless" style="text-align: center;">
				<a class="btn btn-secondary" href="/code02/board/listArticles">전체글보기</a>
			</td>
		</tr>
	</table>
	
	<jsp:include page="/resources/include/bottom.jsp"/>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
	
</body>
</html>