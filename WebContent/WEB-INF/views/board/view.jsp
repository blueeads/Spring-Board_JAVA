<<<<<<< HEAD
<%@ page contentType="text/html; charset=utf-8"
	trimDirectiveWhitespaces="true"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%
	response.setContentType("text/html; charset=utf-8");
%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="i18n/board" />
<%@ taglib prefix="jk" tagdir="/WEB-INF/tags"%>
<html>
<jsp:include page="/WEB-INF/views/include/staticFiles.jsp" />
<body>
	<jsp:include page="/WEB-INF/views/include/bodyHeader.jsp" />
	<div class="container">
		<div class="pg-opt">
			<div class="row">
				<div class="col-md-6 pc">
					<h2>
						<fmt:message key="CONTENT" />
					</h2>
				</div>
				<div class="col-md-6">
					<ol class="breadcrumb">
						<li><fmt:message key="BOARD" /></li>
						<li class="active"><fmt:message key="CONTENT" /></li>
					</ol>
				</div>
			</div>
		</div>
		<div class="content">

			<table class="table table-bordered" style="margin-bottom: 0px">
				<tr>
					<td width="20%"><fmt:message key="BOARD_ID" /></td>
					<td width="79%">${board.boardId}</td>
				</tr>
				<tr>
					<td width="20%"><fmt:message key="WRITER" /></td>
					<td>${board.writer}</td>
				</tr>
				<tr>
					<td width="20%"><fmt:message key="WRITE_DATE" /></td>
					<td><fmt:formatDate value="${board.writeDate}"
							pattern="YYYY-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<td><fmt:message key="SUBJECT" /></td>
					<td>${board.title}</td>
				</tr>
				<tr>
					<td><fmt:message key="CONTENT" /></td>
					<td class="board_content">${board.content}</td>
				</tr>
				<tr>
					<td><fmt:message key="FILE" /></td>
					<td><c:forEach var="fileList" items="${fileList}"
							varStatus="status">
							<c:set var="len" value="${fn:length(fileList.fileOriginalName)}" />
							<c:set var="filetype"
								value="${fn:toUpperCase(fn:substring(fileList.fileOriginalName, len-4, len))}" />
							<c:if
								test="${(filetype eq '.JPG') or (filetype eq 'JPEG') or (filetype eq '.PNG') or (filetype eq '.GIF')}">
								<img style="width: auto; height: auto; max-width: 400px; max-height: 400px"
									src='<c:url value="/file/${fileList.fileId}"/>'
									class="img-thumbnail">
								<br>
							</c:if>
							<a href='<c:url value="/file/${fileList.fileId}"/>'>${fileList.fileOriginalName}
								(<fmt:formatNumber>${fileList.fileSize}</fmt:formatNumber>byte)
							</a>
							<br>
						</c:forEach></td>
				</tr>
				<tr>
					<td colspan=2 align="right"><a
						href='<c:url value="/board/cat/${categoryId}"/>'><button
								type="button" class="btn btn-info">
								<fmt:message key="BOARD_LIST" />
							</button></a> <a href='<c:url value="/board/write/${categoryId}"/>'><button
								type="button" class="btn btn-info">
								<fmt:message key="WRITE_NEW_ARTICLE" />
							</button></a>
						<button type="button" class="btn btn-info" id="boardUpdate">
							<fmt:message key="UPDATE" />
						</button>
						<button type="button" class="btn btn-info" id="boardDelete">
							<fmt:message key="DELETE" />
						</button></td>
				</tr>
			</table>

			<label for="content">comment</label>
			<form action="<c:url value='/reply/view/'/>" method="POST"
				name="commentInsertForm" id="commentInsertForm"
				enctype="multipart/	form-data" class="form-horizontal"
				onsubmit="return false"'>
				<div class="input-group">
					<input type="hidden" name="writer" id="writer"
						value="${sessionScope.userid}" class="form-control"> <input
						type="hidden" value="${board.boardId}" class="form-control"
						id="boardId" name="boardId"> <input type="text"
						class="form-control" id="content" name="content"
						placeholder="내용을 입력하세요."> <span class="input-group-btn">

						<button class="btn btn-default" type="button"
							id="commentInsertBtn" name="commentInsertBtn" value="등록">등록</button>
					</span>
				</div>
			</form>
			<div id="replyList"></div>
			<div id="paging"></div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript">	
	
	/*	변수 선언	*/
	var page = 1;
	//현재 댓글을 수정중인지 아닌지 체크하는 변수
	var updating = 0;
	/*	선언 함수	*/
	//추후 리스트함수랑 js파일로 뺄것...
	function getFormatData(date) {
		var year = date.getFullYear();
		var mon = date.getMonth()+1;
		var day = date.getDate();
		return year + "-" + mon + "-" + day;
	}
	
	//현재 페이지 값 넘기기
	function getCurrentPage(currentpage) {
		page = currentpage;
	}
	
	//로그인 화면으로 이동
	function tabLogin() {
		alert("로그인이 되어있지 않습니다.");
		location.href="/homework/member/login";
	}
	
	/*	게시글		*/
	//페이지에 따른 리스트 가져오기
	function getList(page){
		console.log("초기: "+  page);
		$.ajax({
			url: '/homework/reply/getboardReply/${board.boardId}/'+page,
			type: 'POST',
			dataType : 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			cache : false,
			data: { 'boardId': ${board.boardId}, 'page' : page },
			success : function(result) {
               	var htmls = "";
			if(result.length < 1){
				htmls += '<div>등록된 댓글이 없습니다.</div>';
			} else {
	                    htmls += '<table class="table table-bordered" style="margin-top: 15px">';
	                    htmls += '<tr>';
	                    htmls +='<td width="4%"><fmt:message key="REPLY_NO" /></td>';
	                    htmls +='<td width="10%"><fmt:message key="WRITER" /></td>';
	                    htmls +='<td width="55%"><fmt:message key="REPLY" /></td>';
	                    htmls +='<td width="8%"><fmt:message key="WRITE_DATE" /></td>';
	                    htmls +='<td width="12%"><fmt:message key="BTN_CLICK" /></td>';
	                    htmls += '</tr>';
	                    $(result).each(function(){
		                var date = new Date(this.writeDate);
						var to = getFormatData(date);
	                    htmls += '<tr style="margin-top: 5px">';
	                    htmls += '<td width="4%">'+ this.seq +'</td>';
	                    htmls += '<td width="10%" id="replyWriter'+this.commentId+'">' + this.writer +'</td>';
	                    htmls += '<td width="55%" id="UpdateContent'+this.commentId+'">'+this.content+'</td>';
	                    htmls += '<td width="8%">'+to+'</td>';
	                    htmls += '<td width="12%">';
	                    htmls += '<input type="hidden" value="'+this.commentId+'" id="replycommentId">';
	                    htmls += '<input type="hidden" value="'+content+'" id="replycontent">';
	                    htmls += '<button type="button" class="btn btn-info" id="commentUpdateBtn" name="update" onclick="commentUpdate('+this.commentId+');">수정</button>';
	                    htmls += '<button type="button" class="btn btn-info" id="commentDeleteBtn" name="delete" onclick="commentDelete('+this.commentId+');">삭제</button>';
	                    htmls += '</td>';
	                    htmls += '</tr>';
	                });	//each end
	                     htmls += '</table>';
					$("#replyList").empty().html(htmls);
			}
			$("#replyList").empty().html(htmls);
			},
			error: function(error, state) {
				console.log("getReplyList : " + error + state);
			}
		});
	}
	//페이징 그리기
	function paging() {
		$.ajax({
			url: '/homework/reply/paging',
			dataType: 'html',
			data: {'boardId': ${board.boardId}},
			type: 'POST',
			success: function(result) {
				var htmls = "";
				for(var i = 1; i <= result; i++) {
				console.log("페이징 For문 : " + i);
				
				if(i==result) {
					htmls += '<td class="active">';
				}else {
					htmls += '<td>';
				}
				htmls += '<button type="submit" class="btn btn-info" id=page onclick="getList('+i+'); getCurrentPage('+i+');">';
				htmls += i;
				htmls += '</button>';		
				htmls += '</td>';
				
				$("#paging").empty().html(htmls);
				}
			},
			error : function(request, status, error) {                                           
				console.log("code = " + request.status + " message = "                             
						+ request.responseText + " error = "                                       
						+ error); // 실패 시 처리                                                       
			}
		});
	}
	
	//댓글 삭제 - 조건(게시글을 작성한 보인이여야만 함)
	$("#boardUpdate").click(function(){
		var userid = '${sessionScope.userid}';
		var boardId = ${board.boardId};
		var admin = '${sessionScope.admin}';
		if(userid != "") {
			if(userid == '${board.writer}'){
				location.href="/homework/board/update/"+${board.boardId};
			}else if(admin == 1) {
				var result = confirm("관리자 : 다른 사람의 게시물입니다. 강제로 수정하시겠습니까?");
				if(result){
					location.href="/homework/board/update/"+${board.boardId};
				}
			}else if(userid != '${board.writer}' && admin == 0){
				alert("권한이 없습니다.");
			}
		}else {
			tabLogin();
		}
		});
	
	
	//댓글 삭제 - 조건(게시글을 작성한 보인이여야만 함)
	$("#boardDelete").click(function(){
		var userid = '${sessionScope.userid}';
		var boardId = ${board.boardId};
		var admin = '${sessionScope.admin}';

			if(userid != "") {
				if(admin == 1 && userid != '${board.writer}')	var result = confirm("관리자 : 다른 사람의 게시물입니다. 강제로 삭제하시겠습니까?");
				if(userid == '${board.writer}' || result == '1'){
					$.ajax({
						url: '/homework/board/delete/'+boardId,
						type: 'POST',
						dataType: 'html',
						success: function(){
							alert("성공적으로 게시글이 삭제되었습니다.");
							location.href="/homework/board/cat/"+${categoryId};
							},
						error: function(stauts, error) {
							alert("boardDelete : " + stauts + "/" + error);
							}
					});
				}else if(userid != '${board.writer}' && admin != '1'){
					alert("권한이 없습니다.");
				}
			}else {
				tabLogin();
		}
	});
	
	
	/*	댓글	*/
		//댓글 등록
		$("#commentInsertBtn").click(function() { //댓글 등록 버튼 클릭시 
			var insertData = $("#commentInsertForm").serialize(); //commentInsertForm의 내용을 가져옴
			var userid = '${sessionScope.userid}';
			if(userid != "") {
				if ($("#content").val().trim() === "") {
					alert("댓글을 입력해주세요.");
					$("#content").val("").focus();
				} else {
					$.ajax({
						url : '/homework/reply/view/',
						type : 'post',
						dataType : 'json',
						data : insertData,
						success : function() {
							alert("success");
						},
							error : function(request, status, error) {
								console.log("code = " + request.status + " message = "
										+ request.responseText + " error = "
										+ error); // 실패 시 처리
							},
							complete: function() {
								alert("댓글이 성공적으로 등록되었습니다.");
								$("#content").val("");
								getList(page);
							}
				});
				}
			}else {
				tabLogin();
			}
		});
		
		//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
		function commentUpdate(commentId) {
			var replywriter = document.getElementById('replyWriter'+commentId); 
			var currentwriter = '${sessionScope.userid}';
			var admin = '${sessionScope.admin}';
			if(admin == 1 && replywriter.innerHTML != currentwriter){
				var result = confirm("관리자 : 다른 사람의 댓글입니다. 강제로 수정하시겠습니까?");
			}
			if(replywriter.innerHTML == currentwriter || result == 1) {
				if(updating == 0) {					
					var content = document.getElementById('UpdateContent'+commentId).innerHTML;
					updating = 1;
					var a = '';
					a += '<div class="input-group">';
					a += '<input id="replycontent'+commentId+'" type="text" class="form-control" name="content_'+commentId+'" value="'+content+'"/>';
					a += '<span class="input-group-btn"><button id="updataProc" class="btn btn-default" type="button" onclick="commentUpdateProc('+commentId+');">수정</button> </span>';
					a += '</div>';
					$('#UpdateContent'+commentId).html(a);
				}
			}else if(replywriter.innerHTML != currentwriter && admin == 0){
				alert("권한이 없습니다.");
			}
			else if(currentwriter == "") {
				tabLogin();
			}
		}
		
		//댓글 수정 - input폼으로 수정된 내용변경
		function commentUpdateProc(commentId){
			var content = document.getElementById('replycontent'+commentId).value;
			$.ajax({
					url: '/homework/reply/update',
					type: 'POST',
					data : {'commentId': commentId, 'content' : content, 'boardId': ${board.boardId}},
					dataType : 'html',
					success: function(result) {
						alert("댓글 수정이 완료되었습니다.");
						getList(page);
						updating = 0;
					},
					error: function(stauts, error) {
						console.log("commentUpdateProc : " + stauts + "/" + error);
					}
			});
		}

		//댓글 삭제 
		function commentDelete(commentId) {
			var replywriter = document.getElementById('replyWriter'+commentId); 
			var currentwriter = '${sessionScope.userid}';
			var admin = '${sessionScope.admin}';
			if(admin == 1 && replywriter.innerHTML != currentwriter){
				var result = confirm("관리자 : 다른 사람의 댓글입니다. 강제로 삭제하시겠습니까?");
			}
			if(replywriter.innerHTML == currentwriter || result == 1) {
			$.ajax({
				url : '/homework/reply/delete/'+commentId,
				type : 'POST',
				data: {'commentId': commentId, boardId: '${board.boardId}'},
				dataType : 'text',
				success : function() {
					getList(page);
					alert("성공적으로 댓글을 삭제하였습니다.");
				}
			});
			}else if(currentwriter == "") {
				tabLogin();
			}else if(replywriter != currentwriter && admin == 0){
				alert("권한이 없습니다.");
			}
		}		
		
		//페이지가 시작되면 리스트를 받아오고, 페이징 처리한다.
