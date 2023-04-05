<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/jsp/header.jsp"%>
<div class="mx-auto" style="width: 800px;">
<h1 class="mt-5">공지쓰기</h1>
<hr>
<form role="form" action="/board/adminRegister" method="post">
  <div class="form-group">
    <label for="exampleFormControlInput1">제목</label>
    <input type="text" class="form-control" id="title" name='title' placeholder="제목을 입력하세요">
  </div>
  
  <div class="custom-file">
  <input type="file" class="custom-file-input" id="customFile">
  <label class="custom-file-label" for="customFile">파일첨부</label>
</div>
  
 
  <div class="form-group">
    <label for="exampleFormControlSelect2">게시판 선택</label>
    <select multiple class="form-control" id="btype" name='btype'>
      <option value="공지사항">공지사항</option>
      <option value="전세사기정보">전세사기정보</option>
      <option value="거래피해사례">거래피해사례</option>
	
    
    </select>
  </div>
  
        
  <div class="form-group">
    <label for="exampleFormControlTextarea1">본문</label>
    
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="5" name='content'></textarea>
  </div>
  
   <div class="form-group">
    	공지만료일자: <input type="text" id="datePicker" name='expireDate'>
  </div> 

  
 	 <input type="hidden" id="postId" name='writer' value="관리자" /> 
    <button type="submit" class="btn btn-primary">업로드</button>
    <button type="button" onclick="location.href='adminList' " class="btn btn-danger ">뒤로가기</button>
    
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
    

</div>
</body>
</html>