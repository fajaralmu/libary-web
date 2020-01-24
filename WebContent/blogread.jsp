<%@page import="org.jsoup.Jsoup"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>${post.judul }</title>
<%@ include file="header.jsp"%>
<style type="text/css">
.kontenBlog{
	padding: 30px;
	width: 60%;
}
</style>
</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="content">
			<div class="kontenBlog">
				<h2 align="center">${post.judul}</h2>
			    <h4>oleh ${penulis }</h4>
				<p>${post.konten }</p>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>