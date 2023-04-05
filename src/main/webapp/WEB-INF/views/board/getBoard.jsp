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
	<script src="/resources/jquery/jquery-3.5.1.js"></script>
<link href="/resources/css/bootstrap.css" rel="stylesheet">
<script src="/resources/js/bootstrap.js"></script>
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

		<!-- Body container START -->
		<div id="container">
			<!-- Header START -->
			<header>
				<%@include file="/resources/jsp/header.jsp"%>
			</header>
			<!-- Header END -->


					<div id="subTitle1">
						<p>게시글</p>
					</div>
		</div>
<section class="text-center ">

	<table class="table table-sm table-bordered w-50 mx-auto mt-5">

  <tbody class="font-weight-bold">
  
  <tr>
      <td class="bg-primary text-white w-50 text-dark text-center">NO</td>
      <td><c:out value="${get.bno}"></c:out></td>

    </tr>
  
    <tr>
      <td class="bg-primary text-white w-50 text-dark text-center">등록일</td>
      <td><fmt:formatDate pattern="yyyy-MM-dd" value="${get.regDate}" /></td>

    </tr>
    <tr> 
      <td class="bg-primary text-white w-50 text-dark text-center ">작성자</td>
      <td><c:out value="${get.writer}"></c:out></td>

    </tr>
   
  </tbody>
</table>
    <h2 class="mt-5"><c:out value="${get.title}"></c:out></h2>

    
   	
</section>

<p class="text-justify w-50 mx-auto mt-5"><c:out value="${get.content}"></c:out></p>
<div class="col text-center">
<button type="button" onclick="javascript:window.history.back(); " class="btn btn-danger fixed-bottom ">뒤로가기</button>
</div>
</body>
</html>