<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 추가할부분 -->
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<title>부동산종합정보</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/common.css' />">
<script type="text/javascript">
	/*********************************************************
	 * 초기화
	 ******************************************************** */
	function fn_init() {
		// 첫 입력란에 포커스..
		document.aptForm.searchSgg.focus();
	}

	/*********************************************************
	 * 페이징 처리 함수
	 ******************************************************** */
	function fn_linkPage(pageNo) {
		document.aptForm.pageIndex.value = pageNo;
		document.aptForm.action = "<c:url value='/prices/apt'/>";
		document.aptForm.submit();
	}
	/*********************************************************
	 * 조회 처리 함수
	 ******************************************************** */
	function fn_search() {
		
		alert($('#bjdUmd').val());
		document.aptForm.searchCd.value = $('#bjdUmd').val();
		document.aptForm.pageIndex.value = 1;
		document.aptForm.submit();
	}
	/* ********************************************************
	 * 상세회면 처리 함수
	 ******************************************************** */
	function fn_code_search(lev) {
		$( '#bjdUmd' ).empty();
	   // $( '#bjdUmd' ).append( '<option>전체</option>' );
	    
	    var sggCd =  $('#bjdSgg').val();
	    
		$.ajax({
			url :"<c:url value='/prices/selectBjdCodeList'/>"
	        ,type: "POST"
	        ,data : {"searchLevel":lev, "searchCd":sggCd}
	        ,dataType: 'json'  	   
	        ,success : function(data){
	        	var codeList = data['codeList'];
	        	var length   = codeList['list'].length;
	        	
	        	console.log('data', data);
	        	console.log('length',  length);
	        	 
	        	if(length > 0) {
	        		$.each(codeList['list'], function(i) {
	        			 $( '#bjdUmd' ).append( '<option value='+codeList['list'][i].bjdCode+'>' + codeList['list'][i].bjdName + '</option>' );  
	        		});
	        	}
			}
		    ,error: function(){
		    	//error
		    	$( '#bjdUmd' ).append( '<option>전체</option>' );
		    	console.log('error', data);
		    }
		});
		
	}
