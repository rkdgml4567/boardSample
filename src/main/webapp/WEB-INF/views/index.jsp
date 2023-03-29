<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 추가할부분 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>부동산종합정보</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="/land/resources/css/common.css">
</head>
<body>
	<!-- Body container START -->
	<div id="container"
		style="background: url('/land/resources/images/main.jpg');">
		<!-- Header START -->
		<header>
			<header id="header" >
				<%@include file ="/resources/jsp/headerMain.jsp" %>
			</header>
			
			<!-- Header END -->
		</header>

		<!-- Center START -->

		<div id="contents200">
			<div id="searchDiv">
				<P>The time on the server is ${serverTime} .하하하하</P>
				<P>test1 = ${test1} .하하하하</P>
				<P>test2 = ${test2} .하하하하</P>
			</div>
		</div>
		<!-- Center END -->

		<div id="contents400">
			<div id="tabMenu">
				<input type="radio" id="tab1" name="tabs" checked> <label
					for="tab1">공지사항</label> <input type="radio" id="tab2" name="tabs">
				<label for="tab2">전세사기예방</label>
				<div id="notice" class="tabContent">
					<h2>공지사항 내용입니다.</h2>
					<ul>
						<li>사무실을 이전했습니다</li>
						<li>[참가 모집] 카약 체험에 초대합니다.</li>
						<li>[참가 모집] 여름 방학 기간, 오름 체험단을 모집합니다.</li>
						<li>겨울, 추천 여행지</li>
						<li>가을, 추천 여행지</li>
					</ul>
				</div>
				<div id="gallery" class="tabContent">
					<h2>전세사기예방 내용입니다.</h2>
					<ul>
						<li><img src="resources/images/img-1.jpg"></li>
						<li><img src="resources/images/img-2.jpg"></li>
						<li><img src="resources/images/img-3.jpg"></li>
						<li><img src="resources/images/img-1.jpg"></li>
						<li><img src="resources/images/img-2.jpg"></li>
						<li><img src="resources/images/img-3.jpg"></li>
					</ul>
				</div>
			</div>
			<div id="links">
				<ul>
					<li><a href="#"> <span id="quick-icon1"></span>
							<p>시세</p>
					</a></li>
					<li><a href="#"> <span id="quick-icon2"></span>
							<p>정보</p>
					</a></li>
					<li><a href="#"> <span id="quick-icon3"></span>
							<p>통계</p>
					</a></li>
				</ul>
			</div>
		</div>
		<footer>
			<%@include file ="/resources/jsp/footer.jsp" %>
		</footer>
	</div>
	<!-- Body container End -->
</body>
</html>
