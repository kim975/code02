<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<jsp:include page="/resources/include/top.jsp"/>
	<%-- <c:import url="/resources/include/top.jsp"></c:import> --%>
	
	<form name="frmMember" onsubmit="return false;">
		<div class="" style="width: 500px; height:300px; margin-left: auto; margin-right: auto;">
			<h2>로그인</h2>
			<div class="mb-3">
				<input type="text" name="id" id="id" class="form-control class1" placeholder="아이디">
				<div class="form-text" id="messageId"></div>
			</div>
			<div class="mb-3">
				<input type="password" name="password" id="password" class="form-control class1" placeholder="비밀번호">
				<div class="form-text" id="messagePassword"></div>
				<c:if test="${message != null }">
					<div class="form-text">${message }</div>
				</c:if>
			</div>
			<input class="btn btn-primary" type="submit" value="로그인" onclick="fn_sendMember()">
		</div>
	</form>
	
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	
	var frmMember = document.frmMember;
	var id = frmMember.id.value;
	var password = frmMember.password.value;
	
	$(".class1").click(function() {
		id = frmMember.id.value;
		password = frmMember.password.value;
	});
	
	$(".class1").change(function() {
		id = frmMember.id.value;
		password = frmMember.password.value;
	});
	
	$("#id").click(function() {
		if(id.length == 0 || id == "") {
			$('#messageId').text("아이디를 입력해주세요.");
		}
	});
	
	$("#id").change(function() {
		if(id.length != 0 && id != "") {
			$('#messageId').text("");
		}
	});
	
	$("#password").click(function() {
		if(password.length == 0 || password == "") {
			$('#messagePassword').text("비밀번호를 입력해주세요.");
		}
	}); 
	
	$("#password").change(function() {
		if(password.length != 0 && id != "") {
			$('#messagePassword').text("");
		}
	}); 
	
	function fn_sendMember() {
		
		if(id.length == 0 || id == "") {
			alert("아이디는 필수입니다.");
			
		} else if(password.length == 0 || password == "") {
			alert("비밀번호는 필수입니다.");
			
		} else {
			frmMember.method = "post";
			frmMember.action = "/code02/member/login";
			frmMember.submit();
		} 
	}
</script>
</body>
</html>