<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/jsp/header.jsp"%>
<h1 class="text-center font-weight-bold mt-5">공지관리</h1>
<div class="mx-auto text-center">
<button type="button" onclick="location.href='adminRegister' " class="btn btn-primary btn-lg  mt-3">글쓰기</button>
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
                		<a  class="list-group-item-action active"href='/board/adminGet?bno=<c:out value="${board.bno}"/>'>
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
					<nav aria-label="Page navigation example">
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