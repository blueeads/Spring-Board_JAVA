<%@ page contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
response.setStatus(200);
%>
<!DOCTYPE html> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n/common" />
<html>
<jsp:include page="/WEB-INF/views/include/staticFiles.jsp"/>
<body>
<jsp:include page="/WEB-INF/views/include/bodyHeader.jsp"/>
<div class="container">
<div class="content">
	<div class="jumbotron">
		<h2 style="color:red;"><fmt:message key="${message}"/></h2>
		<p>
<<<<<<< HEAD
			Failed URL: ${url}
			Exception:  ${exception.message}
			<c:forEach items="${exception.stackTrace}" var="stack">    ${stack} 
			</c:forEach>
		
		</p>
		<p><a class="btn btn-primary btn-lg" href='<c:url value="/"/>' role="button" onclick='location.href="historyback()"'><fmt:message key="HOME"/></a></p>
	</div>
</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
<script type="text/javascript">
//취소 버튼 클릭 시 이전 페이지로 이동
function historyback() {
	history.back();
	//history.go(-1);
}
</script>
=======
<<<<<<< HEAD
			Failed URL: ${url}
			Exception:  ${exception.message}
			<c:forEach items="${exception.stackTrace}" var="stack">    ${stack} 
=======
		
			Failed URL: ${url}
			Exception:  ${exception.message}
			<c:forEach items="${exception.stackTrace}" var="ste">    ${ste} 
>>>>>>> refs/remotes/origin/master
			</c:forEach>
		
		</p>
		<p><a class="btn btn-primary btn-lg" href='<c:url value="/"/>' role="button"><fmt:message key="HOME"/></a></p>
	</div>
</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
>>>>>>> refs/remotes/origin/master
</body>
</html>
