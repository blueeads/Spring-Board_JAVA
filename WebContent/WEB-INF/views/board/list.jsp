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
    
	<div class="content">
		<div id = "boardList"></div>
		<table class="table">
		<tr>
			<td align="left" id="nextpage">
			</td>
			<td align="right">
				<input type=hidden id="nextpage">
				<a href='<c:url value="/board/write/${categoryId}"/>'><button type="button" class="btn btn-info"><fmt:message key="WRITE_NEW_ARTICLE"/></button></a>
			</td>
		</tr>
		</table>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function paging() {
		$.ajax({
			url: '/homework/board/paging',
			dataType: 'html',
			data: {'categoryId': ${categoryId}},
			type: 'POST',
			success: function(result) {
				var htmls = "";
				console.log(result);
				for(var i = 1; i <= result; i++) {
				console.log(i);
				
				if(i==result) {
					htmls += '<td class="active">';
				}else {
					htmls += '<td>';
				}
				htmls += '<button type="submit" id="page" class="btn btn-info" onclick="List('+i+');">';
				htmls += i;
				htmls += '</button>';		
				htmls += '</td>';
				
				$("#nextpage").empty().html(htmls);
				}
			},
			error : function(request, status, error) {                                           
				console.log("code = " + request.status + " message = "                             
						+ request.responseText + " error = "                                       
						+ error); // 실패 시 처리                                                       
			},                                                                                   
			complete: function(){
				console.log("complete");	
			}
		});
	}

	function getFormatData(date) {
		var year = date.getFullYear();
		var mon = date.getMonth()+1;
		var day = date.getDate();
		return year + "-" + mon + "-" + day;
	}
	
	function List(page) {
		var Paramdata = { 'categoryId' : ${categoryId}, 'page' : page};
		$.ajax({
					url : '/homework/board/boardlist/${categoryId}/'+page,
					type : 'POST',
					dataType : "json",
<<<<<<< HEAD
					contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					cache : false,
					data : Paramdata,
					success : function(result) {
						$("#boardList").val("");
						var htmls = "";	
						if(result == 0) {
							htmls += '<div>등록된 게시글이 없습니다.</div>';
						}else {
							htmls += '<table class="table table-hover table-bordered">';
							htmls += '<thead>';
							htmls += '<tr>';
							htmls += '<!-- td class="pc"><fmt:message key="NO"/></td-->';
							htmls += '<td><fmt:message key="BOARD_ID"/></td>';
							htmls += '<td class="pc"><fmt:message key="WRITER"/></td>';
							htmls += '<td><fmt:message key="SUBJECT"/>';
							htmls += '</td>';
							htmls += '<td class="pc"><fmt:message key="WRITE_DATE"/></td>';
							htmls += '<td class="pc"><fmt:message key="READ_COUNT"/></td>';
							htmls += '</tr>';
							htmls += '</thead>';
							$(result).each(function(){
							var date = new Date(this.writeDate);
							var to = getFormatData(date);
							htmls += '<tr>';
							htmls += '<td class="pc">'+this.boardId+'</td>';
							htmls += '<td class="pc">'+this.writer+'</td>';
							htmls += '<td>'
							htmls += '<a href="<c:url value="/board/'+this.boardId+'"/>">'+this.title+'</a>';
							htmls += '   ['+this.replyCount+']';
							htmls += '</td>'
							htmls += '<td>'+to+'</td>';
							htmls += '<td>'+this.readCount+'</td>';
							htmls += '</tr>';
							});
							htmls += '</table>';
						}
							$("#boardList").html(htmls);
=======
<<<<<<< HEAD
					contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					cache : false,
					data : Paramdata,
					success : function(result) {
						$("#boardList").val("");
						var htmls = "";	
						if(page == 0) {
							htmls += '<div>등록된 게시글이 없습니다.</div>';
						}else {
							htmls += '<table class="table table-hover table-bordered">';
							htmls += '<thead>';
							htmls += '<tr>';
							htmls += '<!-- td class="pc"><fmt:message key="NO"/></td-->';
							htmls += '<td><fmt:message key="BOARD_ID"/></td>';
							htmls += '<td class="pc"><fmt:message key="WRITER"/></td>';
							htmls += '<td><fmt:message key="SUBJECT"/>';
							htmls += '</td>';
							htmls += '<td class="pc"><fmt:message key="WRITE_DATE"/></td>';
							htmls += '<td class="pc"><fmt:message key="READ_COUNT"/></td>';
							htmls += '</tr>';
							htmls += '</thead>';
							$(result).each(function(){
							var date = new Date(this.writeDate);
							var to = getFormatData(date);
							htmls += '<tr>';
							htmls += '<td class="pc">'+this.boardId+'</td>';
							htmls += '<td class="pc">'+this.writer+'</td>';
							htmls += '<td>'
							htmls += '<a href="<c:url value="/board/'+this.boardId+'"/>">'+this.title+'</a>';
							htmls += '   ['+this.commentCount+']';
							htmls += '</td>'
							htmls += '<td>'+to+'</td>';
							htmls += '<td>'+this.readCount+'</td>';
							htmls += '</tr>';
							});
							htmls += '</table>';
						}
							$("#boardList").html(htmls);
=======
					contentType:"application/json",
					cache : false,
					data : Paramdata,
					success : function(result) {
						$("#boardList").val("");
						var htmls = "";	
						htmls += '<table class="table table-hover table-bordered">';
						htmls += '<thead>';
						htmls += '<tr>';
						htmls += '<!-- td class="pc"><fmt:message key="NO"/></td-->';
						htmls += '<td><fmt:message key="BOARD_ID"/></td>';
						htmls += '<td class="pc"><fmt:message key="WRITER"/></td>';
						htmls += '<td><fmt:message key="SUBJECT"/></td>';
						htmls += '<td class="pc"><fmt:message key="WRITE_DATE"/></td>';
						htmls += '<td class="pc"><fmt:message key="READ_COUNT"/></td>';
						htmls += '</tr>';
						htmls += '</thead>';
						$(result).each(function(){
						var date = new Date(this.writeDate);
						var to = getFormatData(date);
						htmls += '<tr>';
						htmls += '<td class="pc">'+this.boardId+'</td>';
						htmls += '<td class="pc">'+this.writer+'</td>';
						htmls += '<td>'
						htmls += '<a href="<c:url value="/board/'+this.boardId+'"/>">'+this.title+'</a>';
						htmls += '</td>'
						htmls += '<td>'+to+'</td>';
						htmls += '<td>'+this.readCount+'</td>';
						htmls += '</tr>';
						});
						htmls += '</table>';
						$("#boardList").html(htmls);

>>>>>>> refs/remotes/origin/master
>>>>>>> refs/remotes/origin/master
					},
					error: function(stauts, error) {
						console.log(stauts + error);
					}
				});
	}
	$(document).ready(function() {
		List(1);
		paging();
	})
</script>
</body>
</html>