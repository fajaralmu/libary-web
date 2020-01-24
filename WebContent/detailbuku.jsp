<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Buku: ${buku.judul }</title>
<%@ include file="header.jsp"%>

<style type="text/css">
.databuku {
	width: 500px;
	margin-left: 100px;
	margin-top: 100px;
	min-height: 400px;
	border-style: groove;
	padding: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="content">
			<div class="databuku">
				<h2>${buku.judul }</h2>
				<p>penulis: ${buku.penulis }</p>
				<p>penerbit: ${buku.penerbit }</p>
				
			</div>
			<center><a href="home">kembali</a></center>
		</div>
		<%@ include file="footer.jsp"%>
	</div>

</body>
</html>