<%@ page import="entities.Alumno"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="entities.Inscripcion"%>
<%@ page import="entities.Curso"%>
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
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
<title>School management</title>

<!-- Bootstrap core CSS -->
<link href="style/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="style/signin.css" rel="stylesheet">
<link href="style/fechas.css" rel="stylesheet">

<link rel="stylesheet" href="style/style.css">
<script nonce="82c3f7c0-ec11-4d94-8c3b-6364efad306b">(function(w,d){!function(cM,cN,cO,cP){cM.zarazData=cM.zarazData||{};cM.zarazData.executed=[];cM.zaraz={deferred:[],listeners:[]};cM.zaraz.q=[];cM.zaraz._f=function(cQ){return function(){var cR=Array.prototype.slice.call(arguments);cM.zaraz.q.push({m:cQ,a:cR})}};for(const cS of["track","set","debug"])cM.zaraz[cS]=cM.zaraz._f(cS);cM.zaraz.init=()=>{var cT=cN.getElementsByTagName(cP)[0],cU=cN.createElement(cP),cV=cN.getElementsByTagName("title")[0];cV&&(cM.zarazData.t=cN.getElementsByTagName("title")[0].text);cM.zarazData.x=Math.random();cM.zarazData.w=cM.screen.width;cM.zarazData.h=cM.screen.height;cM.zarazData.j=cM.innerHeight;cM.zarazData.e=cM.innerWidth;cM.zarazData.l=cM.location.href;cM.zarazData.r=cN.referrer;cM.zarazData.k=cM.screen.colorDepth;cM.zarazData.n=cN.characterSet;cM.zarazData.o=(new Date).getTimezoneOffset();if(cM.dataLayer)for(const cZ of Object.entries(Object.entries(dataLayer).reduce(((c_,da)=>({...c_[1],...da[1]})))))zaraz.set(cZ[0],cZ[1],{scope:"page"});cM.zarazData.q=[];for(;cM.zaraz.q.length;){const db=cM.zaraz.q.shift();cM.zarazData.q.push(db)}cU.defer=!0;for(const dc of[localStorage,sessionStorage])Object.keys(dc||{}).filter((de=>de.startsWith("_zaraz_"))).forEach((dd=>{try{cM.zarazData["z_"+dd.slice(7)]=JSON.parse(dc.getItem(dd))}catch{cM.zarazData["z_"+dd.slice(7)]=dc.getItem(dd)}}));cU.referrerPolicy="origin";cU.src="/cdn-cgi/zaraz/s.js?z="+btoa(encodeURIComponent(JSON.stringify(cM.zarazData)));cT.parentNode.insertBefore(cU,cT)};["complete","interactive"].includes(cN.readyState)?zaraz.init():cM.addEventListener("DOMContentLoaded",zaraz.init)}(w,d,0,"script");})(window,document);</script>

<% Alumno a = (Alumno) session.getAttribute("user");
	@SuppressWarnings("unchecked")
    LinkedList<Inscripcion> li = (LinkedList<Inscripcion>) request.getAttribute("inscripciones");%>
</head>
<body
	style="background-color: #07393b; <%=li.size()>3?"height: fit-content":""%>">
	<%
HttpSession misession = request.getSession();
Alumno alu = (Alumno) session.getAttribute("user");
if (alu == null) {
	response.sendRedirect("index.jsp");
} else {
%>
	<section class="mt-2">
	<div class="bg-light px-5"
		style="margin-top: 60px;padding-top: 10px;width: 800px; padding-bottom: 40px; border-radius: 15px">
			<div class="align-text-bottom">
				<a class="btn btn-primary pull-right mt-4 mb-3"
					href="Inscripcion">+ Añadir inscripción</a>

				<h1 class="h3 text-secondary pb-3 pt-4 pr-5">
					<span style="color: #6c757d"><i class="fa fa-history"></i></span>
					Inscripciones - Historial
				</h1>
			</div>
		<% if(request.getAttribute("validaciones") != null) {%>
		<div class="alert-danger alert alert-dismissable align-middle">
			${requestScope.validaciones}</div>
		<% } %>
		<% if(request.getAttribute("msg") != null) {%>
		<div class="alert alert-success" role="alert">
			<span><i class="fa fa-check-circle"></i></span> ${requestScope.msg}
		</div>
		<% } %>
		<ol class="list-group list-group w-100 mb-3">
			<% for (Inscripcion ins : li) { %>
			<li
				class="list-group-item d-flex justify-content-between align-items-start">
				<div class="ms-2 me-auto">
					<div class="h4 fw-bold mr-5"><%=ins.getCurso().getNombre() %>
						año
					</div>
					<p class="mt-3" style="margin-bottom: 0">
						Modalidad:
						<%=ins.getCurso().getModalidad() %></p>
					<% if(ins.getFechaInscripcion().plusMonths(2).isAfter(LocalDate.now()) && ins.getFechaInscripcion().isEqual(a.getUltInscripcion().getFechaInscripcion())) { %>
					<a href="EditarInscripcion" class="mt-2 mx-2 btn btn-sm btn-info"
						style="margin-bottom: 0">Editar</a> 
						<form action="EliminarInscripcion" method="post">
						<button type="submit" class="mt-2 mx-2 btn btn-sm btn-danger"
						style="margin-bottom: 0">Eliminar</button>
						</form>
					<% } %>
				</div> <small class="text-muted"><%=ins.getFechaInscripcionFormato() %></small>
			</li>
			<% } %>
		</ol>
		<div class="align-text-top">
		<a class="btn btn-secondary pull-right"
			href="Alumno?opcion=main"
			style="font-size: 16px; width: 100px;">Volver</a>
		</div>
	</div>
	</section>
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