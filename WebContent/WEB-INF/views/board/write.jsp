<%@ page contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n/board" />
<!DOCTYPE html> 
<html>
<jsp:include page="/WEB-INF/views/include/staticFiles.jsp"/>
<body>
<jsp:include page="/WEB-INF/views/include/bodyHeader.jsp"/>
<div class="container">
	<div class="pg-opt">
        <div class="row">
            <div class="col-md-6 pc">
                <h2><fmt:message key="WRITE_NEW_ARTICLE"/></h2>
            </div>
            <div class="col-md-6">
                <ol class="breadcrumb">
                    <li><fmt:message key="BOARD"/></li>
                    <li class="active"><fmt:message key="WRITE_NEW_ARTICLE"/></li>
                </ol>
            </div>
        </div>
    </div>
	<div class="content">
	<form action="<c:url value='/board/write'/>" method="post" enctype="multipart/form-data" class="form-horizontal">
	<c:if test="${!empty categoryList}">
	<div class="form-group">
      <label class="control-label col-sm-2" for="name"><fmt:message key="CATEGORY"/></label>
      <div class="col-sm-4">
        <select name="categoryId" id="categoryId" class="form-control" required>
        	<c:forEach var="category" items="${categoryList}">
        	<option value="${category.categoryId}"  ${category.categoryId eq requestScope.categoryId ? "selected" : ""}>${category.categoryName}</option>
        	</c:forEach>
        </select>
      </div>
    </div>
    </c:if>
	<div class="form-group">
      <label class="control-label col-sm-2" for="name"><fmt:message key="WRITER"/></label>
      <div class="col-sm-2">
        <input type="text" name="writer" id="name" value="${sessionScope.userid}" ${!empty sessionScope.userid ? "readonly" : "" } class="form-control">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email"><fmt:message key="EMAIL"/></label>
      <div class="col-sm-4">
        <input type="text" name="email" id="email" value="${sessionScope.email}" ${!empty sessionScope.email ? "readonly" : "" } class="form-control">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="title"><fmt:message key="SUBJECT"/></label>
      <div class="col-sm-8">
        <input type="text" name="title" id="title" class="form-control">
      	<div id="titleCounter">(0 / 최대 50자)</div>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="content"><fmt:message key="CONTENT"/></label>
      <div class="col-sm-8">
        <textarea id="content" name="content" rows="10" cols="100" class="form-control"></textarea>
        <div id="contentCounter">(0 / 최대 200자)</div>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="photo"><fmt:message key="FILE"/></label>
      <div class="col-sm-8" id="file">
		<input type="file" multiple="multiple" multiple name="file" class="multi" id="BSbtninfo" maxlength="5" data-maxsize="51200"/>
     <!-- <div id="dropzone" style="border:2px dotted #3292A2; width:90%; height:50px; color:#92AAB0; text-align:center; font-size:24px; padding-top:12px; margin-top:10px;">Please Drag and Drop file here</div> -->  
        <span id="droparea" class="help-block"><fmt:message key="FILESIZE_ERROR"/></span>
        
      </div>
    </div>
    <div class="form-group">
    	<div class="col-sm-offset-2 col-sm-8">
			<input type="hidden" name="boardId" value="${board.boardId}">
			<input type="submit" id="i_submit" class="btn btn-info" value="<fmt:message key="SAVE"/>"> <input type="reset" class="btn btn-info" value="<fmt:message key="REWRITE"/>">
		</div>
	</div>
    </form>

	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
