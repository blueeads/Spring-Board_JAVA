<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n/header" />
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/include/staticFiles.jsp"/>
<body>
<jsp:include page="/WEB-INF/views/include/bodyHeader.jsp"/>
<div class="container">
	<div class="pg-opt">
        <div class="row">
            <div class="col-md-6 pc">
                <h2><fmt:message key="HOME"/></h2>
            </div>
            <div class="col-md-6">
                <ol class="breadcrumb">
                    <li><fmt:message key="DASHBOARD"/></li>
                    <li class="active"><fmt:message key="HOME"/></li>
                </ol>
            </div>
        </div>
    </div>

	<div class="content">
	<div class="alert alert-warning" class="page-header">
		<h3><fmt:message key="WELCOME_MESSAGE"/></h3>
	</div>
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
					<table>
<<<<<<< HEAD
					<a href="/homework/board/cat/1" font="30px">게시판1 바로가기</a><br>
					<a href="/homework/board/cat/2" font="30px">게시판2 바로가기</a><br>
					<a href="/homework/board/cat/3" font="30px">게시판3 바로가기</a><br>
					</table>
					</div>
			</div>
	<div class="alert alert-info">
		<ol>
			<li text-align="center">Kwon Ohyeon</li>
		</ol>
	</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
</script>
=======
					<a href="board/cat/1" font="30px">게시판1 바로가기</a><br>
					<a href="board/cat/2" font="30px">게시판2 바로가기</a><br>
					<a href="board/cat/3" font="30px">게시판3 바로가기</a><br>
					</table>
					</div>
			</div>
	<div class="alert alert-info">
		<ol>
			<li text-align="center">Kwon Ohyeon</li>
		</ol>
	</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
>>>>>>> refs/remotes/origin/master
</html>
