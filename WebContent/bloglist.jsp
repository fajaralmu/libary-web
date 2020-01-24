<%@page import="org.jsoup.Jsoup"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Daftar Blog</title>
<%@ include file="header.jsp"%>

</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="content">
			<div id="form">
			<h2 align="center">Daftar Postingan</h2>
				<c:forEach var="post" items="${daftarPost }">
						<h2><c:out value="${post.judul }"></c:out></h2>
						<a href="post?action=edit&id=${post.idpost }">edit</a>|
						<a href="post?action=hapus&id=${post.idpost }">hapus</a>
				</c:forEach>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>

</body>
</html>