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
        <input type="text" name="userid" id="userid" title="<fmt:message key='USERID_TITLE'/>"class="form-control" placeholder="<fmt:message key="MEMBER_ID"/>">
			<!-- 아이디 숫자 카운트 -->
    <!-- <div id="idCounter"></div> -->  
      	<div id="id_check"></div>
      </div>
    </div>
	<div class="form-group">
      <label class="control-label col-sm-2" for="password"><fmt:message key="MEMBER_PW"/></label>
      <div class="col-sm-4">
        <input type="password" name="password" id="password" value="${member.password}" class="form-control" title="<fmt:message key='PASSWORD_TITLE'/>" >
     	<div id="passwordCounter"></div>
     	<div id="pw_check"></div>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="password_re"><fmt:message key="MEMBER_PW_RE"/></label>
      <div class="col-sm-4">
        <input type="password" name="password_re" id="password_re" class="form-control" title="<fmt:message key='PASSWORD_RE_TITLE'/>" >
        <div id="repw_check"></div>
      </div>
    </div>
	<div class="form-group">
      <label class="control-label col-sm-2" for="name"><fmt:message key="MEMBER_NAME"/></label>
      <div class="col-sm-4">
        <input type="text" name="name" id="name" value="${member.name}" class="form-control"  >
        <div id="name_check"></div>
      </div>
    </div>
	<div class="form-group">
      <label class="control-label col-sm-2" for="phone"><fmt:message key="MEMBER_PHONE"/></label>
      <div class="col-sm-4">
        <input type="text" name="phone" id="phone" value="${member.phone}" class="form-control" maxlength="13">
        <div id="phone_check"></div>
      </div>
    </div>
	<div class="form-group">
      <label class="control-label col-sm-2" for="email"><fmt:message key="MEMBER_EMAIL"/></label>
      <div class="col-sm-6">
        <input type="email" name="email" id="email" value="${member.address}" class="form-control"  >
        <div id="email_check"></div>
      </div>
    </div>
    <div class="form-group">
    	<div class="col-sm-offset-2 col-sm-8">
		<input type="submit" id="reg_submit" class="btn btn-info" disabled="disabled" value="<fmt:message key="SAVE"/>">
		<button type="button" onclick="historyback();" class="btn btn-info"><fmt:message key="CANCEL"/></button>
		<div id="inputCheck">모든 란이 작성되면 버튼이 활성화됩니다.</div>
		</div>
	</div>
	</form>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
//정규식
//모든 공백 체크 정규식
var empJ = /\s/g;
//아이디 정규식
var idJ = /^[a-z0-9]{6,12}$/;
var idC = 0;
// 비밀번호 정규식
var pwJ = /^[a-z0-9]{6,12}$/; 
var pwC = 0;
var repwC = 0;
// 이름 정규식
var nameJ = /^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/;
var nameC = 0;
// 이메일 검사 정규식
var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
var mailC = 0;
// 휴대폰 번호 정규식
var phoneJ = /^\d{2,3}-\d{3,4}-\d{4}$/;
var phoneC = 0;

//입력값 체크 함수
var phone = document.getElementById('phone');
phone.onkeyup = function(event){
        event = event || window.event;
        var _val = this.value.trim();
        this.value = autoHypenPhone(_val) ;
}
//숫자 전화번호
function autoHypenPhone(str){
  str = str.replace(/[^0-9]/g, '');
  var tmp = '';
  if( str.length < 4){
    return str;
  }else if(str.length < 7){
    tmp += str.substr(0, 3);
    tmp += '-';
    tmp += str.substr(3);
    return tmp;
  }else if(str.length < 11){
    tmp += str.substr(0, 3);
    tmp += '-';
    tmp += str.substr(3, 3);
    tmp += '-';
    tmp += str.substr(6);
    return tmp;
  }else{
    tmp += str.substr(0, 3);
    tmp += '-';
    tmp += str.substr(3, 4);
    tmp += '-';
    tmp += str.substr(7);
    return tmp;
  }
  return str;
}

//아이디 글자 값 체크
$('#userid').keyup(function (e){
    var content = $(this).val();
    $('#idCounter').html("("+content.length+" / 최대 12)");    //글자수 실시간 카운팅
    if (content.length >= 13){
        alert("최대 12자까지 입력 가능합니다.");
        $(this).val(content.substring(0, 12));
        $('#idCounter').html("(12 / 최대 12)");
    }
});

//비밀번호 글자 값 체크
$('#password').keyup(function (e){
    var content = $(this).val();
    $('#passwordCounter').html("("+content.length+" / 최대 12자)");    //글자수 실시간 카운팅

    if (content.length >= 13){
        alert("최대 12자까지 입력 가능합니다.");
        $(this).val(content.substring(0, 12));
        $('#passwordCounter').html("(12 / 최대 12자)");
    }
});

