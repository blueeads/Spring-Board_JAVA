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
							<%--c:if test="${!empty sessionScope.userid}"--%> <c:set
								var="len" value="${fn:length(board.fileName)}" /> <c:set
								var="filetype"
								value="${fn:toUpperCase(fn:substring(board.fileName, len-4, len))}" />
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
							</button></a> <a href='<c:url value="/board/delete/${board.boardId}"/>'><button
								type="button" class="btn btn-info" id = "boardDelete">
								<fmt:message key="DELETE" />
							</button></a></td>
				</tr>
			</table>

			<label for="content">comment</label>
			<form action="<c:url value='/board/view'/>" method="post"
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
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript">
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
							//console.log("성공");
						},
							//error : function(request, status, error) {
							//	console.log("code = " + request.status + " message = "
							//			+ request.responseText + " error = "
							//			+ error); // 실패 시 처리
							//},
							complete : function() {
								alert("댓글이 성공적으로 등록되었습니다.");
								$("#content").val("");
								getList();
							}
				});
				}
			}else {
				alert("로그인이 되어있지 않습니다.");
				location.href="/homework/member/login"
			}
		});
		
		//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
		function commentUpdate(cno, contnet) {
				var a = '';
				a += '<div class="input-group">';
				a += '<input id="replycontent" type="text" class="form-control" name="content_'+cno+'" value="'+contnet+'"/>';
				a += '<span class="input-group-btn"><button id="updataProc" class="btn btn-default" type="button" onclick="commentUpdateProc('
						+ cno + contnet + ');">수정</button> </span>';
				a += '</div>';
				$('#UpdateContent'+cno).html(a);
		}
		
		function commentUpdateProc(cno, content){
			$.ajax({
					url: '/homework/reply/update',
					type: 'POST',
					data : {'cno': cno,
						'content' : content
						},
					dataType : 'json',
					success: function(result) {
						alert("댓글 수정완료");
						getList();
					},
					error: function(state, error) {
						console.log(state + error);
					},
					complete: function() {
						console.log(content);
					}
			});
		}

		//댓글 삭제 
		function commentDelete(cno) {
			var boardId = document.getElementById('replyWriter'); 
			var currentId = '${sessionScope.userid}';
			if(boardId.innerHTML == currentId) {
			$.ajax({
				url : '/homework/reply/delete/'+cno,
				type : 'post',
				data: {'cno': cno,
						'bno': ${board.boardId}	
				},
				dataType : 'json',
				success : function() {
					console.log("success");
				},
				complete: function(){
					getList();
					alert("성공적으로 댓글을 삭제하였습니다.");
				}
			});
			}else if(currentId == "") {
				alert("로그인이 되어있지 않습니다.");
				location.href="/homework/member/login";
			}else if(boardId != currentId){
				alert("권한이 없습니다.");
			}
			console.log(currentId);
		}
		
		function getList(){
			$.ajax({
				url: '/homework/reply/getboardReply',
				type: 'POST',
				dataType : 'json',
				data: {
					'bno': '${board.boardId}'
				},
				success: function(result) {
	               	var htmls = "";
				if(result.length < 1){
					htmls += '<div>등록된 댓글이 없습니다.</div>';
				} else {					
		                    htmls += '<table class="table table-bordered" style="margin-top: 15px">';
		                    htmls += '<tbody id="">';
		                    htmls += '<tr>';
		                    htmls +='<td width="4%"><fmt:message key="REPLY_NO" /></td>';
		                    htmls +='<td width="10%"><fmt:message key="WRITER" /></td>';
		                    htmls +='<td width="55%"><fmt:message key="REPLY" /></td>';
		                    htmls +='<td width="10%"><fmt:message key="WRITE_DATE" /></td>';
		                    htmls +='<td width="10%"><fmt:message key="BTN_CLICK" /></td>';
		                    htmls += '</tr>';
		                    $(result).each(function(){
		                     htmls += '<tr style="margin-top: 5px">';
		                     htmls += '<td width="4%">'+ this.seq +'</td>';
		                     htmls += '<td width="10%" id="replyWriter">' + this.writer +'</td>';
		                     htmls += '<td width="55%" id="UpdateContent'+this.cno+'">'+this.content+'</td>';
		                     htmls += '<td width="10%">';
		                     htmls += '<td width="10%">';
		                     htmls += '<input type="hidden" value="'+this.cno+'" id="replycno">';
		                     htmls += '<input type="hidden" value="'+this.content+'" id="replycontent">';
		                     htmls += '<button type="button" id="commentUpdateBtn" name="update" onclick="commentUpdate('+this.cno+','+this.content+');">수정</button>';
		                     htmls += '<button type="button" id="commentDeleteBtn" name="delete" onclick="commentDelete('+this.cno+');">삭제</button>';
		                     htmls += '	</td>';
		                     htmls += '	</tr>';
		                });	//each end
		                     htmls += '	</tbody>';
		                     htmls += '	</table>';
				}
				$("#replyList").html(htmls);
				},
				error: function(error, state) {
					console.log(error + state);
				},
				complete: function(result) {
					//console.log(result);
				}
			
			});
		}
		
		$(document).ready(function() {
			getList();
		});
	</script>
</body>
</html>
