<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="/resources/jquery/jquery-3.5.1.js"></script>
<link href="/resources/css/bootstrap.css" rel="stylesheet">
<script src="/resources/js/bootstrap.js"></script>
<title>Insert title here</title>

<!-- bootStrap-->

</head>
<body>
<h1 class="text-center font-weight-bold mt-5">공지사항</h1>
<hr>
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
      <td class="bg-primary text-white w-50 text-dark ">작성자</td>
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