=======
<<<<<<< HEAD
<%@ page contentType="text/html; charset=utf-8"
	trimDirectiveWhitespaces="true"%>
<%request.setCharacterEncoding("utf-8");%>
<%response.setContentType("text/html; charset=utf-8");%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="i18n/board" />
<%@ taglib prefix="jk" tagdir="/WEB-INF/tags"%>
<html>
<jsp:include page="/WEB-INF/views/include/staticFiles.jsp" />
<body>
	<jsp:include page="/WEB-INF/views/include/bodyHeader.jsp" />
	<div class="container">
		<div class="pg-opt">
			<div class="row">
				<div class="col-md-6 pc">
					<h2>
						<fmt:message key="CONTENT" />
					</h2>
				</div>
				<div class="col-md-6">
					<ol class="breadcrumb">
						<li><fmt:message key="BOARD" /></li>
						<li class="active"><fmt:message key="CONTENT" /></li>
					</ol>
				</div>
			</div>
		</div>
		<div class="content">

			<table class="table table-bordered" style="margin-bottom: 0px">
				<tr>
					<td width="20%"><fmt:message key="BOARD_ID" /></td>
					<td width="79%">${board.boardId}</td>
				</tr>
				<tr>
					<td width="20%"><fmt:message key="WRITER" /></td>
					<td>${board.writer}</td>
				</tr>
				<tr>
					<td width="20%"><fmt:message key="WRITE_DATE" /></td>
					<td><fmt:formatDate value="${board.writeDate}"
							pattern="YYYY-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<td><fmt:message key="SUBJECT" /></td>
					<td>${board.title}</td>
				</tr>
				<tr>
					<td><fmt:message key="CONTENT" /></td>
					<td class="board_content">${board.content}</td>
				</tr>
				<tr>
					<td><fmt:message key="FILE" /></td>
					<td><c:forEach var="fileList" items="${fileList}"
							varStatus="status">
							<c:set var="len" value="${fn:length(fileList.fileOriginalName)}" />
							<c:set var="filetype"
								value="${fn:toUpperCase(fn:substring(fileList.fileOriginalName, len-4, len))}" />
							<c:if
								test="${(filetype eq '.JPG') or (filetype eq 'JPEG') or (filetype eq '.PNG') or (filetype eq '.GIF')}">
								<img style="width: auto; height: auto; max-width: 400px; max-height: 400px"
									src='<c:url value="/file/${fileList.fileId}"/>'
									class="img-thumbnail">
								<br>
							</c:if>
							<a href='<c:url value="/file/${fileList.fileId}"/>'>${fileList.fileOriginalName}
								(<fmt:formatNumber>${fileList.fileSize}</fmt:formatNumber>byte)
							</a>
							<br>
						</c:forEach></td>
				</tr>
				<tr>
					<td colspan=2 align="right"><a
						href='<c:url value="/board/cat/${categoryId}"/>'><button
								type="button" class="btn btn-info">
								<fmt:message key="BOARD_LIST" />
							</button></a> <a href='<c:url value="/board/write/${categoryId}"/>'><button
								type="button" class="btn btn-info">
								<fmt:message key="WRITE_NEW_ARTICLE" />
							</button></a>
						<button type="button" class="btn btn-info" id="boardUpdate">
							<fmt:message key="UPDATE" />
						</button>
						<button type="button" class="btn btn-info" id="boardDelete">
							<fmt:message key="DELETE" />
						</button></td>
				</tr>
			</table>

			<label for="content">comment</label>
			<form action="<c:url value='/comment/view/'/>" method="POST"
				name="commentInsertForm" id="commentInsertForm"
				enctype="multipart/	form-data" class="form-horizontal"
				onsubmit="return false"'>
				<div class="input-group">
					<input type="hidden" name="writer" id="writer"
						value="${sessionScope.userid}" class="form-control"> <input
						type="hidden" value="${board.boardId}" class="form-control"
						id="boardId" name="boardId"> <input type="text"
						class="form-control" id="content" name="content"
						placeholder="내용을 입력하세요."> <span class="input-group-btn">

						<button class="btn btn-default" type="button"
							id="commentInsertBtn" name="commentInsertBtn" value="등록">등록</button>
					</span>
				</div>
			</form>
			<div id="commentList"></div>
			<div id="paging"></div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript">	
	
	/*	변수 선언	*/
	var page = 1;
	//현재 댓글을 수정중인지 아닌지 체크하는 변수
	var updating = 0;
	/*	선언 함수	*/
	//추후 리스트함수랑 js파일로 뺄것...
	function getFormatData(date) {
		var year = date.getFullYear();
		var mon = date.getMonth()+1;
		var day = date.getDate();
		return year + "-" + mon + "-" + day;
	}
	
	//현재 페이지 값 넘기기
	function getCurrentPage(currentpage) {
		page = currentpage;
	}
	
	//로그인 화면으로 이동
	function tabLogin() {
		alert("로그인이 되어있지 않습니다.");
		location.href="/homework/member/login";
	}
	
	/*	게시글		*/
	//페이지에 따른 리스트 가져오기
	function getList(page){
		console.log("초기: "+  page);
		$.ajax({
			url: '/homework/comment/getBoardComment/${board.boardId}/'+page,
			type: 'POST',
			dataType : 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			cache : false,
			success : function(result) {
               	var htmls = "";
			if(result.length < 1){
				htmls += '<div>등록된 댓글이 없습니다.</div>';
			} else {
	                    htmls += '<table class="table table-bordered" style="margin-top: 15px">';
	                    htmls += '<tr>';
	                    htmls +='<td width="4%"><fmt:message key="COMMENT_NO" /></td>';
	                    htmls +='<td width="10%"><fmt:message key="WRITER" /></td>';
	                    htmls +='<td width="55%"><fmt:message key="COMMENT" /></td>';
	                    htmls +='<td width="8%"><fmt:message key="WRITE_DATE" /></td>';
	                    htmls +='<td width="12%"><fmt:message key="BTN_CLICK" /></td>';
	                    htmls += '</tr>';
	                    $(result).each(function(){
		                var date = new Date(this.reg_date);
						var to = getFormatData(date);
	                    htmls += '<tr style="margin-top: 5px">';
	                    htmls += '<td width="4%">'+ this.seq +'</td>';
	                    htmls += '<td width="10%" id="commentWriter'+this.contentId+'">' + this.writer +'</td>';
	                    htmls += '<td width="55%" id="UpdateContent'+this.contentId+'">'+this.content+'</td>';
	                    htmls += '<td width="8%">'+to+'</td>';
	                    htmls += '<td width="12%">';
	                    htmls += '<input type="hidden" value="'+this.contentId+'" id="commentcontentId">';
	                    htmls += '<input type="hidden" value="'+this.content+'" id="commentcontent">';
	                    htmls += '<button type="button" class="btn btn-info" id="commentUpdateBtn" name="update" onclick="commentUpdate('+this.contentId+');">수정</button>';
	                    htmls += '<button type="button" class="btn btn-info" id="commentDeleteBtn" name="delete" onclick="commentDelete('+this.contentId+');">삭제</button>';
	                    htmls += '</td>';
	                    htmls += '</tr>';
	                });	//each end
	                     htmls += '</table>';
					$("#commentList").empty().html(htmls);
			}
			$("#commentList").empty().html(htmls);
			},
			error: function(error, state) {
				console.log("getcommentList : " + error + state);
			}
		});
	}
	//페이징 그리기
	function paging() {
		$.ajax({
			url: '/homework/comment/paging',
			dataType: 'html',
			data: {'boardId': ${board.boardId}},
			type: 'POST',
			success: function(result) {
				var htmls = "";
				for(var i = 1; i <= result; i++) {
				console.log("페이징 For문 : " + i);
				
				if(i==result) {
					htmls += '<td class="active">';
				}else {
					htmls += '<td>';
				}
				htmls += '<button type="submit" class="btn btn-info" id=page onclick="getList('+i+'); getCurrentPage('+i+');">';
				htmls += i;
				htmls += '</button>';		
				htmls += '</td>';
				
				$("#paging").empty().html(htmls);
				}
			},
			error : function(request, status, error) {                                           
				console.log("code = " + request.status + " message = "                             
						+ request.responseText + " error = "                                       
						+ error); // 실패 시 처리                                                       
			}
		});
	}
	
	//댓글 삭제 - 조건(게시글을 작성한 보인이여야만 함)
	$("#boardUpdate").click(function(){
		var userid = '${sessionScope.userid}';
		var boardId = ${board.boardId};
		var admin = '${sessionScope.admin}';
		if(userid != "") {
			if(userid == '${board.writer}'){
				location.href="/homework/board/update/"+${board.boardId};
			}else if(admin == 1) {
				var result = confirm("관리자 : 다른 사람의 게시물입니다. 강제로 수정하시겠습니까?");
				if(result){
					location.href="/homework/board/update/"+${board.boardId};
				}
			}else if(userid != '${board.writer}' && admin == 0){
				alert("권한이 없습니다.");
			}
		}else {
			tabLogin();
		}
		});
	
	
	//댓글 삭제 - 조건(게시글을 작성한 보인이여야만 함)
	$("#boardDelete").click(function(){
		var userid = '${sessionScope.userid}';
		var boardId = ${board.boardId};
		var admin = '${sessionScope.admin}';

			if(userid != "") {
				if(admin == 1 && userid != '${board.writer}')	var result = confirm("관리자 : 다른 사람의 게시물입니다. 강제로 삭제하시겠습니까?");
				if(userid == '${board.writer}' || result == '1'){
					$.ajax({
						url: '/homework/board/delete/'+boardId,
						type: 'POST',
						dataType: 'html',
						success: function(){
							alert("성공적으로 게시글이 삭제되었습니다.");
							location.href="/homework/board/cat/"+${categoryId};
							},
						error: function(stauts, error) {
							alert("boardDelete : " + stauts + "/" + error);
							}
					});
				}else if(userid != '${board.writer}' && admin != '1'){
					alert("권한이 없습니다.");
				}
			}else {
				tabLogin();
		}
	});
	
	
	/*	댓글	*/
		//댓글 등록
		$("#commentInsertBtn").click(function() { //댓글 등록 버튼 클릭시 
			var insertData = $("#commentInsertForm").serialize(); //commentInsertForm의 내용을 가져옴
			var userid = '${sessionScope.userid}';
			if(userid != "") {
				if ($("#content").val().trim() === "") {
					alert("댓글을 입력해주세요.");
					$("#content").val("").focus();
				} else {
					$.ajax({
						url : '/homework/comment/view/',
						type : 'post',
						dataType : 'json',
						data : insertData,
						success : function() {
							alert("success");
						},
							error : function(request, status, error) {
								console.log("code = " + request.status + " message = "
										+ request.responseText + " error = "
										+ error); // 실패 시 처리
							},
							complete: function() {
								alert("댓글이 성공적으로 등록되었습니다.");
								$("#content").val("");
								getList(page);
							}
				});
				}
			}else {
				tabLogin();
			}
		});
		
		//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
		function commentUpdate(contentId) {
			var commentwriter = document.getElementById('commentWriter'+contentId); 
			var currentwriter = '${sessionScope.userid}';
			var admin = '${sessionScope.admin}';
			if(admin == 1 && commentwriter.innerHTML != currentwriter){
				var result = confirm("관리자 : 다른 사람의 댓글입니다. 강제로 수정하시겠습니까?");
			}
			if(commentwriter.innerHTML == currentwriter || result == 1) {
				if(updating == 0) {					
					var content = document.getElementById('UpdateContent'+contentId).innerHTML;
					updating = 1;
					var a = '';
					a += '<div class="input-group">';
					a += '<input id="commentcontent'+contentId+'" type="text" class="form-control" name="content_'+contentId+'" value="'+content+'"/>';
					a += '<span class="input-group-btn"><button id="updataProc" class="btn btn-default" type="button" onclick="commentUpdateProc('+contentId+');">수정</button> </span>';
					a += '</div>';
					$('#UpdateContent'+contentId).html(a);
				}
			}else if(commentwriter.innerHTML != currentwriter && admin == 0){
				alert("권한이 없습니다.");
			}
			else if(currentwriter == "") {
				tabLogin();
			}
		}
		
		//댓글 수정 - input폼으로 수정된 내용변경
		function commentUpdateProc(contentId){
			var content = document.getElementById('commentcontent'+contentId).value;
			$.ajax({
					url: '/homework/comment/update',
					type: 'POST',
					data : {'contentId': contentId, 'content' : content, 'boardId': ${board.boardId}},
					dataType : 'html',
					success: function(result) {
						alert("댓글 수정이 완료되었습니다.");
						getList(page);
						updating = 0;
					},
					error: function(stauts, error) {
						console.log("commentUpdateProc : " + stauts + "/" + error);
					}
			});
		}

		//댓글 삭제 
		function commentDelete(contentId) {
			var commentwriter = document.getElementById('commentWriter'+contentId); 
			var currentwriter = '${sessionScope.userid}';
			var admin = '${sessionScope.admin}';
			if(admin == 1 && commentwriter.innerHTML != currentwriter){
				var result = confirm("관리자 : 다른 사람의 댓글입니다. 강제로 삭제하시겠습니까?");
			}
			if(commentwriter.innerHTML == currentwriter || result == 1) {
			$.ajax({
				url : '/homework/comment/delete/'+contentId,
				type : 'POST',
				data: {'contentId': contentId, boardId: '${board.boardId}'},
				dataType : 'text',
				success : function() {
					getList(page);
					alert("성공적으로 댓글을 삭제하였습니다.");
				}
			});
			}else if(currentwriter == "") {
				tabLogin();
			}else if(commentwriter != currentwriter && admin == 0){
				alert("권한이 없습니다.");
			}
		}		
		
		//페이지가 시작되면 리스트를 받아오고, 페이징 처리한다.