</script>
</head>
<body>
	<form name="aptForm" action="<c:url value='/prices/apt'/>" method="post" onSubmit="fn_search(); return false;">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	<input name="searchCd" type="hidden" value="<c:out value='${searchVO.searchCd}'/>">
		<!-- Body container START -->
		<div id="container">
			<!-- Header START -->
			<header>
				<%@include file="/resources/jsp/header.jsp"%>
			</header>
			<!-- Header END -->

			<!-- Center START -->
			<div id="contents_center">
				<!-- title -->
				<div id="subTitle">
					<!-- 1.title -->
					<div id="subTitle1">
						<p>아파트</p>
					</div>
					<!-- 2.search -->
					<div id="subTitle2">
						<div class="search_box">
							<!-- full width 1200px , label width 120px -->
							<ul>
								<li><label>소재지</label></li>
								<li>
									<div style="width: 480px">
										<!--시군구 -->
										<select id="bjdSgg" onChange="fn_code_search('3')">
										
										<c:forEach items="${codeList2}" var="code2" varStatus="status"  >
											<option value="<c:out value='${code2.bjdSggCode}' />"
												<c:if test="${fn:substring(searchVO.searchCd,0,5) == code2.bjdSggCode}">selected="selected"</c:if>><c:out value='${code2.bjdName}' /></option>
										</c:forEach>
										</select>
										
										<!--읍면동 -->
										<select id="bjdUmd">
										<c:forEach items="${codeList3}" var="code3" varStatus="status">
											<option value="<c:out value='${code3.bjdCode}' />"
												<c:if test="${searchVO.searchCd == code3.bjdCode}">selected="selected"</c:if>><c:out value='${code3.bjdName}' /></option>
										</c:forEach>
										</select> <input class="s_input" name="searchNm" type="text" size="35"
											title="아파트단지명" value='<c:out value="${searchVO.searchNm}"/>'
											maxlength="155">
									</div>
								</li>
								<li>
									<div style="width: 540px; text-align: right;">
										<!-- 검색키워드 및 조회버튼 -->
										<input type="submit" class="s_btn" value="초기화" title="초기화" align="right" />
									</div>
								</li>
							</ul>
							<br>
							<ul>
								<li><label>가격</label>
								</li>
								<li><div style="width: 480px">
										<select name="searchTrGbn">
											<option value=""
												<c:if test="${searchVO.searchTrGbn == ''}">selected="selected"</c:if>>전체</option>
											<!-- 게시판명 -->
											<option value="1"
												<c:if test="${searchVO.searchTrGbn == '1'}">selected="selected"</c:if>>매매</option>
											<option value="2"
												<c:if test="${searchVO.searchTrGbn == '2'}">selected="selected"</c:if>>전세</option>
											<option value="3"
												<c:if test="${searchVO.searchTrGbn == '3'}">selected="selected"</c:if>>월세</option>
										</select> <input class="s_input" name="minAmt" type="text" size="35"
											title="최소금액" value='<c:out value="${searchVO.minAmt}"/>'
											maxlength="155"> ~ <input class="s_input"
											name="maxAmt" type="text" size="35" title="최대금액"
											value='<c:out value="${searchVO.maxAmt}"/>' maxlength="155">
									</div></li>

								<li><label>면적</label></li>
								<li>
								<div style="width: 350px;">
									<input class="s_input" name="minUnit" type="text" size="35" title="최소면적" value='<c:out value="${searchVO.minUnit}"/>' maxlength="155"> 
									~ 
									<input class="s_input" name="maxUnit" type="text" size="35" title="최대면적" value='<c:out value="${searchVO.maxUnit}"/>' maxlength="155">
									
								</div>
								
								</li>
								<li><div style="width: 60px; text-align: right;">
								<input type="submit" class="s_btn" style="text-align:right;width:50px" value="조 회" title="조회"  onSubmit="fn_search(); return false;"/>
								</div></li>
						
							</ul>
						</div>
					</div>
				</div>
				<!-- contents -->
				<div id="subContents">
					<!-- <div id="subContents1"> -->
					<!-- 상단 -->
					<div style="height: 600px;">
						<!-- 목록영역 -->
						<table class="tbl_note">
							<colgroup>
								<col style="width: 20%;">
								<col style="width: 20%;">
								<col style="width: 20%;">
								<col style="width: 20%;">
								<col style="width: 20%;">
							</colgroup>

							<thead>
								<tr>
									<!-- 소재지 -->
									<th>소재지<br>단지명</th>
									<!-- 공급면적 -->
									<th>공급면적㎡(평)</th>
									<!-- 매매시세 -->
									<th>매매시세(만원)</th>
									<!-- 전세시세 -->
									<th>전세시세(만원)</th>
									<!-- 월세시세 -->
									<th>월세시세<br>(보증금/시세)</th>
								</tr>
							</thead>
							<tbody class="tbl_list">
								<c:if test="${fn:length(resultList) == 0}">
									<tr>
										<td colspan="5">조회된데이터가 없습니다.</td>
									</tr>
								</c:if>
								<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
									<tr>
										<td><c:out value='${resultInfo.bjdCode}' /><br> 
										    <c:out value='${resultInfo.kaptName}' /></td>
										<td><c:out value='${resultInfo.areaUnit}' /></td>
										<td><c:out value='${resultInfo.trAmt}' /></td>
										<td><c:out value='${resultInfo.depositAmt}' /></td>
										<td><c:out value='${resultInfo.rentAmt}' /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- 하단 - paging navigation -->
					<div style="height: 100px;">
						<div class="pagination">
							<ul>
								<ui:pagination paginationInfo="${paginationInfo}" jsFunction="fn_linkPage" type="image" />
							</ul>
						</div>
					</div>
					<!-- 하단 End- paging navigation -->
				</div>
				<!-- SubContents END -->
			</div>
			<!-- Center END -->

			<!-- footer START -->
			<footer>
				<%@include file="/resources/jsp/footer.jsp"%>
			</footer>
			<!-- footer End -->
		</div>
		<!-- Body container End -->
	</form>
</body>
</html>