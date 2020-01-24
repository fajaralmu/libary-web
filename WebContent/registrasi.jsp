<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Formulir Daftar User</title>
<%@ include file="header.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var username = false;
		var nama = false;
		//var sandi1 = false;
		var sandi2 = false;
		if($("#username").val()!=""){
			username = true;
		}else{
			username = false;
		}
		
		if($("#nama").val()!=""){
			nama = true;
		}else{
			nama = false;
		}
		
		if($("#sandi1").val()!="" && $("#sandi2").val()!="" && $("#sandi1").val()==$("#sandi2").val()){
			sandi2 = true;
		}else{
			sandi2 = false;
		}

		$("#username").keyup(function(e) {
			 if ($(this).val() != ""){
				$("#cekUsername").html("harap tunggu..");
				$.ajax({
					url : "cekUsername",
					type : "post",
					data : {
						usernameData : $("#username").val(),
					},
					success : function(responseText) {
						console.log(responseText);
						if(responseText=="sudahada"){
							$("#cekUsername").attr("class","error");
							$("#cekUsername").html(responseText);
							username = false;
						}else if(responseText=="oke"){
							$("#cekUsername").attr("class","oke");
							$("#cekUsername").html("Username tersedia");
							username = true;
						}
							
					}});
		 	}
			else
				username = false;
		});
		$("#nama").keyup(function() {
			if ($(this).val() != "")
				nama = true;
			else
				nama = false;
		});

		$("#sandi1").keyup(function() {
			if ($(this).val() != "") {
				if ($("#sandi1").val() == $("#sandi2").val()) {
					$("#cocok").attr("class", "oke");
					$("#cocok").html("sandi cocok");
					sandi2 = true;

				} else {
					$("#cocok").attr("class", "error");
					$("#cocok").html("sandi tdk cocok");
					sandi2 = false;
				}
			}
		});

		$("#sandi2").keyup(function() {
			if ($(this).val() != "") {
				if ($("#sandi1").val() == $("#sandi2").val()) {
					$("#cocok").attr("class", "oke");
					$("#cocok").html("sandi cocok");
					sandi2 = true;

				} else {
					$("#cocok").attr("class", "error");
					$("#cocok").html("sandi tdk cocok");
					sandi2 = false;

				}
			}
		});

		$("#klikdaftar").click(function() {
		
			if (username && nama && sandi2) {

			if($("#idpengguna").val()!=null){
			
			$.ajax({
					url : "prosesEditUser",
					type : "post",
					data : {
						id : $("#idpengguna").val(),
						username : $("#username").val(),
						nama : $("#nama").val(),
						katasandi : $("#sandi1").val()
						
						},
					success : function(responseText) {
						console.log(responseText);
						if(responseText == "editok"){
							$("#form").attr("class","oke");
							$("#form").html("<h2>update berhasil! kembali ke <a href='beranda'>beranda</a></h2>");
						}else {
							$("#msg").attr("class","error");
							$("#msg").html("update gagal :" +responseText);
						}
					}
				}
				);
			}else{
				$.ajax({
					url : "prosesRegistrasi",
					type : "post",
					data : {
						username : $("#username").val(),
						nama : $("#nama").val(),
						katasandi : $("#sandi1").val()},
					success : function() {
						$("#form").attr("class","oke");
						$("#form").html("<h2>pendaftaran berhasil! Silakan <a href='masuk'>masuk</a></h2>");},
					error: function(){
						$("#msg").attr("class","error");
						$("#msg").html("pendaftaran gagal");
						}
				});
				}
			}
		});
	});
</script>

</head>
<body>
	<div class="container">
		<div class="header">
		</div>
		<div class="content">
			<div id="form" align="center">
			<h2>Formulir Registrasi</h2>
			<p id="msg"></p>
			<c:if test="${user ==null }">
			<form action="" method="post">
				<table cellpadding="5">
					<tr>
						<td><label>Username</label></td>
						<td><input type="text" name="username" id="username" /></td>
						<td width="150"> <span
							id="cekUsername"></span></td>
					</tr>
					<tr>
						<td><label>Nama</label></td>
						<td><input type="text" name="nama" id="nama" /></td>
					</tr>
					<tr>
	
						<td><label>Password</label></td>
						<td><input type="password" name="katasandi" id="sandi1" /></td>
					</tr>
					<tr>
						<td><label>Ulangi pasword</label></td>
						<td><input type="password" name="re-katasandi" id="sandi2" /></td>
						<td width="150"> <span
							id="cocok"></span></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="button" value="Daftar" id="klikdaftar" /></td>
					</tr>
				</table>
			</form>
			</c:if>
			<c:if test="${user != null }">
			<form action="" method="post">
				<table cellpadding="5">
					<tr>
						<td><label>Username</label></td>
						<td><input type="text" name="username" id="username" value="<c:out value='${user.username }'/>" disabled="disabled" /></td>
						<input type="hidden" name="idpengguna" id="idpengguna" value="<c:out value='${user.id }'/>" />
					</tr>
					<tr>
						<td><label>Nama</label></td>
						<td><input type="text" name="nama" id="nama" value="<c:out value='${user.nama }'/>"/></td>
					</tr>
					<tr>
	
						<td><label>Password</label></td>
						<td><input type="password" name="katasandi" id="sandi1" value="<c:out value='${user.katasandi }'/>" /></td>
					</tr>
					<tr>
						<td><label>Ulangi pasword</label></td>
						<td><input type="password" name="re-katasandi" id="sandi2" value="<c:out value='${user.katasandi }'/>" /></td>
						<td width="150"> <span
							id="cocok"></span></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="button" value="Daftar" id="klikdaftar" /></td>
					</tr>
				</table>
			</form>
			</c:if>
			
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>