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
		<div>${dto.communityId }</div>
		<div>${dto.gamerId }</div>
		<div>${dto.communityWriteTime }</div>
		<div>${dto.readCount }</div>
	</div>
	<div class="subject">${dto.communityName }</div>
	<div>${dto.communityContent }</div>
	<c:if test="${not empty dto.filedtolist }">
	<div>
		<c:forEach items="${dto.filedtolist }" var="filedto">
		<div><a href="${pageContext.request.contextPath }/files/${filedto.filePath}" download="${filedto.fileOriginal }">${filedto.fileOriginal }</a></div>
		</c:forEach>
	</div>
	</c:if>
	<div>
		<form id="frm-reply">
		<input type="hidden" name="communityId" value="${dto.communityId }">
		<div class="flex">
			<div>댓글</div>
			<div><input type="text" name="replyContent" required></div>
			<div><button type="button" class="btn replay" >댓글달기</button></div>
		</div>
		</form>
	</div>
	<div class="reply-wrap">
	</div>
</div>
<script>
$(loadedHandler);
function loadedHandler(){
	//event 등록
	$(".btn.replay").on("click", btnReplyClickHandler);
	$(".btn.rreplay").on("click", btnRReplyClickHandler);

	//$(".btn.rreplycontent.show").on("click", btnRReplyContentClickHandler);
	// displayReplyWrap()을수행 ajax
	$.ajax({
		url: "${pageContext.request.contextPath }/community/reply/read.ajax"
		,method:"post"
		,error : ajaxErrorHandler
		,data: {communityId:"${dto.communityId }"}
		,dataType:"json"
		,success: function(result){
			console.log(result);
			displayReplyWrap(result);
		}
	});
	
}
function btnRReplyClickHandler(){
	//Login 페이지로 이동
	if(checkLogin("로그인되어야 글쓰기가 가능합니다.\n로그인페이지로 이동하시겠습니까?","write")){
		return;
	}
	
	if($(this).parents(".frm-rreply").find("[name=replyContent]").val().trim().length == 0){
		alert("입력된 글이 없습니다. 입력 후 글 등록해주세요.");
		return;
	}
	console.log($(this).parents(".frm-rreply").serialize());
	
	$.ajax({
		url: "${pageContext.request.contextPath }/community/reply/write.ajax"
		,method:"post"
		,error : ajaxErrorHandler
		,data: $(this).parents(".frm-rreply").serialize()
		,dataType:"json"
		,success: function(result){
			console.log(result);
			if(result == "-1"){
				alert("댓글 작성이 되지 않았습니다. 게시글 목록으로 이동 후 다시 작성해주세요.");
				location.href="${pageContext.request.contextPath }/community/list";
				return;
			}
			if(result == "0"){
				alert("댓글 등록에 실패했습니다. 다시 시도해주세요.");
				return;
			}
			displayReplyWrap(result);
		}
	});
}
function btnReplyClickHandler(){
	//Login 페이지로 이동
	if(checkLogin("로그인되어야 글쓰기가 가능합니다.\n로그인페이지로 이동하시겠습니까?","write")){
		return;
	}
	
	if($("#frm-reply [name=replyContent]").val().trim().length == 0){
		alert("입력된 글이 없습니다. 입력 후 글 등록해주세요.");
		return;
	}
	console.log($("#frm-reply").serialize());
	
	$.ajax({
		url: "${pageContext.request.contextPath }/community/reply/write.ajax"
		,method:"post"
		,error : ajaxErrorHandler
		,data: $("#frm-reply").serialize()
		,dataType:"json"
		,success: function(result){
			console.log(result);
			if(result == "-1"){
				alert("댓글 작성이 되지 않았습니다. 게시글 목록으로 이동 후 다시 작성해주세요.");
				location.href="${pageContext.request.contextPath }/community/list";
				return;
			}
			if(result == "0"){
				alert("댓글 등록에 실패했습니다. 다시 시도해주세요.");
				return;
			}
			displayReplyWrap(result);
		}
	});
}

function btnRReplyContentClickHandler(){
/*
	$(".btn.rreplycontent.show").each(function(){
		if($(this).text() == "취소"){
			$(this).text("ㄷㄷ글");
		}
	});
 */	
	if($(this).text() == "ㄷㄷ글"){
		$(this).text("취소");	
	}else {
		$(this).text("ㄷㄷ글");
	}
	//$(".communityreply.grid .rreplycontent.span").show();
	//$(this).parent().next().show();
	$(this).parent().next().toggle();

	
}





function displayReplyWrap(datalist){
	console.log("${dto.communityId }");
	var htmlVal = '';
	for(var idx in datalist){
		var replydto = datalist[idx];
		htmlVal += `
		<form class="frm-rreply">
		<input type="hidden" name="communityId" value="${dto.communityId }">
		<input type="hidden" name="replyId" value="\${replydto.replyId }">
		<input type="hidden" name="replyLevel" value="\${replydto.replyLevel }">
		<input type="hidden" name="replyStep" value="\${replydto.replyStep }">
		<input type="hidden" name="replyRef" value="\${replydto.replyRef }">
		<div  class="communityreply grid">
			<div>\${replydto.replyId }</div>
			<div>
`;
for( var i = 1; i<replydto.replyLevel; i++ ){
	htmlVal += '<span style="color:red;font-weight:bold;">ㄴ</span>';
}
		htmlVal += `
			\${replydto.replyContent }
			</div>
			<div>\${replydto.replyWriteTime }</div>
			<div>\${replydto.gamerId }</div>
			<div><button type="button" class="btn show rreplycontent">ㄷㄷ글</button></div>
			<div class="rreplycontent span" ><input type="text" name="replyContent" ><button type="button" class="btn rreplay" >등록</button> </div>
		</div>
		</form>
		`;
	}
	$(".reply-wrap").html(htmlVal);
	// html(새로운내용으로덮어쓰면기존event등록이사라짐)
	// event 다시 등록
	$(".btn.rreplycontent.show").on("click", btnRReplyContentClickHandler);
	$(".btn.rreplay").on("click", btnRReplyClickHandler);
}
</script>
</html>