=======
<%@ page contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="i18n/board" />
<%@ taglib prefix="jk" tagdir="/WEB-INF/tags" %>
<html>
<jsp:include page="/WEB-INF/views/include/staticFiles.jsp" />
<body>
	<jsp:include page="/WEB-INF/views/include/bodyHeader.jsp" />
	<div class="container">
		<div class="pg-opt">
			<div class="row">
				<div class="col-md-6 pc">
					<h2>
						<fmt:message key="CONTENT" />
					</h2>
				</div>
				<div class="col-md-6">
					<ol class="breadcrumb">
						<li><fmt:message key="BOARD" /></li>
						<li class="active"><fmt:message key="CONTENT" /></li>
					</ol>
				</div>
			</div>
		</div>
		<div class="content">

			<table class="table table-bordered" style="margin-bottom: 0px">
				<tr>
					<td width="20%"><fmt:message key="BOARD_ID" /></td>
					<td width="79%">${board.boardId}</td>
				</tr>
				<tr>
					<td width="20%"><fmt:message key="WRITER" /></td>
					<td>${board.writer}</td>
				</tr>
				<tr>
					<td width="20%"><fmt:message key="WRITE_DATE" /></td>
					<td><fmt:formatDate value="${board.writeDate}"
							pattern="YYYY-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<td><fmt:message key="SUBJECT" /></td>
					<td>${board.title}</td>
				</tr>
				<tr>
					<td><fmt:message key="CONTENT" /></td>
					<td class="board_content">${board.content}</td>
				</tr>
				<c:if test="${!empty board.fileName}">
					<tr>
						<td><fmt:message key="FILE" /></td>
						<td>
							<%--c:if test="${!empty sessionScope.userid}"--%> 
							<c:set var="len" value="${fn:length(board.fileName)}" /> 
							<c:set var="filetype" value="${fn:toUpperCase(fn:substring(board.fileName, len-4, len))}" />
							<c:if
								test="${(filetype eq '.JPG') or (filetype eq 'JPEG') or (filetype eq '.PNG') or (filetype eq '.GIF')}">
								<img src='<c:url value="/file/${board.fileId}"/>'
									class="img-thumbnail">
								<br>
							</c:if> <%--/c:if--%> <a href='<c:url value="/file/${board.fileId}"/>'>${board.fileName}
								(<fmt:formatNumber>${board.fileSize}</fmt:formatNumber>byte)
						</a>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan=2 align="right"><a
						href='<c:url value="/board/cat/${categoryId}"/>'><button
								type="button" class="btn btn-info">
								<fmt:message key="BOARD_LIST" />
							</button></a> <a href='<c:url value="/board/write/${categoryId}"/>'><button
								type="button" class="btn btn-info">
								<fmt:message key="WRITE_NEW_ARTICLE" />
							</button></a> <a href='<c:url value="/board/update/${board.boardId}"/>'><button
								type="button" class="btn btn-info">
								<fmt:message key="UPDATE" />
							</button></a><button
								type="button" class="btn btn-info" id = "boardDelete" >
								<fmt:message key="DELETE" />
							</button></td>
				</tr>
			</table>

			<label for="content">comment</label>
			<form action="<c:url value='/reply/view'/>" method="post"
				name="commentInsertForm" id="commentInsertForm"
				enctype="multipart/	form-data" class="form-horizontal">
				<div class="input-group">
					<input type="hidden" name="writer" id="writer" value="${sessionScope.userid}" class="form-control">
						<input type="hidden" value="${board.boardId}" class="form-control" id="bno" name="bno"> <input type="text" class="form-control" id="content" name="content"
						placeholder="내용을 입력하세요."> <span class="input-group-btn">
						<button class="btn btn-default" type="button"
							id="commentInsertBtn" name="commentInsertBtn">등록</button>
					</span>
				</div>
			</form> 
			<div id="replyList"></div>
			<div id="paging"></div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" charset="UTF-8">	
	var page = 1;
	function paging() {
		$.ajax({
			url: '/homework/reply/paging',
			dataType: 'html',
			data: {'bno': ${board.boardId}},
			type: 'POST',
			success: function(result) {
				var htmls = "";
				for(var i = 1; i <= result; i++) {
				console.log("페이징 For문 : " + i);
				
				if(i==result) {
					htmls += '<td class="active">';
				}else {
					htmls += '<td>';
				}
				htmls += '<button type="submit" id=page onclick="getList('+i+'); getCurrentPage('+i+');">';
				htmls += i;
				htmls += '</button>';		
				htmls += '</td>';
				
				$("#paging").empty().html(htmls);
				}
			},
			error : function(request, status, error) {                                           
				console.log("code = " + request.status + " message = "                             
						+ request.responseText + " error = "                                       
						+ error); // 실패 시 처리                                                       
			}
		});
	}
	
	function getCurrentPage(currentpage) {
		page = currentpage;
	}
	
		$("#commentInsertBtn").click(function() { //댓글 등록 버튼 클릭시 
			var insertData = $("#commentInsertForm").serialize(); //commentInsertForm의 내용을 가져옴
			var userid = '${sessionScope.userid}';
			if(userid != "") {
				if ($("#content").val().trim() === "") {
					alert("댓글을 입력해주세요.");
					$("#content").val("").focus();
				} else {
					$.ajax({
						url : '/homework/reply/view',
						type : 'post',
						dataType : 'json',
						data : insertData,
						success : function(data) {
						},
							//error : function(request, status, error) {
							//	console.log("code = " + request.status + " message = "
							//			+ request.responseText + " error = "
							//			+ error); // 실패 시 처리
							//},
							complete : function() {
								alert("댓글이 성공적으로 등록되었습니다.");
								$("#content").val("");
								getList(page);
							}
				});
				}
			}else {
				alert("로그인이 되어있지 않습니다.");
				location.href="/homework/member/login"
			}
		});
		
		//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
		function commentUpdate(cno) {
			var content = document.getElementById('UpdateContent'+cno).innerHTML;
			var a = '';
			a += '<div class="input-group">';
			a += '<input id="replycontent'+cno+'" type="text" class="form-control" name="content_'+cno+'" value="'+content+'"/>';
			a += '<span class="input-group-btn"><button id="updataProc" class="btn btn-default" type="button" onclick="commentUpdateProc('+cno+');">수정</button> </span>';
			a += '</div>';
			$('#UpdateContent'+cno).html(a);
		}
		
		function commentUpdateProc(cno){
			var content = document.getElementById('replycontent'+cno).value;
			$.ajax({
					url: '/homework/reply/update',
					type: 'POST',
					data : {'cno': cno, 'content' : content, 'bno': ${board.boardId}},
					dataType : 'html',
					success: function(result) {
						alert("댓글 수정이 완료되었습니다.");
						getList(page);
					},
					error: function(stauts, error) {
						console.log("commentUpdateProc : " + stauts + "/" + error);
					}
			});
		}

		//댓글 삭제 
		function commentDelete(cno) {
			var boardwriter = document.getElementById('replyWriter'+cno); 
			var currenwriter = '${sessionScope.userid}';
			if(boardwriter.innerHTML == currenwriter) {
			$.ajax({
				url : '/homework/reply/delete/'+cno,
				type : 'POST',
				data: {'cno': cno, bno: ${board.boardId}},
				dataType : 'text',
				success : function() {
					getList(page);
					alert("성공적으로 댓글을 삭제하였습니다.");
				}
			});
			}else if(currenwriter == "") {
				alert("로그인이 되어있지 않습니다.");
				location.href="/homework/member/login";
			}else if(boardwriter != currenwriter){
				alert("권한이 없습니다.");
			}
		}
		//추후 리스트함수랑 js파일로 뺄것...
		function getFormatData(date) {
			var year = date.getFullYear();
			var mon = date.getMonth()+1;
			var day = date.getDate();
			return year + "-" + mon + "-" + day;
		}
		
		function getList(page){
			console.log("초기: "+  page);
			$.ajax({
				url: '/homework/reply/getboardReply/${board.boardId}/'+page,
				type: 'POST',
				dataType : 'json',
				contentType:"application/json; Charset=UTF-8",
				cache : false,
				data: {
					'bno': ${board.boardId},
					'page' : page
				},
				success: function(result) {
	               	var htmls = "";
	               	console.log(result);
				if(result.length < 1){
					htmls += '<div>등록된 댓글이 없습니다.</div>';
				} else {
		                    htmls += '<table class="table table-bordered" style="margin-top: 15px">';
		                    htmls += '<tr>';
		                    htmls +='<td width="4%"><fmt:message key="REPLY_NO" /></td>';
		                    htmls +='<td width="10%"><fmt:message key="WRITER" /></td>';
		                    htmls +='<td width="55%"><fmt:message key="REPLY" /></td>';
		                    htmls +='<td width="10%"><fmt:message key="WRITE_DATE" /></td>';
		                    htmls +='<td width="10%"><fmt:message key="BTN_CLICK" /></td>';
		                    htmls += '</tr>';
		                    $(result).each(function(){
			                var date = new Date(this.reg_date);
							var to = getFormatData(date);
		                    htmls += '<tr style="margin-top: 5px">';
		                    htmls += '<td width="4%">'+ this.seq +'</td>';
		                    htmls += '<td width="10%" id="replyWriter'+this.cno+'">' + this.writer +'</td>';
		                    htmls += '<td width="55%" id="UpdateContent'+this.cno+'">'+this.content+'</td>';
		                    htmls += '<td width="10%">'+to+'</td>';
		                    htmls += '<td width="10%">';
		                    htmls += '<input type="hidden" value="'+this.cno+'" id="replycno">';
		                    htmls += '<input type="hidden" value="'+content+'" id="replycontent">';
		                    htmls += '<button type="button" id="commentUpdateBtn" name="update" onclick="commentUpdate('+this.cno+');">수정</button>';
		                    htmls += '<button type="button" id="commentDeleteBtn" name="delete" onclick="commentDelete('+this.cno+');">삭제</button>';
		                    htmls += '</td>';
		                    htmls += '</tr>';
		                });	//each end
		                     htmls += '</table>';
						$("#replyList").empty().html(htmls);
				}
				$("#replyList").empty().html(htmls);
				},
				error: function(error, state) {
					console.log("getReplyList : " + error + state);
				}
			});
		}
		
		//$("#boardDelete").click(function(){
		//	$.ajax({
		//		url: '/homework/board/delete/'+${boardId},
		//		type: 'POST',
		//		dataType: 'html',
		//		data: { 'boardId': ${board.boardId}},
		//		success: function(){
		//			location.href="/homework/board/cat/"+${categoryId};
		//		},
		//		error: function(stauts, error) {
		//			console.log("boardDelete : " + stauts + "/" + error);
		//		}
		//	});
		//});
		
>>>>>>> refs/remotes/origin/master
>>>>>>> refs/remotes/origin/master
		$(document).ready(function() {
			getList(page);
			paging();
		});
	</script>
</body>
</html>
