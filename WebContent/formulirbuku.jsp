<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Formulir Buku</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="content">
			<div id="form">
				<div align="center">
					<c:if test="${buku == null}">
						<h2>Tambah buku</h2>
						<form action="tambah" method="post">
							<table cellpadding="5">
								<tr>
									<th>Kode</th>
									<td><input type="text" name="kodebuku" value="" /></td>
								</tr>
								<tr>
									<th>Judul</th>
									<td><input type="text" name="judul" value="" /></td>
								</tr>
								<tr>
									<th>Penulis</th>
									<td><input type="text" name="penulis" value="" /></td>
								</tr>
								<tr>
									<th>Penerbit</th>
									<td><input type="text" name="penerbit" value="" /></td>
								</tr>
								<tr>
									<th>Harga</th>
									<td><input type="text" name="harga" value="" /></td>
								</tr>
								<tr>
									<th>Stok</th>
									<td><input type="text" name="stok" value="" /></td>
								</tr>
								<tr>
									<td></td>
									<td><input type="submit" value="Simpan" /></td>
								</tr>
							</table>

						</form>
					</c:if>
					<c:if test="${buku != null}">
						<h2>Edit buku</h2>
						<form action="perbarui" method="post">

							<table cellpadding="5">
								<tr>
									<th>Kode</th>
									<td><input type="hidden" name="idbuku"
										value="<c:out value='${buku.id }'/>" /> <input type="text"
										name="kodebuku" value="<c:out value='${buku.kode }'/>" /></td>
								</tr>
								<tr>
									<th>Judul</th>
									<td><input type="text" name="judul"
										value="<c:out value='${buku.judul }'/>" /></td>
								</tr>
								<tr>
									<th>Penulis</th>
									<td><input type="text" name="penulis"
										value="<c:out value='${buku.penulis }'/>" /></td>
								</tr>
								<tr>
									<th>Penerbit</th>
									<td><input type="text" name="penerbit"
										value="<c:out value='${buku.penerbit }'/>" /></td>
								</tr>
								<tr>
									<th>Harga</th>
									<td><input type="text" name="harga"
										value="<c:out value='${buku.harga }'/>" /></td>
								</tr>
								<tr>
									<th>Stok</th>
									<td><input type="text" name="stok"
										value="<c:out value='${buku.stok }'/>" /></td>
								</tr>
								<tr>
									<td></td>
									<td><input type="submit" value="Simpan" /></td>
								</tr>
							</table>

						</form>
					</c:if>
				</div>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>