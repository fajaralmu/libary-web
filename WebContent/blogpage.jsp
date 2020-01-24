<%@page import="org.jsoup.Jsoup"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Blog</title>
<%@ include file="header.jsp"%>
<style type="text/css">
.susunanBlog {
	padding: 30px;
	width: 60%;
}

a {
	padding: 10px;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="content">
			<h2 align="center">Halaman Blog</h2>
			<div class="susunanBlog">
				<center>
					Halaman:<br>
					<%-- href="myblog?hal=<%=i%>" --%>
					<%
						if (Integer.parseInt(request.getParameter("hal").toString()) > 1) {
					%>
					<a
						href="myblog?hal=<%=Integer.parseInt(request.getParameter("hal")
						.toString()) - 1%>">prev</a>
					<%
						}
					%>
					<%
						for (int i = 1; i <= Integer.parseInt(request.getAttribute(
								"totalhalaman").toString()); i++) {
					%>
					<a href="myblog?hal=<%=i%>">(<%=i%>)
					<%
						}
						if (Integer.parseInt(request.getParameter("hal").toString()) < Integer
								.parseInt(request.getAttribute("totalhalaman").toString())) {
					%>
					<a
						href="myblog?hal=<%=Integer.parseInt(request.getParameter("hal")
						.toString()) + 1%>">next</a>
					<%
						}
					%>

				</center>
				
				<div class="atas">div atas</div>
				<c:forEach var="post" items="${DaftarPost }">
					<h2>
						<c:out value="${post.judul }"></c:out>
					</h2>
					<p>${fn:substring(post.kontenfreehtml,0,200) }</p>
					<a href="myblog?action=baca&id=${post.idpost }">lanjutkan
						membaca</a>
				</c:forEach>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>