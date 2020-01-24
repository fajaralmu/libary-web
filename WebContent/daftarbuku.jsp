<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Daftar Buku</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="content">
		<div id="form">
			<div align="center">
				<table border="1" cellpadding="5">
					<caption>
						<h2>Daftar Buku</h2>
					</caption>
					<tr>
						<th>Kode</th>
						<th>Judul</th>
						<th>Penulis</th>
						<th>Penerbit</th>
						<th>Harga</th>
						<th>Stok</th>
						<th>Tindakan</th>
					</tr>
					<c:forEach var="buku" items="${DaftarBuku}">
						<tr>
							<td><c:out value="${buku.kode}" /></td>
							<td><c:out value="${buku.judul}" /></td>
							<td><c:out value="${buku.penulis}" /></td>
							<td><c:out value="${buku.penerbit}" /></td>
							<td><c:out value="${buku.harga}" /></td>
							<td><c:out value="${buku.stok}" /></td>
							<td><a href="editBuku?idbuku=${buku.id}">Edit</a>|<a
								href="hapus?idbuku=${buku.id}">Hapus</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>