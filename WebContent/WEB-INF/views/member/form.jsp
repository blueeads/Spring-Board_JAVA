<%@ page contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n/member" />
<html>
<jsp:include page="/WEB-INF/views/include/staticFiles.jsp"/>
<body>
<jsp:include page="/WEB-INF/views/include/bodyHeader.jsp"/>
<div class="container">
	<div class="pg-opt">
        <div class="row">
            <div class="col-md-6 pc">
                <h2><fmt:message key="INSERT_USER_INFO"/></h2>
            </div>
            <div class="col-md-6">
                <ol class="breadcrumb">
                    <li><fmt:message key="MEMBER"/></li>
                    <li class="active"><fmt:message key="INSERT_USER_INFO"/></li>
                </ol>
            </div>
        </div>
    </div>
	<div class="content">
	<form action="<c:url value='/member/insert'/>" method="post" id="joinForm" class="form-horizontal">
	<div class="form-group">
      <label class="control-label col-sm-2" for="userid"><fmt:message key="MEMBER_ID"/></label>
      <div class="col-sm-4">
        <input type="text" name="userid" id="userid" value="${member['userid']}" ${empty member.userid ? "" : "readonly"} title="<fmt:message key='USERID_TITLE'/>" pattern="\w+" class="form-control" placeholder="<fmt:message key="MEMBER_ID"/>" required>
      </div>
    </div>
	<div class="form-group">
      <label class="control-label col-sm-2" for="password"><fmt:message key="MEMBER_PW"/></label>
      <div class="col-sm-4">
        <input type="password" name="password" id="password" value="${member.password}" class="form-control" title="<fmt:message key='PASSWORD_TITLE'/>" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="password_re"><fmt:message key="MEMBER_PW_RE"/></label>
      <div class="col-sm-4">
        <input type="password" name="password_re" id="password_re" class="form-control" title="<fmt:message key='PASSWORD_RE_TITLE'/>" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" required>
      </div>
    </div>
	<div class="form-group">
      <label class="control-label col-sm-2" for="name"><fmt:message key="MEMBER_NAME"/></label>
      <div class="col-sm-4">
        <input type="text" name="name" id="name" value="${member.name}" class="form-control" required>
      </div>
    </div>
	<div class="form-group">
      <label class="control-label col-sm-2" for="phone"><fmt:message key="MEMBER_PHONE"/></label>
      <div class="col-sm-6">
        <input type="text" name="phone" id="phone" value="${member.phone}" class="form-control" required>
      </div>
    </div>
	<div class="form-group">
      <label class="control-label col-sm-2" for="email"><fmt:message key="MEMBER_EMAIL"/></label>
      <div class="col-sm-8">
        <input type="email" name="email" id="email" value="${member.address}" class="form-control" required>
      </div>
    </div>
    <div class="form-group">
    	<div class="col-sm-offset-2 col-sm-8">
		<input type="submit" class="btn btn-info" value="<fmt:message key="SAVE"/>">
		<input type="reset" class="btn btn-info" value="<fmt:message key="CANCEL"/>">
		</div>
	</div>
	</form>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>