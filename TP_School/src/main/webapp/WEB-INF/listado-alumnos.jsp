<%@ page import="entities.Alumno" %>
<%@ page import="entities.Persona" %>
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
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">
    	<!-- Bootstrap 5 CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
		<!-- Font Awesome CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
	<!-- Font Awesome CSS -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	
	<title>School management</title>

    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="style/signin.css" rel="stylesheet">
    
    <%
    Persona p = (Persona) session.getAttribute("user");
    LinkedList<Alumno> la = (LinkedList<Alumno>) request.getAttribute("alumnos");
    %>
    
</head>
<body style="background-color: #07393b; height: auto">
<div class="bg-light" style="margin-top: -20px;padding:30px; border-radius: 15px">
 <% if(request.getAttribute("msg") != null) {%>
    <div class="alert alert-success" role="alert">
      <span><i class="fa fa-check-circle"></i></span>
      	${requestScope.msg}
    </div>
 <% } %>
<div class="pl-4 align-text-bottom">
	<a class="btn btn-secondary pull-right" href="index.html">Cerrar Sesión</a>
	<a class="btn btn-primary pull-right" href="signin?option=add"><span><i class="fa fa-plus"></i></span> Añadir alumno</a>
	<h1 class="h4 text-center pb-2">Alumnos</h1>
</div>
<div>
<table class="table table-hover align-middle">
  <thead class="table-dark">
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Nombre</th>
      <th scope="col">Apellido</th>
      <th scope="col">Legajo</th>
      <th scope="col">DNI</th>
      <th scope="col">Email</th>
      <th scope="col">Teléfono</th>
      <th scope="col">Fecha Nacimiento</th>
      <th scope="col">Regular</th>
      <th></th>
      <th></th>
    </tr>
  </thead>
  <tbody>
 <% for (Alumno a: la) { %>
     <tr title="<%=a.getUltInscripcion()!=null?a.getUltInscripcion().getCurso().getNombre():"No inscripto a ningún"%> año - <%=a.getUltInscripcion()!=null?a.getUltInscripcion().getCurso().getModalidad():""%>">
      <th scope="row" ><%=a.getIdAlumno() %></th>
      <td><%=a.getNombre()%></td>
      <td><%=a.getApellido()%></td>
      <td><%=a.getLegajo()%></td>
      <td><%=a.getDni()%></td>
      <td><%=a.getMail()%></td>
      <td><%=a.getTel()%></td>
      <td><%=a.getFechaNacFormat()%></td>
      <td><div class="form-check form-switch">
  			<input style="background-color: <%=a.isRegular()?"#0d6efd":"#fff" %>" class="form-check-input" 
  				type="checkbox" role="switch" id="isregular" <%=a.isRegular()?"checked":"" %> disabled>
  			<label class="form-check-label" for="isregular"><%=a.isRegular()?"Sí":"No " %></label>
		  </div>
	  </td>
	  <td><a href="signin?option=edit&id=<%=a.getDni()%>" class="mt-3" style="margin-bottom: 0">Editar</a></td>
      <td><a href="signin?option=darbaja&id=<%=a.getIdAlumno()%>" class="mt-3" style="margin-bottom: 0">Eliminar</a></td>
    </tr>
  <% } %>
  </tbody>
</table>
</div>
</div>
</body>
</html>