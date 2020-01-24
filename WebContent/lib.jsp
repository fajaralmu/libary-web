<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Latihan Perpustakaan</title>
<%@ include file="header.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {

		$("#tombolcari").click(function() {
			if ($("#katakunci").val() == "")
				return;
				
			$(".hasilcari").html("Harap tunggu...");
			$.ajax({
				url : "cariBuku",
				type : "post",
				dataType: "json",
				data : {
					katakunci : $("#katakunci").val(),
					filter : $("#filter").val()
				},
				success : function(data) {
					alert(data);	
					$(".hasilcari").empty();			
					var buku = $.parseJSON(JSON.stringify(data));
					
					for(var i=0;i<buku.length;i++){
						 $(".hasilcari").append("<div class='buku'>"
						+"<a href='buku?id="+buku[i].id+"'><h2>"+buku[i].judul+"</h2></a>"
						+"<p>kode: "+buku[i].kode+"</p>"
						+"<p>penulis: "+buku[i].penulis+"</p>"
						+"</div>");  
					}
				},error: function(){
					$(".hasilcari").empty();	
					alert("tidak ditemukan");
				}

			});
		});
	});
</script>
<style type="text/css">

.hasilcari{

padding: 10px;
}

</style>
</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="content">
			<h2 align="center">Selamat Datang di Aplikasi Perpustakaan al
				Makataba</h2>
			<form class="caribuku">
				<input type="search" id="katakunci"
					style="height: 50px; width: 40%; font-size: 25px" /> 
					 
					<select style="width: 220px;height: 50px; font-size: 25px" id="filter">
					<option value="judul" selected>judul</option>
					<option value="penulis" >penulis</option>
					<option value="penerbit" >penerbit</option>
					<option value="kodebuku" >kode buku</option>
					</select>
					<br><br>
					<input type="button" id="tombolcari" value="Cari"
					style="height: 40px; width: 13%; font-size: 25px" />
			</form>
			<div class="hasilcari"></div>
		</div>


		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>