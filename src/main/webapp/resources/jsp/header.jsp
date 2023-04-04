<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 추가할부분 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>부동산종합정보</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <link rel="stylesheet" href="/resources/css/common.css">
</head>
<body>
	<!-- Body container START -->
	<div id="container">
		<!-- Header START -->
		<header>
			<div id="logo">
				<a href="/">
					<h1>부동산종합정보</h1>
				</a>
			</div>
			<nav>
				<ul id="topMenu">
					<li><a href="#">시세 <span>▼</span></a>
						<ul>
							<li><a href="/prices/apt">아파트</a></li>
							<li><a href="/prices/effApt">오피스텔</a></li>
						</ul></li>
					<li><a href="#">정보 <span>▼</span></a>
						<ul>
							<li><a href="/info/news/list">뉴스</a></li>
							<li><a href="#">블로그</a></li>
						</ul></li>
					<li><a href="#">통계</a></li>
					<li><a href="#">커뮤니티</a>
						<ul>
							<li><a href="#">공지사항</a></li>
							<li><a href="#">전세사기정보</a></li>
						</ul></li>
				</ul>
			</nav>
			<!-- Header END -->
		</header>
	</div>
	<!-- Body container End -->
</body>
</html>
