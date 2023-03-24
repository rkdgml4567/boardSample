<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${path}/resources/jquery/jquery-3.5.1.js"></script>
<link href="${path}/resources/css/bootstrap.css" rel="stylesheet" type="text/css">
<script src="${path}/resources/js/bootstrap.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<title>Insert title here</title>

<!-- bootStrap-->

</head>
<body>
<form role="form" action="/board/sample" method="post">
  <div class="form-group">
    <label for="exampleFormControlInput1">제목</label>
    <input type="text" class="form-control" id="title" name='sample' placeholder="제목을 입력하세요">
  </div>
  
  <div class="custom-file">
  <input type="file" class="custom-file-input" id="customFile">
  <label class="custom-file-label" for="customFile">파일첨부</label>
</div>
  
 
  <div class="form-group">
    <label for="exampleFormControlSelect2">게시판 선택</label>
    <select multiple class="form-control" id="bType" name='sample2'>
      <option value="announcement">공지사항</option>
      <option value="landInfo">전세사기정보</option>
      <option value="tradeInfo">거래피해사례</option>
	
    
    </select>
  </div>
  
        
  <div class="form-group">
    <label for="exampleFormControlTextarea1">본문</label>
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="5" name='sample3'></textarea>
  </div>
  
   <div class="form-group">
    	공지만료일자: <input type="date" id="datePicker" name='sample4'>
  </div> 
  <input type="date" id="123" name='sample4'>
  
 	 <input type="hidden" id="postId" name='sample5' value="Admin" /> -->
    <button type="submit" class="btn btn-primary">업로드</button>
    <button type="button" onclick="location.href='AnnouncementList' " class="btn btn-danger ">뒤로가기</button>
    
    </form> 
    
     <script type="text/javascript">
    
    $("#datePicker").datepicker({
    	changeMonth:true,
    	dateFormat:"yy-mm-dd",
		dayNames:['월요일','화요일','수요일','목요일','금요일','토요일','일요일'],
		dayNamesMin:['월','화','수','목','금','토','일'],
		monthNameShort:['1','2','3','4','5','6','7','8','9','10','11','12'],
		monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
    });
    
   
  
	</script>    
</body>
</html>