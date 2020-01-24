<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Masuk</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="content">
			<div id="form" align="center">
				<c:if test="${gagalmasuk != null }">
					<p style="color: red">
						<c:out value="${gagalmasuk}" />
					</p>
				</c:if>

				<h2>Masuk</h2>
				<form action="masuk" method="post">
					<label>Username</label><br> <input type="text" name="username" />
					<br> <label>Password</label><br> <input type="password"
						name="katasandi" /><br> <input type="submit" value="Masuk" />
				</form>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>