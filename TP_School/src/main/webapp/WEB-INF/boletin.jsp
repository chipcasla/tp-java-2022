<%@ page import="entities.*"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="entities.Inscripcion"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">
<!-- Bootstrap 5 CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
	integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I"
	crossorigin="anonymous">
<!-- Font Awesome CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Font Awesome CSS -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">

<title>School management</title>

<!-- Bootstrap core CSS -->
<link href="style/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="style/signin.css" rel="stylesheet">
<!--<link href="style/fechas.css" rel="stylesheet"> -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.dropdown-toggle:hover {
	color: #b8b9ff !important;
}
</style>

<link rel="stylesheet" href="style/style.css">
<script nonce="82c3f7c0-ec11-4d94-8c3b-6364efad306b">(function(w,d){!function(cM,cN,cO,cP){cM.zarazData=cM.zarazData||{};cM.zarazData.executed=[];cM.zaraz={deferred:[],listeners:[]};cM.zaraz.q=[];cM.zaraz._f=function(cQ){return function(){var cR=Array.prototype.slice.call(arguments);cM.zaraz.q.push({m:cQ,a:cR})}};for(const cS of["track","set","debug"])cM.zaraz[cS]=cM.zaraz._f(cS);cM.zaraz.init=()=>{var cT=cN.getElementsByTagName(cP)[0],cU=cN.createElement(cP),cV=cN.getElementsByTagName("title")[0];cV&&(cM.zarazData.t=cN.getElementsByTagName("title")[0].text);cM.zarazData.x=Math.random();cM.zarazData.w=cM.screen.width;cM.zarazData.h=cM.screen.height;cM.zarazData.j=cM.innerHeight;cM.zarazData.e=cM.innerWidth;cM.zarazData.l=cM.location.href;cM.zarazData.r=cN.referrer;cM.zarazData.k=cM.screen.colorDepth;cM.zarazData.n=cN.characterSet;cM.zarazData.o=(new Date).getTimezoneOffset();if(cM.dataLayer)for(const cZ of Object.entries(Object.entries(dataLayer).reduce(((c_,da)=>({...c_[1],...da[1]})))))zaraz.set(cZ[0],cZ[1],{scope:"page"});cM.zarazData.q=[];for(;cM.zaraz.q.length;){const db=cM.zaraz.q.shift();cM.zarazData.q.push(db)}cU.defer=!0;for(const dc of[localStorage,sessionStorage])Object.keys(dc||{}).filter((de=>de.startsWith("_zaraz_"))).forEach((dd=>{try{cM.zarazData["z_"+dd.slice(7)]=JSON.parse(dc.getItem(dd))}catch{cM.zarazData["z_"+dd.slice(7)]=dc.getItem(dd)}}));cU.referrerPolicy="origin";cU.src="/cdn-cgi/zaraz/s.js?z="+btoa(encodeURIComponent(JSON.stringify(cM.zarazData)));cT.parentNode.insertBefore(cU,cT)};["complete","interactive"].includes(cN.readyState)?zaraz.init():cM.addEventListener("DOMContentLoaded",zaraz.init)}(w,d,0,"script");})(window,document);</script>

<%
Alumno a = (Alumno) session.getAttribute("user");
@SuppressWarnings("unchecked")
LinkedList<Inscripcion> li = (LinkedList<Inscripcion>) request.getAttribute("inscripciones");

@SuppressWarnings("unchecked")
LinkedList<Nota> ln = (LinkedList<Nota>) request.getAttribute("calificaciones");

