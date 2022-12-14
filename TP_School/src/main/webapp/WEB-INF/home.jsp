<%@ page import="entities.Alumno" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!doctype html>
<html lang="en">
<head>
<title>Inicio</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="style/style.css">
<!-- Bootstrap core CSS -->
<link href="style/bootstrap.css" rel="stylesheet">
<script nonce="82c3f7c0-ec11-4d94-8c3b-6364efad306b">(function(w,d){!function(cM,cN,cO,cP){cM.zarazData=cM.zarazData||{};cM.zarazData.executed=[];cM.zaraz={deferred:[],listeners:[]};cM.zaraz.q=[];cM.zaraz._f=function(cQ){return function(){var cR=Array.prototype.slice.call(arguments);cM.zaraz.q.push({m:cQ,a:cR})}};for(const cS of["track","set","debug"])cM.zaraz[cS]=cM.zaraz._f(cS);cM.zaraz.init=()=>{var cT=cN.getElementsByTagName(cP)[0],cU=cN.createElement(cP),cV=cN.getElementsByTagName("title")[0];cV&&(cM.zarazData.t=cN.getElementsByTagName("title")[0].text);cM.zarazData.x=Math.random();cM.zarazData.w=cM.screen.width;cM.zarazData.h=cM.screen.height;cM.zarazData.j=cM.innerHeight;cM.zarazData.e=cM.innerWidth;cM.zarazData.l=cM.location.href;cM.zarazData.r=cN.referrer;cM.zarazData.k=cM.screen.colorDepth;cM.zarazData.n=cN.characterSet;cM.zarazData.o=(new Date).getTimezoneOffset();if(cM.dataLayer)for(const cZ of Object.entries(Object.entries(dataLayer).reduce(((c_,da)=>({...c_[1],...da[1]})))))zaraz.set(cZ[0],cZ[1],{scope:"page"});cM.zarazData.q=[];for(;cM.zaraz.q.length;){const db=cM.zaraz.q.shift();cM.zarazData.q.push(db)}cU.defer=!0;for(const dc of[localStorage,sessionStorage])Object.keys(dc||{}).filter((de=>de.startsWith("_zaraz_"))).forEach((dd=>{try{cM.zarazData["z_"+dd.slice(7)]=JSON.parse(dc.getItem(dd))}catch{cM.zarazData["z_"+dd.slice(7)]=dc.getItem(dd)}}));cU.referrerPolicy="origin";cU.src="/cdn-cgi/zaraz/s.js?z="+btoa(encodeURIComponent(JSON.stringify(cM.zarazData)));cT.parentNode.insertBefore(cU,cT)};["complete","interactive"].includes(cN.readyState)?zaraz.init():cM.addEventListener("DOMContentLoaded",zaraz.init)}(w,d,0,"script");})(window,document);</script>
<% Alumno a = (Alumno) session.getAttribute("user");%>
</head>
<body style="background-color: #07393b">
<div class="page">
<nav id="colorlib-main-nav" role="navigation">
<a href="#" class="js-colorlib-nav-toggle colorlib-nav-toggle active"><i></i></a>
<div class="js-fullheight colorlib-table" style="background-color: #07393b">
<div class="img" style="background-image: url(images/bg_3.jpg);"></div>
<div class="colorlib-table-cell js-fullheight" style="background-color: #07393b">
<div class="contenedor bg-info row no-gutters">
<div class="bg-light hijo text-center p-4 rounded-5" style="border-radius: 15px; width: 28rem;background-color: #07393b">
<h1 class="mb-4">MENÚ DE OPCIONES</h1>
<ul>
<li class="active"><a href="signin?option=home"><span>Home</span></a></li>
<li><a href="signin?option=report-card" style="color: #000"><span style="color: #000">Boletín online</span></a></li>
<li><a href="signin?option=inscription"><span style="color: #000">Inscripciones</span></a></li>
<li><a href="signin?option=edit"><span style="color: #000">Editar mis datos</span></a></li>
<li><a href="index.html"><span style="color: #000">Cerrar sesión</span></a></li>
</ul>
</div>
</div>
</div>
</div>
</nav>
<div id="colorlib-page">
<header>
<div class="container">
<div class="colorlib-navbar-brand">
<a class="colorlib-logo" href="#">Company Logo</a>
</div>
<a href="#" class="js-colorlib-nav-toggle colorlib-nav-toggle"><i></i></a>
</div>
</header>
<section class="hero-wrap js-fullheight" style="background-color: #07393b">
<div class="container px-0">
<div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center" data-scrollax-parent="true">
<div class="col-md-12 ftco-animate text-center">
<div class="desc">
<h1>BIENVENIDO</h1>
<h3><%=a.getNombre()+" "+a.getApellido()%></h3>
</div>
</div>
</div>
</div>
</section>
</div>
</div>
<script src="style/jquery-min.js"></script>
<script src="style/popper.js"></script>
<script src="style/bootstrapmin.js"></script>
<script src="style/main.js"></script>
<script defer src="https://static.cloudflareinsights.com/beacon.min.js/vaafb692b2aea4879b33c060e79fe94621666317369993" integrity="sha512-0ahDYl866UMhKuYcW078ScMalXqtFJggm7TmlUtp0UlD4eQk0Ixfnm5ykXKvGJNFjLMoortdseTfsRT8oCfgGA==" data-cf-beacon='{"rayId":"776a839e58f6f806","token":"cd0b4b3a733644fc843ef0b185f98241","version":"2022.11.3","si":100}' crossorigin="anonymous"></script>
</body>
</html>