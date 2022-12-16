<%@page import="java.util.LinkedList"%>
<%@ page import="entities.*" %>

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
    Curso c = (Curso) request.getAttribute("disponible");%>
</head>
<body style="background-color: #07393b">
<div class="bg-light" style="margin: 10px; padding:30px; border-radius: 15px">
<form action="new-inscription" method="post">
<div class="form-row">
  <div>
  	<span style="color: #6c757d"><i class="fa fa-folder-plus fa-2x pb-4 pt-3 pl-3"></i></span>
  </div>
  <div class="align-middle">
  	<h1 class="text-secondary pb-4 pt-3 px-4" style="font-size: 1.5em">Nueva Inscripcion</h1>
  </div>
 <% if(request.getAttribute("validaciones") != null) {%>
      <div class="alert-danger alert alert-dismissable align-middle">
      	${requestScope.validaciones}
      </div>
  <% } %>
</div>
<input type="hidden" id="opc" name="opc" value="editarinscripcion">
<div class="form-row" >
<div class="col-md-2 mb-3">
      <label for="validationDefault01">Legajo</label>
      <input type="text" class="form-control" id="validationDefault01" name="legajo" required placeholder="Legajo" 
      value="<%=session.getAttribute("user")!=null?a.getLegajo():""%>" <%=session.getAttribute("user")!=null?"disabled":"" %>>  
</div>
<div class="col-md-4 mb-3 mx-2">
<label for="cur">Curso</label>
<select id="cur" name="cur" required class="form-select" onchange="cargaModalidad();" aria-label="Select course">
  <option value="" disabled>Seleccione curso...</option>
  <% if(c != null) { %>
  <option  selected value="<%=c.getNombre()%>"><%=c.getNombre()%> año</option>
  <% } %>
</select>
</div>
<div class="col-md-4 mb-3">
<label for="mod">Modalidad</label>
<select id="mod" name="mod" required onfocus="cargaModalidad();" class="form-select" aria-label="Select mod">
  <option value="" disabled selected>Seleccione modalidad...</option>
  
</select>
</div>
</div>
<div class="row align-bottom">
<button class="btn btn-lg btn-primary btn-block mt-4 ml-3 mr-4" type="submit" style="width: 190px;height: 48px;background-color: #07393b">
<span><i class="fa fa-pen"></i></span> Editar</button>
<a class="btn btn-lg btn-secondary btn-block mt-4" href="signin?option=inscription" style="width: 160px; height: 48px">Volver</a>
</div>
</form>
</div>
<script src="style/manejo-select.js"></script>
</body>
</html>