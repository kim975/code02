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
<title>마이페이지</title>
</head>
<body>
	<jsp:include page="/resources/include/top.jsp"/>
	<div style="width: 500px; margin-left: auto; margin-right: auto; margin-bottom: 3px;">
		<ul class="nav nav-tabs" style="width: 500px;">
		  <li class="nav-item">
		    <a class="nav-link " aria-current="page" href="/code02/member/myPage">내정보</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link" href="/code02/member/myWriteArticle">내가 쓴 글</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link active" href="/code02/member/myWriteReply?id=${loginInfo.id }">내가 쓴 댓글</a>
		  </li>
		</ul>
	</div>
	
	<table class="table" style="width: 1100px; margin-left: auto; margin-right: auto;" >
		<tr class="table-secondary" style="text-align: center;">
			<td><strong>글번호</strong></td>
			<td><strong>내용</strong></td>
			<td><strong>작성자</strong></td>
			<td><strong>작성일</strong></td>
		</tr>
		<!-- 등록된 글 없을 때 나오는 문장 작동안함 [] 이렇게 표시됨 -->
		<c:choose>
			<c:when test="${not empty replysList}">
				<c:forEach var="reply" items="${replysList }">
				 	<!-- 아이디의 목록만 가져온게 아니라 전체목록 가져오고 그중 아이디 검사해서 출력 다음에 바꾸기 -->
					<tr align="center">
						<td width="5%">${reply.articleNO }</td>
						<td align="left" width="35%">
							<span style="padding-right: 30px;"></span>
							<a class="cls1 " style="text-decoration:none;" href="/code02/board/viewArticle?articleNO=${reply.mainParentNO }">${reply.content }</a>
						</td>
						<td width="10%">${reply.id }</td>
						<td width="10%">
							<fmt:formatDate value="${reply.writeDate }"/>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr height="10">
					<td colspan="4">
						<p align="center">
							<b><span style="font-size: 16px;">쓴 댓글이 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>