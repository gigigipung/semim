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
<h1>회원가입</h1>
<form action="${pageContext.request.contextPath}/join" method="post">
<div><label>아이디 : <input type="text" name="id"></label><button type="button" class="btn checkid">중복확인</button></div>
<div><label>비밀번호 : <input type="password" name="pwd"></label></div>
<div><label>이메일 : <input type="text" name="email"></label></div>
<div><button type="submit">회원가입</button></div>
</form>

<script>
$(loadHandler);
function loadHandler(){
	$(".btn.checkid").on("click",btnCheckidClickHandler);
}
function btnCheckidClickHandler(){
	console.log("중복확인");
	var idVal = $("[name=id]").val();
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/checkemail.ajax"
		,method : "post"
		,data : { cid : $("[name=id]").val,}
	});
}
</script>
</body>
</html>