%>
</head>
<body style="background-color: #07393b; height: auto">
<%
HttpSession misession = request.getSession();
Alumno alu = (Alumno) session.getAttribute("user");
if (alu == null) {
	response.sendRedirect("index.jsp");
} else {
%>
	
	<div id="colorlib-page">
		<header>
			<div class="container">
				<div class="colorlib-navbar-brand">
					<a class="colorlib-logo" href="#">Company Logo</a>
				</div>
			</div>
		</header>
		<section class="hero-wrap js-fullheight"
			style="background-color: #07393b">
			<div class="bg-light pb-5"
				style="margin-top: 20px; width: 780px; padding-top: 30px; padding-bottom: 10px; padding-right: 30px; padding-left: 30px; border-radius: 15px">
				<div class="container px-0">
				<% if(request.getAttribute("validaciones") != null) {%>
						<div class="alert-danger alert alert-dismissable align-middle">
							${requestScope.validaciones}</div>
						<% } %>
					<div
						class="mb-5 row no-gutters slider-text js-fullheight align-items-center justify-content-center"
						data-scrollax-parent="true">
						<div class="ftco-animate pb-5">
							<div class="dropdown mb-2">
								<button class="btn btn-secondary dropdown-toggle"
									style="color: #FFFFFF; background-color: #07393b; font-size: 12px"
									data-toggle="dropdown" type="button" id="dropdownMenuButton1"
									data-bs-toggle="dropdown">Seleccione un curso...</button>
								<ul class="dropdown-menu">
									<%
									for (Inscripcion ins : li) {
									%>
									<li><a class="dropdown-item" style="font-size: 14px"
										href="Alumno?opcion=boletin&cour=<%=ins.getCurso().getIdCurso()%>&mod=<%=ins.getCurso().getIdModalidad()%>"><%=ins.getCurso().getNombre()%>
											Año (<%=ins.getFechaInscripcion().getYear()%>)</a></li>
									<%
									}
									%>
								</ul>
							</div>
							<div class="p-2 mb-1" style="background-color: #19c2c929">
								<div class="row pl-4 align-text-bottom">
									<h1 class="col-md-5 h4">
										Alumno:
										<%=a.getApellido() + ", " + a.getNombre()%></h1>
									<h1 class="col-md-3 h4">
										Legajo:
										<%=a.getLegajo()%></h1>
								</div>
								<%
								if (!(request.getParameter("cour") == null || request.getParameter("mod") == null) && ln != null && !ln.isEmpty()) {
								%>
								<h2 class="h5 pl-4">
									Curso:
									<%=ln.getFirst().getAsignatura().getCurso().getNombre()%>
									Año -
									<%=ln.getFirst().getAsignatura().getCurso().getModalidad()%></h2>
								<%
								}
								%>
							</div>
							<div>
								<table class="table table-hover align-middle">
									<thead class="table-dark">
										<tr>
											<th scope="col">Materia</th>
											<th scope="col">Nota 1C</th>
											<th scope="col">Nota 2C</th>
											<th scope="col">Nota 3C</th>
											<th scope="col">Nota Final</th>
										</tr>
									</thead>
									<tbody>
										<%
										if (!(request.getParameter("cour") == null || request.getParameter("mod") == null) && ln != null && !ln.isEmpty()) {
										%>
										<%
										for (Nota n : ln) {
										%>
										<%
										if (n.getAsignatura().getCurso().getIdCurso() == Integer.parseInt(request.getParameter("cour"))
												&& n.getAsignatura().getCurso().getIdModalidad() == Integer.parseInt(request.getParameter("mod"))
												&& !(n.getNota1C() == 0 && n.getNotaFinal() == 0)) {
										%>
										<tr>
											<th scope="row"><%=n.getAsignatura().getMateria().getNombre()%></th>
											<td><%=n.getNota1C() == 0 ? "-" : n.getNota1C()%></td>
											<td><%=n.getNota2C() == 0 ? "-" : n.getNota2C()%></td>
											<td><%=n.getNota3C() == 0 ? "-" : n.getNota3C()%></td>
											<td
												class="<%=n.getNotaFinal() >= 6 ? "table-success" : n.getNotaFinal() == 0 ? "table-light" : "table-danger"%>"><%=n.getNotaFinal() == 0 ? "-" : n.getNotaFinal()%></td>

										</tr>
										<%
										}
										%>
										<%
										}
										%>
										<%
										}
										%>
									</tbody>
								</table>
							</div>
							<a class="btn btn-lg btn-secondary btn-block mt-3 mb-4"
								href="Alumno?opcion=main"
								style="font-size: 15px; padding-top: 0.7rem; float: right; width: 100px;">Volver</a>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	<script>
$(document).ready(function(){
  $(".dropdown-toggle").dropdown();
});
</script>
	<script src="style/jquery-min.js"></script>
	<script src="style/popper.js"></script>
	<script src="style/bootstrapmin.js"></script>
	<script src="style/main.js"></script>
	<script defer
		src="https://static.cloudflareinsights.com/beacon.min.js/vaafb692b2aea4879b33c060e79fe94621666317369993"
		integrity="sha512-0ahDYl866UMhKuYcW078ScMalXqtFJggm7TmlUtp0UlD4eQk0Ixfnm5ykXKvGJNFjLMoortdseTfsRT8oCfgGA=="
		data-cf-beacon='{"rayId":"776a839e58f6f806","token":"cd0b4b3a733644fc843ef0b185f98241","version":"2022.11.3","si":100}'
		crossorigin="anonymous"></script>
<% } %>
</body>
</html>