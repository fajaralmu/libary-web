<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Beranda User</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="content">
			<h2>
				Hi ${nama }
				<%=request.getSession().getAttribute("username").toString() %>
			</h2>
			<ul>
				<li><a href="baru">Tambah Buku</a></li>
				<li><a href="daftarBuku">Daftar Buku</a></li>
				<li><a href="editUser?iduser=${id }">Edit User</a></li>
				<li><a href='post?action=baru'>tulis Posting</a></li>
				<li><a href='post?action=daftarposting'>posting Saya</a></li>
				<li><a href='baru'>tambah Buku</a></li>
				<li><a href="keluar">Keluar</a></li>
			</ul>
		</div>
		<%@ include file="footer.jsp"%>
	</div>

</body>
</html>