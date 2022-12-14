<%@ page import="entities.*" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.Inscripcion" %>
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
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	
	<title>School management</title>

    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/signin.css" rel="stylesheet">
    <link href="style/fechas.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  	<style>
  		.dropdown-toggle:hover{
			color: #b8b9ff !important;
		}
  	</style>
    <%
    Alumno a = (Alumno) session.getAttribute("user");
    LinkedList<Inscripcion> li = (LinkedList<Inscripcion>) request.getAttribute("inscripciones");
    LinkedList<Nota> ln = null;
    if(request.getAttribute("calificaciones")!=null) {
    	ln = (LinkedList<Nota>) request.getAttribute("calificaciones");
    };%>
</head>
<body style="background-color: #07393b; height: auto">
<div class="bg-light" style="margin-top: -20px;width: 780px; padding:30px; border-radius: 15px">
<div class="dropdown mb-3">
  <button class="btn btn-secondary dropdown-toggle" style="color:#FFFFFF;background-color: #07393b" data-toggle="dropdown" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown">
    Seleccione un curso...
  </button>
  <ul class="dropdown-menu">
  <% for (Inscripcion ins : li) { %>
    <li><a class="dropdown-item" href="signin?option=report-card&cour=<%=ins.getCurso().getIdCurso()%>&mod=<%=ins.getCurso().getIdModalidad()%>"><%=ins.getCurso().getNombre()%> Año (<%=ins.getFechaInscripcion().getYear()%>)</a></li>
  <% } %>
  </ul>
</div>
<div class="p-2 mb-1" style="background-color: #19c2c929">
<div class="row pl-4 align-text-bottom">
<h1 class="col-md-5 h4">Alumno: <%=a.getApellido()+", "+a.getNombre()%></h1>
<h1 class="col-md-3 h4">Legajo: <%=a.getLegajo() %></h1>
</div>
<% if(!(request.getParameter("cour")==null || request.getParameter("mod")==null)) { %>
<h2 class="h5 pl-4">Curso: <%=ln.getFirst().getAsignatura().getCurso().getNombre()%> Año - <%=ln.getFirst().getAsignatura().getCurso().getModalidad()%></h2>
<% } %>
</div>
<div>
<table class="table table-hover align-middle">
  <thead class="table-dark">
    <tr>
      <th scope="col">Materia</th>
      <th scope="col">Nota Final</th>
      <th scope="col">Nota 1C</th>
      <th scope="col">Nota 2C</th>
      <th scope="col">Nota 3C</th>
    </tr>
  </thead>
  <tbody>
 <% if(!(request.getParameter("cour")==null || request.getParameter("mod")==null)) { %>
 <% for (Nota n : ln) { %>
   <% if (n.getAsignatura().getCurso().getIdCurso()==Integer.parseInt(request.getParameter("cour")) 
   		&& n.getAsignatura().getCurso().getIdModalidad()==Integer.parseInt(request.getParameter("mod"))) { %>
    <tr>
      <th scope="row"><%=n.getAsignatura().getMateria().getNombre() %></th>
      <td class="<%=n.getNotaFinal()>=6?"table-success":n.getNotaFinal()==0?"table-light":"table-danger"%>"><%=n.getNotaFinal()==0?"-":n.getNotaFinal()%></td>
      <td><%=n.getNota1C()==0?"-":n.getNota1C() %></td>
      <td><%=n.getNota2C()==0?"-":n.getNota2C() %></td>
      <td><%=n.getNota3C()==0?"-":n.getNota3C() %></td>
    </tr>
  <% } %>
<% } %>
<% } %>
  </tbody>
</table>
</div>
<a class="btn btn-lg btn-secondary btn-block mt-4" href="signin?option=main" style="float: right;width: 170px; height: 48px">Volver</a>
</div>
<script>
$(document).ready(function(){
  $(".dropdown-toggle").dropdown();
});
</script>
</body>
</html>