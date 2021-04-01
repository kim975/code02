<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
	<jsp:include page="/resources/include/top.jsp"/>
	<table class="table" style="width: 1100px; margin-left: auto; margin-right: auto;">
		<tr class="table-secondary" style="text-align: center;">
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>생일</td>
			<td>이메일</td>
			<td>핸드폰</td>
			<td>가입일</td>
			<td>회원 관리</td>
			<td>삭제</td>
			<td>탈퇴 여부</td>
		</tr>
		
		<c:choose>
			<c:when test="${membersList != null }">
				<c:forEach var="member" items="${membersList }">
					<tr>
						<td>${member.id }</td>
						<td>${member.password }</td>
						<td>${member.name }</td>
						<td><fmt:formatDate value="${member.birthDay }" pattern="yyyy-MM-dd"/></td>
						<td>${member.email }</td>
						<td>${member.phoneNumber }</td>
						<td><fmt:formatDate value="${member.joinDate }" pattern="yyyy-MM-dd"/></td>
						<td><a href="/code02/member/myPage?id=${member.id }">수정</a></td>
						<td><a href="/code02/admin/removeMember?id=${member.id }">삭제</a></td>
						<td>${member.deleteFlag }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${membersList == null }">
				<tr>
					<th colspan="10">등록된 회원이 없습니다.</th>
				</tr>
			</c:when>
		</c:choose>
	</table>
	
</body>
</html>