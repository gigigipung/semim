<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/base.css" rel="stylesheet" type="text/css">
<%@ include file="../header.jsp"%>
</head>
<body>
[[ map : ${map.dtolist }]]<br>
[[ totalPageCount : ${map.totalPageCount }]]<br>
[[ startPageNum : ${map.startPageNum }]]<br>
[[ endPageNum : ${map.endPageNum }]]<br>
<h1>shop</h1>
<div class="board grid">
<c:choose>
	<c:when test="${empty map.dtolist }">
	글 없어요.
	</c:when>
	<c:otherwise>
		<c:forEach items="${map.dtolist }" var="vo" varStatus="vs">
			<div>${vo.gameID }</div>
			<div>${vo.gameName }</div>
			<div>${vo.gameImg }</div>
			<div>${vo.gameOpen }</div>
			<div>${vo.gamePrice }</div>
			
			
		</c:forEach>
	</c:otherwise>
</c:choose>
</div>

</body>
</html>