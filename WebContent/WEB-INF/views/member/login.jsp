<%@ page contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n/member" />
<!DOCTYPE html> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="/WEB-INF/views/include/staticFiles.jsp"/>
<body>
<jsp:include page="/WEB-INF/views/include/bodyHeader.jsp"/>
<div class="container">
	<div class="pg-opt">
        <div class="row">
            <div class="col-md-6 pc">
                <h2><fmt:message key="LOGIN"/><small style="color:red"> <fmt:message key="${not empty message ? message : 'BLANK'}"/></small></h2>
            </div>
            <div class="col-md-6">
                <ol class="breadcrumb">
                    <li><fmt:message key="MEMBER"/></li>
                    <li class="active"><fmt:message key="LOGIN"/></li>
                </ol>
            </div>
        </div>
    </div>
<div class="content">
<c:if test="${empty sessionScope.userid}">
	<form action="<c:url value='/member/login'/>" method="post" class="form-horizontal">
	<div class="form-group">
      <label class="control-label col-sm-2" for="id"><fmt:message key="MEMBER_ID"/></label>
      <div class="col-sm-4">
        <input type="text" name="userid" id="userid" value="${member['userid']}" title="<fmt:message key='USERID_TITLE'/>" class="form-control" placeholder="<fmt:message key="MEMBER_ID"/>">
     	<div id="id_check"></div>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pw"><fmt:message key="MEMBER_PW"/></label>
      <div class="col-sm-4">
       <input type="password" name="password" id="password" value="${member.password}" class="form-control" title="<fmt:message key='PASSWORD_TITLE'/>">
       <div id="pw_check"></div>
      </div>
    </div>
    <div class="form-group">
    	<div class="col-sm-offset-2 col-sm-8">
		<input type="submit" class="btn btn-info" value="<fmt:message key="SIGN_IN"/>">
		<input type="reset" class="btn btn-info" value="<fmt:message key="RESET"/>">
		<a href="<c:url value='/member/insert'/>" class="btn btn-success"><fmt:message key="INSERT_USER_INFO"/></a>
		</div>
	</div>
	</form>
</c:if>
</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
<script type="text/javascript">//아이디 정규식
var idJ = /^[a-z0-9]{6,12}$/;
var idC = 0;
// 비밀번호 정규식
var pwJ = /^[a-z0-9]{6,12}$/; 
var pwC = 0;
var repwC = 0;

$('#userid').blur(function() {
var userid = $("#userid").val();
if(!idJ.test(userid)) {
	$("#id_check").text("잘못된 입력입니다.");
}
}

$('#password').blur(function() {
var password = $("#password").val();
if(!pwJ.test(password)) {
	$("#pw_check").text("잘못된 입력입니다.");
}
}

</script>
</body>
</html>


