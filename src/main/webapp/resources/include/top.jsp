<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
	
	<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="/code02">MY CODE</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarCollapse">
	      <ul class="navbar-nav me-auto mb-2 mb-md-0">
	        <li class="nav-item">
	          <a href="/code02/board/listArticles" class="nav-link">게시글 전체보기</a>
	        </li>
	        <c:if test="${loginInfo.id == 'master' }">
				<li class="nav-item">
					<a href="/code02/admin/adminMainPage" class="nav-link">관리자 페이지</a>
		        </li>
			</c:if>
	      </ul>
	      <div class="d-flex">
	      	<c:choose>
				<c:when test="${sessionScope.loginInfo==null }">
					<div>
						<a href="/code02/member/loginForm" class="btn btn-secondary btn-sm m-1">로그인</a>
						<a href="/code02/member/memberForm" class="btn btn-secondary btn-sm m-1">회원가입</a>
					</div>
				</c:when>
				<c:otherwise>
					<span class="navbar-text navbar-dark">
        				${loginInfo.name }님 환영합니다
      				</span>
					<a href="/code02/member/logout"  class="btn btn-secondary btn-sm m-1">로그아웃</a><br>
					<a href="/code02/member/myPage"  class="btn btn-secondary btn-sm m-1">마이페이지</a>
					
				</c:otherwise>
			</c:choose>
	      </div>
	    </div>
	  </div>
	</nav>
	