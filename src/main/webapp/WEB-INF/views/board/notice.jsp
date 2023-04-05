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
						<p>공지사항</p>
					</div>
		</div>
<div class="mx-auto text-center">

<hr>
</div>
<div class="justify-content-center text-center w-50 mx-auto">
<table class="table mt-5">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">제목</th>
      <th scope="col">작성자</th>
      <th scope="col">등록일</th>
      <th scope="col">게시판</th>
    </tr>
  </thead>
  <tbody class="font-weight-bold">
   <c:forEach items="${list}" var="board">
                	<tr>             		
                		<td><c:out value="${board.bno}"/></td>
						<td>	                		
                		<a  class="list-group-item-action active"href='/board/getBoard?bno=<c:out value="${board.bno}"/>'>
                		<c:out value="${board.title}"></c:out>
                		</a>
                		</td>
                		<td><c:out value="${board.writer}"/></td>
                		<td><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd"/></td>
                		<td><c:out value="${board.btype}"/></td>
                	</tr>
                </c:forEach>
  </tbody>
</table>
</div>
	<div class="d-flex justify-content-center">
					<nav aria-label="Page navigation example ">
 					 <ul class="pagination justify-content-center">
						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previous"><a class="page-link"
								href="${pageMaker.startPage -1}">Previous</a></li>
						</c:if>

						<c:forEach var="num" begin="${pageMaker.startPage}"
		 					end="${pageMaker.endPage}">
							<li class="page-item paginate_button ${pageMaker.cri.pageNum == num ? "active":""} ">
								<a class="page-link" href="${num}">${num}</a>
							</li>
						</c:forEach>

						<c:if test="${pageMaker.next}">
							<li class="paginate_button next"><a  class="page-link"
								href="${pageMaker.endPage +1 }">Next</a></li>
						</c:if>
					</ul>
				</nav>
	</div>
				
				
				<form id='actionForm' action="/board/adminList " method='get'>
				
				<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
				<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>

				</form>

		
		
		<script type="text/javascript">
			$(document).ready(function(){
				
				var actionForm = $("#actionForm")
				
				$(".paginate_button a").on("click",function(e){
					e.preventDefault();
					
					console.log('click');
					
					actionForm.find("input[name='pageNum']").val($(this).attr("href"));
					
					actionForm.submit();
				});
			
				
			})
		</script>


</body>
</html>