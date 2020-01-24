<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Latihan Perpustakaan</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="header.jsp"%>

</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="content">
		<div style="padding:10px;margin-top: 100px" align="center">
			Pilih file untuk upload: <br />
			<form action="upload" method="post"
				enctype="multipart/form-data">
				<input type="file" name="file" size="50" /> <br /> <input
					type="submit" value="Upload File" />
			</form>
			</div>
			</div>


			<%@ include file="footer.jsp"%>
		</div>
</body>
</html>