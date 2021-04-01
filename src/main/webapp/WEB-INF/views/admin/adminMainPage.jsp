<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/resources/include/top.jsp"/>
	<c:if test="${loginInfo.id != 'master'}">
		<c:redirect url="/mainPage" />
	</c:if>
	<a href="/code02/admin/memberList">회원 목록</a>
	<a href="/code02/admin/articleList">글 목록</a>
	<a href="/code02/admin/replyList">댓글 목록</a>
</body>
</html>