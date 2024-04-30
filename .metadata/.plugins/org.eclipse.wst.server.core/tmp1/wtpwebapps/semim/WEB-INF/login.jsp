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
<h1>login</h1>
<fieldset>
	<legend>login</legend>
	<form id="cloud-login">
		<div><label>아이디</label><input type="text" name="id"></div>
		<div><label>비밀번호</label><input type="password" name="pwd"></div>
		<div><input type="button" value="로그인" class="btn submit"></div>
		<div><p class="erm">asd</p></div>
	</form>
</fieldset>
${prePage}
<script>
$(loadedHandler);
function loadedHandler(){
	$("#cloud-login .btn.submit").on("click",loginClickHandler);
}
function loginClickHandler(){
	console.log($("#cloud-login").serialize());
	console.log("${prePage}")
	$.ajax({
		url:"${pageContext.request.contextPath}/login"
		,method : "post"
		,data : $("#cloud-login").serialize()
		,success : function(result){
			console.log(result);
			if(result == 1 ){
				//alert("반갑습니다.");
				var prePage = "${prePage}";
				if(prePage == "write"){
					location.href="${pageContext.request.contextPath}/commu/list";
				}
				location.href="${pageContext.request.contextPath}/index";
			}else {
				$(".erm").html("비밀번호와 계정 이름이 맞는지 확인하고 다시 시도하세요.");
				$("[name=pwd]").val("");
			}
		}
		,error : function(request, status, error){
			alert("code: "+request.status + "\n" + "message: " 
					+ request.responseText + "\n"
					+ "error: "+error);
		}
	});
}
</script>

</body>
</html>