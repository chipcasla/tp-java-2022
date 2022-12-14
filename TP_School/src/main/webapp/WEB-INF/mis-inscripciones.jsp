<%@ page import="entities.Alumno" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.Inscripcion" %>
<%@ page import="entities.Curso" %>
<%@ page import="java.time.LocalDate"  %>
<%@ page import="java.util.LinkedList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">
    	<!-- Bootstrap 5 CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
		<!-- Font Awesome CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<!-- Font Awesome CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	<title>School management</title>

    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/signin.css" rel="stylesheet">
    <link href="style/fechas.css" rel="stylesheet">
	
    <% Alumno a = (Alumno) session.getAttribute("user");
    LinkedList<Inscripcion> li = (LinkedList<Inscripcion>) request.getAttribute("inscripciones");%>
</head>
<body style="background-color: #07393b; <%=li.size()>3?"height: fit-content":""%>">
<div class="bg-light px-5" style="width:800px;margin: 10px;padding:20px; border-radius: 15px">
<div class="form-row" style="position: relative">
<span style="color: #6c757d"><i class="fa fa-history fa-2x pb-4 pt-4 pl-3"></i></span>
<div class="align-middle">
  <h1 class="h3 text-secondary pb-4 pt-4 pl-4 pr-5">Inscripciones - Historial</h1>
</div>
<div class="align-middle">
<a class="btn btn-primary mt-4 mb-4" href="signin?option=inscribir" style="margin-left: 100px;height: 35px;width: 170px">+ Añadir inscripción</a>
</div>
</div>
<% if(request.getAttribute("validaciones") != null) {%>
      <div class="alert-danger alert alert-dismissable align-middle">
      	${requestScope.validaciones}
      </div>
  <% } %>
  <% if(request.getAttribute("msg") != null) {%>
      <div class="alert alert-success" role="alert">
      	<span><i class="fa fa-check-circle"></i></span>
      	${requestScope.msg}
      </div>
<% } %>
<ol class="list-group list-group w-100">
<% for (Inscripcion ins : li) { %>
  <li class="list-group-item d-flex justify-content-between align-items-start">
    <div class="ms-2 me-auto">
      <div class="h4 fw-bold mr-5"><%=ins.getCurso().getNombre() %> año</div>
      <p class="mt-3" style="margin-bottom: 0">Modalidad: <%=ins.getCurso().getModalidad() %></p>
    </div>
    <small class="text-muted"><%=ins.getFechaInscripcionFormato() %></small>
  </li>
 <% } %>
</ol>
<a class="btn btn-lg btn-secondary btn-block mt-4" href="signin?option=main" style="float: right;width: 170px; height: 48px">Volver</a>
</div>
</body>
</html>