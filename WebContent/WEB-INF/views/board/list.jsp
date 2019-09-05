<%@ page contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n/board" />
<%@ taglib prefix="jk" tagdir="/WEB-INF/tags" %>
<html>
<jsp:include page="/WEB-INF/views/include/staticFiles.jsp"/>
<body>
<jsp:include page="/WEB-INF/views/include/bodyHeader.jsp"/>
<div class="container">
	<div class="pg-opt">
	    <div class="row">
	        <div class="col-md-6 pc">
	            <h2><fmt:message key="BOARD_LIST"/> 
	            </h2>
	        </div>
	        <div class="col-md-6">
	            <ol class="breadcrumb">
	                <li><fmt:message key="BOARD"/></li>
	                <li class="active"><fmt:message key="BOARD_LIST"/></li>
	            </ol>
	        </div>
	    </div>
    </div>
	
	<form action="<c:url value='/board/boardlist'/>" method="post"
				name="boardListForm" id="boardListForm"
				enctype="multipart/	form-data" class="form-horizontal">
	<div class="content">
		<div id = "boardList"></div>
	    <table class="table table-hover table-bordered" id="List">
		<thead>
		<tr>
			<!-- td class="pc"><fmt:message key="NO"/></td-->
			<td><fmt:message key="BOARD_ID"/></td>
			<td class="pc"><fmt:message key="WRITER"/></td>
			<td><fmt:message key="SUBJECT"/></td>
			<td class="pc"><fmt:message key="WRITE_DATE"/></td>
			<td class="pc"><fmt:message key="READ_COUNT"/></td>
		</tr>
		</thead>
		<c:set var="seq" value="${(page-1)*10}" scope="page" />
		<c:forEach var="board" items="${boardList}">
		<tr>
			<c:set var="seq" value="${seq + 1}" scope="page"/>
			<!-- td class="pc">${seq}</td-->
			<td>${board.boardId}</td>
			<td class="pc">${board.writer}</td>
			<td>
			<a href='<c:url value="/board/${board.boardId}"/>'>${board.title}</a>
			</td>
			<td class="pc"><fmt:formatDate value="${board.writeDate}" pattern="YYYY-MM-dd"/></td>
			<td class="pc">${board.readCount}</td>
		</tr>
		</c:forEach>
		</table>
		
		<table class="table">
		<tr>
			<td align="left" id="nextpage">
			<!-- 여기다가 추가 -->
				<jk:paging categoryId="${categoryId}" totalPageCount="${totalPageCount}" nowPage="${page}"/>
			</td>
			<td align="right">
				<input type=hidden id="nextpage">
				<a href='<c:url value="/board/write/${categoryId}"/>'><button type="button" class="btn btn-info"><fmt:message key="WRITE_NEW_ARTICLE"/></button></a>
			</td>
		</tr>
		</table>
	</div>
	</form>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function paging() {
		
	}

	function getFormatData(date) {
		var year = date.getFullYear();
		var mon = date.getMonth()+1;
		var day = date.getDate();
		return year + "-" + mon + "-" + day;
	}
	
	function List() {
		var categoryId = 1;
		var page = 1; //페이지 변수를 1로 초기화
		var Paramdata = { 'categoryId' : ${categoryId}, 'page' : page };
		$.ajax({
					url : '/homework/board/boardlist',
					type : 'POST',
					dataType : "json",
					data : Paramdata,
					success : function(result) {
						$("#boardList").val("");
						var htmls = "";
						$(result).each(function(){
						var date = new Date(this.writeDate);
						var to = getFormatData(date);
						
						htmls += '<tr>';
						htmls += '<td>'+this.boardId+'</td>';
						htmls += '<td class="pc">'+this.writer+'</td>';
						htmls += '<td>'+this.title+'</td>';
						htmls += '<td>'+to+'</td>';
						htmls += '<td>'+this.readCount+'</td>';
						htmls += '</tr>'
						
						
						$("#boardList").html(htmls);
						}
						)
					},
					complete : function(List) {
						console.log(List);
					}
				});
	}
	$(document).ready(function() {
		List();
	})
</script>
</body>
</html>