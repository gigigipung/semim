<jsp:include page="/WEB-INF/common_css_firstlink.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/common_function.jsp"/>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/base.css" rel="stylesheet" type="text/css">
<%@ include file="/WEB-INF/header.jsp"%>

</head>
<body>

<div class="grid">
<h1>shop</h1>
<div class="dropdown">
		<label>기준</label>
        <button class="dropbtn">메뉴</button>
        <div id="myDropdown" class="dropdown-content">
            <a href="javascript:countryHandler('한국');"  class="dropdown-list" id="한국" onclick="filterByCategory('한국')" >한국</a>
            <a href="javascript:countryHandler('일본');"  class="dropdown-list" id="일본" onclick="filterByCategory('일본')">일본</a>
        </div>
</div>
	
<div class="board grid shop">
<c:choose>
    <c:when test="${empty map.dtolist}">
        글 없어요.
    </c:when>
</c:choose>
</div>
	<div class="shop-wrap">
	</div>
<script>

$(loadedHandler);
function loadedHandler(){
	//event 등록
	$(".dropdown-list").on("click", btnClickHandler);
	 $.ajax({
	        url: "${pageContext.request.contextPath}/shop/list",
	        method: "post",
	        error: function(xhr, status, error) {
	            console.error("AJAX 요청이 실패했습니다:", status, error);
	        },
	        data: { country: "한국" }, // 또는 { country: "일본" }
	        dataType: "json",
	        success: function(map){
				console.log(123);
				console.log(map);
				console.log(123);
				console.log(map.dtolist);
				displayShopWrap(map.dtolist);
			}
	    });
	
}

function btnClickHandler() {
    var country = $(this).text(); 

    $.ajax({
        url: "${pageContext.request.contextPath}/shop/list",
        method: "post",
        error: function(xhr, status, error) {
            console.error("AJAX 요청이 실패했습니다:", status, error);
        },
        data: { country: $(this).text() }, // 또는 { country: "일본" }
        dataType: "json",
        success: function(map){
			displayShopWrap(map.dtolist);
		}
    });
}

       
        


function countryHandler(country){
	
	
    
}


$(document).ready(function() {
    var selectedCategory = "카테고리 1"; // 기본 선택 카테고리 설정

    // 카테고리1을 선택하고 필터링합니다.
    filterByCategory('한국');

    $(".dropdown").on("click", function() {
        $("#myDropdown").toggleClass("show");
    });

    $(window).on("click", function(event) {
        if (!$(event.target).hasClass('dropbtn')) {
            $(".dropdown-content").removeClass('show');
        }
    });
});

function filterByCategory(category) {
    selectedCategory = category; 
    var products = $('.product');
    products.each(function() {
        if ($(this).data('category') === category) {
            $(this).show();
        } else {
            $(this).hide();
        }
    });
    $(".dropbtn").html(selectedCategory);
}
        
function displayShopWrap(dtolist){
	var htmlVal = '';
	for (var idx in dtolist) {
	    var vo = dtolist[idx];
	    console.log(vo);
	    htmlVal += `
	        <div class="board grid shop">
	    		
	            <div>\${vo.gameImg}</div>
	            <div><a href="${pageContext.request.contextPath }/shop/read?id=\${vo.gameID }&code=\${vo.nationalCode}">\${vo.gameName}</a></div>
	            <div>\${vo.gameOpen}</div>
	            <div>\${vo.gamePrice}</div>
	            <div>\${vo.gameID}</div>
	            
	        </div>
	    `;
	}

	
	$(".shop-wrap").html(htmlVal);
	// html(새로운내용으로덮어쓰면기존event등록이사라짐)
	// event 다시 등록
}        
       
</script>
</body>
</html>