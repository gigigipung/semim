<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/base.css" rel="stylesheet" type="text/css">
<%@ include file="header.jsp"%>
</head>
<body>
<div>
<div><p id="ermt"></div>
<div><p id="erme"></div>
<div><p id="ermc"></div>
</div>
<form action="${pageContext.request.contextPath}/join" method="post">
<h1>계정 만들기</h1>
<div><label>이메일 주소</label><input type="text" id="emailtype" maxlength="50" placeholder="your@email.com"></div>
<div><label>이메일 주소 다시 입력</label><input type="text" id="email" name="email" maxlength="50" placeholder="your@email.com"></div>
<div><label>거주국가</label>
<select name="country" id="country">
<option value="korea">대한민국</option> 
<option value="japan">일본</option>
</select></div>
<div><input type="checkbox" id="okay">
<label>본인은 만 13세 이상이며 Cloud 이용 약관 및 Cloud 개인정보 보호정책에 동의합니다.</label></div>

<div><button type="button" class="btn checkemail">회원가입</button></div>
</form>

<script>
$(loadHandler);
function loadHandler(){
	$(".btn.checkemail").on("click",btnCheckidClickHandler);
	$(".btn.checkemail").on("click",validateEmail);
}
function btnCheckidClickHandler(){
	console.log("중복확인");
	console.log($('input:checkbox[id="okay"]').is(":checked"));
	console.log($("[id=emailtype]").val());
	console.log($("[id=email]").val());
	console.log($("[id=email]").val().length);
	console.log($("[id=email]").val()==$("[id=emailtype]").val());
	console.log($("[id=email]").val()!=$("[id=emailtype]").val());
	console.log("===================")
	var idVal = $("[name=email]").val();
	checkboxHandler();	
	
	if($("[id=email]").val()!=$("[id=emailtype]").val()){
		$("#erme").html("메일 주소란에 동일한 주소를 입력하세요.");
	}else{
		$("#erme").html("");
		$.ajax({
			async : false
			,url : "${pageContext.request.contextPath}/checkemail.ajax"
			,method : "post"
			,data : $("[name=email]")
			,success : function(result){
				console.log(result);
				if(result > 0){
					$("#erme").html("이미 아이디 있습니다.");
				}else {
					if($("[id=email]").val().length==0){
						$("#erme").html("이메일 주소 확인란에 이메일을 입력해 주세요.");
					}
					else{
					//이메일 사용가능
						if($('input:checkbox[id="okay"]').is(":checked")==true){
						location.href="${pageContext.request.contextPath}/join/completesignup";
						}
					}
				}
			}
			,error : function(request, status, error){
				$(".erme").html("code: "+request.status + "\n" + "message: " 
						+ request.responseText + "\n"
						+ "error: "+error);
			}
		});
	}
}
function checkboxHandler(){
	if($('input:checkbox[id="okay"]').is(":checked")==true){
		$("#ermc").html("");
	}else{
		$("#ermc").html("이용약관에 동의해주십시오.");
	}
}

function emailCheck(email_address){     
	email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
	if(!email_regex.test(email_address)){ 
		return false; 
	}else{
		return true;
	}
}
function validateEmail() {
	var email = $("[id=emailtype]").val();

	if (emailCheck(email)) {
		$("#ermt").html("");
	} else {
		$("#ermt").html("올바른 메일 주소를 입력하세요.");
	}
}
</script>



</body>
</html>