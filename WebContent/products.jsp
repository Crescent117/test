<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
<%@page import="dao.ProductDAO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	ProductDAO dao = new ProductDAO();
	List<Product> dto = dao.getProductAll();
	
	List<Product> list = (List<Product>)request.getAttribute("search");
	dao.close();
%>
<html>

<script type="text/javascript">
	function search(){
		var search = document.getElementById("searchWord");
		
		if(search.value==""){
			return;
		}
	} 
	
</script>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" />
<title>상품 목록</title>
</head>
<body>
<c:set var="list" value="<%=list %>"></c:set>
<c:set var="dto" value="<%=dto %>"></c:set>
	<jsp:include page="menu.jsp" />
	<form action="/WebMarket/searchController" method="get" name="search">
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 목록</h1>
			<input type="submit" style="float:right" value="검색" name="searchBtn" >
			<input type="text" style="float:right" name="searchWord" id="searchWord">
		</div>
	</div>
	<div class="container">
		<div class="row" align="center">
			<c:choose>
			<c:when test="${list eq null}">
				<c:forEach items="${dto}" var="dto">
					<div class="col-md-4">
						<img src="/WebMarket/upload/${dto.filename }" style="width: 100%">
						<h3>${dto.pname }</h3>
						<p>${dto.unitPrice }원
						<p><a href="/WebMarket/Bcontroller.do?id=${dto.productId }"class="btn btn-secondary" role="button">상세 정보 &raquo;></a>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="dto">
					<div class="col-md-4">
						<img src="/WebMarket/upload/${dto.filename }" style="width: 100%">
						<h3>${dto.productId }</h3>
						<p>${dto.productId }원
						<p><a href="/WebMarket/Bcontroller.do?id=${dto.productId }"class="btn btn-secondary" role="button">상세 정보 &raquo;></a>
					</div>
				</c:forEach>
			
			</c:otherwise>
			</c:choose>
		</div>
		<hr>
	</div>
	</form>
	<jsp:include page="footer.jsp" />
</body>
</html>
