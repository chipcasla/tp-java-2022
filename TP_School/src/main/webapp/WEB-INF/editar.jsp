<%@ page import="entities.Alumno"%>
<%@ page import="entities.Persona"%>
<%@ page import="java.time.LocalDate"%>

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

<% Alumno a = request.getAttribute("mi-alumno")==null?(Alumno) session.getAttribute("user"):(Alumno) request.getAttribute("mi-alumno");
String myServlet = request.getAttribute("servlet")==null?"Alumno?opcion=main":(String)request.getAttribute("servlet");
String action = request.getAttribute("action")==null?"EditarAlumno":(String)request.getAttribute("action");
%>
</head>
<body style="background-color: #07393b">
<%
HttpSession misession = request.getSession();
Persona pers = (Persona) session.getAttribute("user");
if (pers == null) {
	response.sendRedirect("index.jsp");
} else {
%>
	<div class="bg-light"
		style="margin: 10px; padding: 20px; border-radius: 15px">
		<form action="<%=action %>" method="post" style="padding: 15px">
			<div class="form-row">
				<div>
					<span style="color: #6c757d"><i
						class="fa fa-user-edit fa-2x pb-4 pt-3 pl-3"></i></span>
				</div>
				<div class="align-middle">
					<h1 class="text-secondary pb-4 pt-4 px-4" style="font-size: 1.5em">Formulario
						- Editar mis datos</h1>
				</div>
				<% if(request.getAttribute("validaciones") != null) { %>
				<div class="alert-danger alert alert-dismissable align-middle">
					${requestScope.validaciones}</div>
				<% } %>
				<% if(request.getAttribute("msg") != null) {%>
				<div class="alert alert-success" role="alert">
					<span><i class="fa fa-check-circle"></i></span> ${requestScope.msg}
				</div>
				<% } %>
			</div>
			<div class="form-row">
				<% if(session.getAttribute("user").getClass() == Persona.class) { %>
				<input type="hidden" id="id" name="id" value="<%=a.getIdAlumno() %>">
				<input type="hidden" id="tipo-action" name="opc" value="editar">

				<% } %>
				<div class="col-md-4 mb-3">
					<label for="validationDefault01">Nombre</label> <input type="text"
						class="form-control" id="validationDefault01" name="first-name"
						required placeholder="Nombre"
						value="<%=request.getAttribute("nom")==null?a.getNombre():request.getAttribute("nom")%>"
						required>
				</div>
				<div class="col-md-3 mb-3">
					<label for="validationDefault02">Apellido</label> <input
						type="text" class="form-control" id="validationDefault02"
						name="last-name" required placeholder="Apellido"
						value="<%=a.getApellido()%>" required>
				</div>
				<div class="col-md-5 mb-3">
					<label for="validationDefaultMail">Correo electrónico</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroupPrepend2">@</span>
						</div>
						<input type="email" class="form-control"
							id="validationDefaultMail" name="email" required
							placeholder="Correo" value="<%=a.getMail()%>" required>
					</div>
				</div>
			</div>
			<div class="form-row">
				<div class="col-md-2 mb-3">
					<label for="disabledLegajo">Legajo</label> <input
						class="form-control" id="disabledLegajo" type="text" required
						value="<%=a.getLegajo()%>" disabled>
				</div>
				<div class="col-md-4 mb-3">
					<label for="validationDefault03">Nro. Documento</label> <input
						type="text" inputmode="numeric" pattern="[0-9]{8}"
						oninvalid="setCustomValidity('Por favor, ingrese sus 8 dígitos de DNI')"
						onchange="try{setCustomValidity('')}catch(e){}"
						class="form-control" id="validationDefault03" name="dni" required
						placeholder="DNI" value="<%=a.getDni()%>" required>
				</div>
				<div class="col-md-3 mb-3">
					<label for="validationDefault04">Teléfono</label> <input type="tel"
						inputmode="numeric" pattern="[0-9]{10}"
						oninvalid="setCustomValidity('Por favor, ingrese telefono con 10 dígitos  (sin prefijo 15 ni +549)')"
						onchange="try{setCustomValidity('')}catch(e){}"
						class="form-control" id="validationDefault04" name="tel" required
						placeholder="Teléfono" value="<%=a.getTel()%>" required>
				</div>
				<div class="col-md-3 mb-3 form-date">
					<label class="form-date__label" for="input-date">Fecha de
						nacimiento</label> <input class="form-control form-date__input"
						type="date" id="input-date" name="date-birth"
						max="<%=LocalDate.now()%>" required value="<%=a.getFechaNac()%>" />
				</div>
			</div>
			<div class="form-row">
				<% if(session.getAttribute("user").getClass() == Persona.class) { %>
				<div class="col-md-4 mx-4 mb-4">
					<input class="form-check-input" type="checkbox" name="isregular"
						value="1" id="isregular" <%=a.isRegular()?"checked":"" %>>
					<label class="form-check-label mx-1 mt-1" for="isregular">Regular</label>
				</div>
				<% } %>
			</div>
			<div class="form-row">
				<a href="#"><span class="link mb-4 mt-3 m-2">Cambiar
						contraseña</span></a>
			</div>
			<div class="row align-bottom">
				<button class="btn btn-lg btn-primary btn-block mt-4 mx-3"
					type="submit"
					style="border-color: #202f81; background-color: #07393b; width: 170px; height: 48px">Confirmar</button>
				<button class="btn btn-lg btn-secondary btn-block mt-4 mx-3"
					type="reset" style="width: 170px; height: 48px">Restaurar</button>
				<a class="btn btn-lg btn-secondary btn-block mt-4 mx-3"
					href="<%=myServlet%>" style="width: 170px; height: 48px">Volver</a>
			</div>
		</form>
	</div>
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