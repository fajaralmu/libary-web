<%@page import="com.fajar.DAO.DAOUser"%>
<%@page import="com.fajar.Controller.ControllerPerpustakaan"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css">
<%--
<%@
include file ="css/bootstrap.css " %> --%> 

p{
	font-size: 20px;
}

.error {
	color: red;
}

.oke {
	color: green;
}

.container {
	
}


.header {
	
	width: 28%;
	position: fixed;
	background-color: buttonface;
	margin-left: auto;
	padding: 10px; 
}

.header > img{
    display: block;
    margin-left: auto;
    margin-right: auto;
   
}

.content {
	background-color: appworkspace;
	float: right;
	min-height: 700px;
	width: 70%;
}

.footer {
	height: 200px;
	background-color: threedshadow;
	margin-bottom: 10px;
	float: right;
	width: 70%;
}

a {
	text-decoration: none;
}

a:VISITED{
	color: blue;
}

/* a:ACTIVE{
	color: green;
	font-size: 30px;
} */

body {
	/*  background-image: url("${pageContext.request.contextPath }/image/bgbook.png");
	 background-repeat: no-repeat; */
	background-attachment: fixed;
	background-color: navy;
	font-family: sans-serif;
}

.menukanan{
	font-size: 25px;
	list-style-type: none;	
	padding: 1px;
}

.menukanan > li{
	
	padding: 5px;
	background-color: navy;
	margin-top: 5px;
	text-align:  center;
}

.menukanan > li:HOVER{
	background-color: teal;
}
.menukanan > li > a{
	color: silver;
	font-family: calibri;
	
}

.caribuku{
	text-align: center;

}


#form{
	border-style: inset;
	width: 70%;
	margin: 0 auto;
	margin-top: 50px;
	padding: 5px; 
	min-height: 300px;
}
</style>
<script type="text/javascript">
	
<%@ include file="js/jquery.js" %>
	
<%@ include file="js/jquery-ui.min.js" %>
	
<%@ include file="js/bootstrap.min.js" %>
	
	$(document).ready(function(){
	/* document.getElementsByTagName("body").a */
	
	
	
	<%
	String namaUser = ""; 
	boolean login = false;
	ControllerPerpustakaan c = new ControllerPerpustakaan();
	DAOUser dou = new DAOUser();
	if(c.sessionUser(request,response)) {
	 namaUser = dou.dapatkanUser(Integer.parseInt(request.getSession().getAttribute("id").toString())).getNama();
	 login = true;
	} %>
	var nama = "<%=namaUser%>";
	var login = <%=login%>;
	$(".header").append("<h1 align='center'>al Maktaba</h1>");
	
	if(login){
	$(".header").append("<ul class='menukanan'>"
				+"<li><a href='beranda'>"+nama+"</a></li>"
				+"<li><a href='catatan'>blog</a></li>"
				//+"<li><a href='myblog?action=semua'>blog</a></li>"
				+"<li><a href='home'>library</a></li>"
				+"<li><a href='daftarBuku'>daftar Buku</a></li>"
				+"<li><a href='keluar'>keluar</a></li>"
				+"</ul>");
	}else{
		$(".header").append("<br><ul class='menukanan'>"
				+"<li><a href='catatan'>blog</a></li>"
				+"<li><a href='home'>library</a></li>"
				+"<li><a href='masuk'>masuk</a></li>"
				+"<li><a href='registrasi'>daftar</a></li>"
				+"<li><a href='daftarBuku'>daftar Buku</a></li>"
				+"</ul>");
		
	}
	
	$("<img src='${pageContext.request.contextPath }/image/logo.png'/>").insertBefore( "ul" );
	
	$(".header").append("<div class='formpesan'></form>");
	
	$(".formpesan").load("form.jsp");
	
	
	
});
	
	
</script>