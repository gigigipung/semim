<jsp:include page="/WEB-INF/common_css_firstlink.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Semim community Write</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<jsp:include page="/WEB-INF/common_function.jsp"/>
<style>
.communityreply.grid{
	display:grid;
	grid-template-columns: 1fr 5fr 2fr 1fr 1fr;
	max-width: 800px;
}
.communityreply.grid .rreplycontent.span{
	grid-column : 1/-1;
	display : none;
}
.community.grid{
	display:grid;
	grid-template-columns: 1fr;
	max-width: 800px;
}
.community.grid > div{
	border: 1px solid black;
	padding : 10px;
}
.flex{
	display: flex;
	justify-content: space-between;
}
.flex > div{
	border: 1px solid black;
	padding : 10px;
}
.flex > div:nth-child(1), .flex > div:nth-child(4){
	width:50px;
	flex-shrink: 0;
}
.community > .subject{
	text-align: center;
	font-weight: 800;
}
.community > .subject{
	text-align: center;
	font-weight: 800;
}
.community [name=replyContent] {
	width:500px;
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/base.css" rel="stylesheet" type="text/css">
<%@ include file="../header.jsp"%>
</head>
<body>
<h1>Semim community Write</h1>
<div class="community grid">
	<div class="flex">
		<div>${dto.nationalCode }</div>
		<div>${dto.gameID }</div>
		<div>${dto.gameImg }</div>
		<div>${dto.gameName }</div>
	</div>
	<div class="subject">${dto.gameName }</div>
	<div>${dto.gameContent }</div>

	
</div>
<script>
$(loadedHandler);
function loadedHandler(){
	//event 등록
	$(".btn.replay").on("click", btnReplyClickHandler);
	$(".btn.rreplay").on("click", btnRReplyClickHandler);

	
	
}

</script>
</html>










