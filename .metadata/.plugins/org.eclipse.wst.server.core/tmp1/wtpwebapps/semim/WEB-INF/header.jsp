<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<meta charset="UTF-8">
<title>Insert title here</title>
<div ><label class="index">cloud</label></div>
	

<c:choose>
	<c:when test="${empty sss }">
		<div><button class="btn join">회원가입</button></div>
		<div><button class="btn login">로그인</button></div>
	</c:when>
	<c:otherwise>
		<div>[[ 아이디 : ${sss.gamerId} ]]</div>
		<form id="frm-logout">
		<div><button class="btn logout">로그아웃</button></div>
		</form>
	</c:otherwise>
</c:choose>
<div><button class="btn shop">상점</button></div>
<div><button class="btn board">게시판</button></div>

<!-- 아래는 스크립트만 -->
<script>
$(loadedHandler);
function loadedHandler(){
	//event 등록
	$(".index").on("click", indexClickHandler);
	$(".btn.join").on("click", btnJoinClickHandler);
	$(".btn.login").on("click", btnLoginClickHandler);
	$(".btn.shop").on("click", btnShopClickHandler);
	$(".btn.board").on("click", btnBoardClickHandler);
	$(".btn.logout").on("click", btnLogoutClickHandler);
}

function indexClickHandler(){
	location.href="${pageContext.request.contextPath}/index";
}
function btnJoinClickHandler(){
	//window.open("${pageContext.request.contextPath}/join","_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=500,left=500,width=400,height=400");
	location.href="${pageContext.request.contextPath}/join";
}
function btnLoginClickHandler(){
	location.href="${pageContext.request.contextPath}/login";
}
function btnShopClickHandler(){
	location.href="${pageContext.request.contextPath}/shop/list";
}
function btnBoardClickHandler(){
	location.href="${pageContext.request.contextPath}/community/list";	
}
function btnLogoutClickHandler(){
	// get방식으로 사용하지 않음
	// location.href="${pageContext.request.contextPath}/logout";
	//
	
	alert("로그아웃되었습니다.");
	
	var frmlogout = document.getElementById("frm-logout");
	frmlogout.action = "${pageContext.request.contextPath}/logout";
	frmlogout.method = "post";
	frmlogout.submit();
	
	
}
</script>