//$(function() { // wait for document to load 
//	  $('#file').MultiFile({
//	    list: '#div file-list'
//	  });
//	});
//
////버튼 클릭 시 이벤트 함수
//$(function () {
//     var obj = $("#dropzone");
//
//     obj.on('dragenter', function (e) {
//          e.stopPropagation();
//          e.preventDefault();
//          $(this).css('border', '2px solid #5272A0');
//     });
//
//     obj.on('dragleave', function (e) {
//          e.stopPropagation();
//          e.preventDefault();
//          $(this).css('border', '2px dotted #8296C2');
//     });
//
//     obj.on('dragover', function (e) {
//          e.stopPropagation();
//          e.preventDefault();
//     });
//
//     obj.on('drop', function (e) {
//          e.preventDefault();
//          $(this).css('border', '2px dotted #8296C2');
//
//          var files = e.originalEvent.dataTransfer.files;
//          if(files.length < 1)
//               return;
//
//          F_FileMultiUpload(files, obj);
//     });
//
//});
////파일 멀티 업로드
//function F_FileMultiUpload(files, obj) {
//     if(confirm(files.length + "개의 파일을 업로드 하시겠습니까?") ) {
//         var data = new FormData();
//         for (var i = 0; i < files.length; i++) {
//            data.append('file', files[i]);
//         }
//
//         var url = "/homework/file/write/";
//         $.ajax({
//            url: url,
//            method: 'post',
//            data: data,
//            dataType: 'text',
//            processData: false,
//            contentType: false,
//            success: function(res) {
//                F_FileMultiUpload_Callback(res.files);
//            }
//         });
//     }
//}
//
//// 파일 멀티 업로드 Callback
//function F_FileMultiUpload_Callback(files) {
//     for(var i=0; i < files.length; i++)
//         console.log(files[i].file_nm + " - " + files[i].file_size);
//}
//
//function FileSizeChk() {
//	var File_Size = document.getElementById("file").files[0].size;
//	alert(File_Size); 
//	}
//$("#fileBtn").click(function(){
//	var formData = new FormData($(".form-horizontal")[0]);
//	$.ajax({
//		type: 'post',
//		url: '/homework/file/write',
//		data: formData,
//		processData: false,
//		contentType: false,
//		success: function() {
//			alert("파일 업로드");
//			console.log(formData);
//		},
//		error: function(stauts, error){
//			console.log(stauts + error);	
//		}
//	});
//});
//
//function uploadImage(){
//    
//    //확장자 체크
//    var arr = $("#file").val().split(".");
//    var arrSize = arr.length;
//    console.log(arr);
//    if (arr[arrSize-1].toUpperCase()!="jpg" || arr[arrSize-1].toUpperCase()!="JPG" || arr[arrSize-1].toUpperCase()!="GIF" || arr[arrSize-1].toUpperCase()!="gif")
//    {
//        $("#imgUrl").val('');
//        alert("지원하지 않는 파일 확장자입니다.");
//        return false;
//    }
//    
//    //파일크기 체크
//    var fileSize = $("input[name='file']")[0].files[0].size;
//    console.log("### fileSize="+fileSize);
//    if (fileSize > 20480) {//20480 = 20Kb
//        $("#imgUrl").val('');
//        $("#file").val('');
//        alert("JPG 이미지는 20kbyte 이하여야 합니다.");
//        $("#file").focus();
//        return false;
//    }
//    //파일이 있어야만 함
// 
//    //서버 이미지 등록
//    var formData = new FormData();
//    formData.append("file",$("input[name='file']")[0].files[0]);
//        $.ajax({
//            type: 'POST',
//            url : '/homework/file/write/',
//            processData: false,
//            contentType: false,
//            data: formData,
//            success: function(result){
//                $("#imgUrl").val( result );
//            },
//            error: function(e){
//                alert("이미지등록 처리중 오류가 발생하였습니다.");
//            }
//        });
//}

//
//function CheckFile(file) {
//	var str = file.value;
//	$.ajax({
//		url: '/homework/file/write',
//		data: str,
//		type: 'post',
//		success: function(){
//			alert("aaa");
//		}
//	})
//}
//

/*		입력값 체크 함수		*/
//제목 글자 수 값
$('#title').keyup(function (e){
    var content = $(this).val();
    $('#titleCounter').html("("+content.length+" / 최대 50자)");    //글자수 실시간 카운팅

    if (content.length >= 50){
        alert("최대 50자까지 입력 가능합니다.");
        $(this).val(content.substring(0, 50));
        $('#titleCounter').html("(50 / 최대 50자)");
    }
});

//내용 글자 수 값
$('#content').keyup(function (e){
    var content = $(this).val();
    $('#contentCounter').html("("+content.length+" / 최대 200자)");    //글자수 실시간 카운팅

    if (content.length >= 200){
        alert("최대 200자까지 입력 가능합니다.");
        $(this).val(content.substring(0, 200));
        $('#contentCounter').html("(200 / 최대 200자)");
    }
});

	
</script>
</html>