//아이디 중복 확인
$('#userid').blur(function() {
			var userid = $("#userid").val();
			console.log(userid);
			$.ajax({
				url : '/homework/member/check?userid=' + userid,
				type : 'GET',
				success : function(result) {
					console.log(result);
					if (result == 1) {
						// 1 : 아이디가 중복되는 문구
						$("#id_check").text("사용중인 아이디입니다");
						$("#id_check").css("color", "red");
						
						idC = 0; //아이디 체크 1
					} else {
						if (idJ.test(userid)) {
							// 0 : 아이디 길이 / 문자열 검사
							$("#id_check").text("사용가능한 아이디입니다.");
							$('#id_check').css('color', 'blue');
							$("#userid").css("boreder-color", "#00F", "-webkit-box-shadow", "0 0 3px rgba(0, 0, 255, .5");
							idC = 1; //아이디 체크 1
						} else if (userid == "") {
							$('#id_check').text('아이디를 입력해주세요');
							$('#id_check').css('color', 'red');
							idC = 0; //아이디 체크 1
						} else {
							$('#id_check').text(
									"아이디는 소문자와 숫자 6~12자리만 가능합니다.");
							$('#id_check').css('color', 'red');
							idC = 0; //아이디 체크 1
						}
						
					}
				}
			});
			inputCheck();
		});

//비밀번호 체크
	$('#password').blur(function() {
			var password = $("#password").val();
			if(pwJ.test(password)) {
				$("#pw_check").text("사용가능한 패스워드입니다.");
				$('#pw_check').css('color', 'blue');
				pwC = 1;
			} else if(password == "") {
				$("#pw_check").text("패스워드를 입력해주세요.");
				$('#pw_check').css('color', 'red');
				pwC = 0;
			} else {
				$("#pw_check").text("패스워드는 소문자와 숫자 6~12자리만 가능합니다.");
				$('#pw_check').css('color', 'red');
				pwC = 0;
			}
			inputCheck();
		});
		
	$('#password_re').blur(function() {
		var password = $("#password").val();
		var password_re = $("#password_re").val();
		if(password == password_re) {
			$("#repw_check").text("패스워드가 일치합니다.");
			$('#repw_check').css('color', 'blue');
			repwC = 1;
		} else if(password_re == "") {
			$("#repw_check").text("패스워드를 다시 입력해주세요.");
			$('#repw_check').css('color', 'red');
			repwC = 0;
		} else {
			$("#repw_check").text("일치하지 않습니다. 다시 입력해주세요.");
			$('#repw_check').css('color', 'red');
			repwC = 0;
		}
		inputCheck();
	});
	
	$('#name').blur(function() {
		var name = $("#name").val();
		if(nameJ.test(name)) {
			$("#name_check").text("");
			$('#name_check').css('color', 'blue');
			nameC = 1;
		} else if(name == "") {
			$("#name_check").text("이름을 입력해주세요.");
			$('#name_check').css('color', 'red');
			nameC = 0;
		} else {
			$("#name_check").text("사용 할 수 없는 문자가 섞여있습니다.");
			$('#name_check').css('color', 'red');
			nameC = 0;
		}
		inputCheck();
	});
	
	$('#phone').blur(function() {
		var phone = $("#phone").val();
		if(phoneJ.test(phone)) {
			$("#phone_check").text("");
			$('#phone_check').css('color', 'blue');
			phoneC = 1;
		} else if(phone == "") {
			$("#phone_check").text("핸드폰 번호를 입력해주세요.");
			$('#phone_check').css('color', 'red');
			phoneC = 0;
		} else {
			$("#phone_check").text("핸드폰 번호를 다시 한번 확인해주세요.");
			$('#phone_check').css('color', 'red');
			phoneC = 0;
		}
		inputCheck();
	});
	
	$('#email').blur(function() {
		var email = $("#email").val();
		if(mailJ.test(email)) {
			$("#email_check").text("사용가능한 이메일 입니다.");
			$('#email_check').css('color', 'blue');
			mailC = 1;
		} else if(email == "") {
			$("#email_check").text("이메일을 입력해주세요.");
			$('#email_check').css('color', 'red');
			mailC = 0;
		} else {
			$("#email_check").text("이메일 양식이 맞는지 다시 한번 확인해주세요.");
			$('#email_check').css('color', 'red');
			mailC = 0;
		}
		inputCheck();
	});
	
function inputCheck() {
	console.log("idc" + idC + "pwC" + pwC + "repwC" + repwC + "nameC" + nameC + "mailC" + mailC + "phoneC" + phoneC);
	if(idC == 1 && pwC == 1 && repwC == 1 && nameC == 1 && mailC == 1 && phoneC == 1) {
		$("#reg_submit").attr("disabled", false);
		$("inputCheck").text("회원가입 버튼이 활성화 되었습니다. 입력하신 정보 확인 후 눌러주세요!");
	}
}

//취소 버튼 클릭 시 이전 페이지로 이동
function historyback() {
	history.back();
	//history.go(-1);
}
	
</script>
</html>