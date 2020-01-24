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
	var hal =1;
 	$.ajax({
		url : "catatan",
		type : "post",
									//dataType : "json",
		data : {
			halaman : "formalitas",
		},
		success : function(responseText) {
			$(".halaman").empty();
			var currentPage = parseInt(hal);
			if(currentPage>1){
				var prev = currentPage -1;
				$(".halaman")
					.append("<a  href='#' halaman="+prev+" id='halaman"+prev+"' onclick=pindahHalaman('halaman"+ prev+ "')>prev</a>");
			}
			for (var i = 1; i <= parseInt(responseText); i++) {
				if(currentPage == i){
					$(".halaman")
						.append("<a  href='#' halaman="+ i+ " id='halaman"+ i+ "' onclick=pindahHalaman('halaman"+ i+ "')><b><u>("+ i+ ")</u></b></a>");
					}else{
					$(".halaman")
						.append("<a  href='#' halaman="+ i+ " id='halaman"+ i+ "' onclick=pindahHalaman('halaman"+ i+ "')>("+ i+ ")</a>");
				}
			}
			if(currentPage<parseInt(responseText)){
				var next = currentPage +1;
				$(".halaman")
						.append("<a  href='#' halaman="+next+" id='halaman"+next+"' onclick=pindahHalaman('halaman"+ next+ "')>next</a>");
			}
			$.ajax({
					url : "catatan",
					type : "post",
					//dataType : "json",
					data : {
						tampil : "1",
					},
					success : function(data) {
						$(".susunanBlog").empty();			
						var post = $.parseJSON(JSON.stringify(data));
						for(var i=0;i<post.length;i++){
							$(".susunanBlog").append("<div class='post'>"
							+"<a href='catatan?action=baca&id="+post[i].idpost+"'><h2>"+post[i].judul+"</h2></a>"
							+"<p>"+post[i].kontenfreehtml.substring(0,200)+"</p>"
							+"</div>");  
						}
					},
					error : function() {
						$(".susunanBlog").empty();
						alert("tidak ditemukan");
					}
				});
		},
		error : function() {
			$(".atas").empty();
			alert("tidak ditemukan");
		}
	});
});
</script>
</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="content">
			<h2 align="center">Halaman Blog</h2>
			<div class="halaman" align="center">
					Halaman:<br> 
			</div>
			<div class="susunanBlog"></div>
				<script type="text/javascript">
					function pindahHalaman(id) {
						/* alert($("#" + id).attr("halaman"));
						 */var hal = $("#" + id).attr("halaman");
								$.ajax({
									url : "catatan",
									type : "post",
									//dataType : "json",
									data : {
										halaman : hal,
									},
									success : function(responseText) {
										
										$(".halaman").empty();
										var currentPage = parseInt(hal);
										var prev = currentPage -1;
										if(currentPage>1){
											$(".halaman")
													.append("<a  href='#' halaman="+prev+" id='halaman"+prev+"' onclick=pindahHalaman('halaman"+ prev+ "')>prev</a>");
										}for (var i = 1; i <= parseInt(responseText); i++) {
										
												if(currentPage == i){
													$(".halaman")
														.append("<a  href='#' halaman="+ i+ " id='halaman"+ i+ "' onclick=pindahHalaman('halaman"+ i+ "')><b><u>("+ i+ ")</u></b></a>");
													}else{
													$(".halaman")
														.append("<a  href='#' halaman="+ i+ " id='halaman"+ i+ "' onclick=pindahHalaman('halaman"+ i+ "')>("+ i+ ")</a>");
												}
											
										}
										if(currentPage<parseInt(responseText)){
										 var next = currentPage +1;
											$(".halaman")
											.append("<a  href='#' halaman="+next+" id='halaman"+next+"' onclick=pindahHalaman('halaman"+ next+ "')>next</a>");
										 }
										$.ajax({
											url : "catatan",
											type : "post",
											//dataType : "json",
											data : {
												tampil : hal,
											},
											success : function(data) {
												$(".susunanBlog").empty();			
												var post = $.parseJSON(JSON.stringify(data));
												
												for(var i=0;i<post.length;i++){
													$(".susunanBlog").append("<div class='post'>"
														+"<a href='catatan?action=baca&id="+post[i].idpost+"'><h2>"+post[i].judul+"</h2></a>"
														+"<p>"+post[i].kontenfreehtml.substring(0,200)+"</p></b>"
														+"</div>");  
												}
											},
											error : function() {
												$(".susunanBlog").empty();
												alert("tidak ditemukan");
											}
										});
									},
									error : function() {
										$(".atas").empty();
										alert("tidak ditemukan");
									}

								});
					}
				</script>
			
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>