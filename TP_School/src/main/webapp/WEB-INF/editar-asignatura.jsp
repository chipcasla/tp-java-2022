<%@page import="javax.annotation.processing.SupportedOptions"%>
<%@ page import="java.time.LocalDate"%>
<%@page import="java.util.LinkedList"%>
<%@ page import="entities.*"%>

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
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
<title>School management</title>

<!-- Bootstrap core CSS -->
<link href="style/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../style/signin.css" rel="stylesheet">
<link href="../style/fechas.css" rel="stylesheet">
 
<% 

@SuppressWarnings("unchecked")
LinkedList<Profesor> profesores = (LinkedList<Profesor>) request.getAttribute("profesores");

Asignatura asig = (Asignatura) request.getAttribute("asignatura");

%>
 
</head>
<body style="background-color: #07393b">
	<div class="bg-light"
		style="margin: 10px; padding: 20px; border-radius: 15px">
		<form action="EditarAsignatura" method="post" style="padding: 15px">
			<div class="form-row">
				<div>
					<span style="color: #6c757d"><i
						class="fa fa-pen fa-2x pb-4 pt-3 pl-3"></i></span>
				</div>
				<div class="align-middle">
					<h1 class="text-secondary pb-4 pt-4 px-4" style="font-size: 1.5em">Formulario
						- Editar asignatura</h1>
				</div>
				<% if(request.getAttribute("validaciones") != null) {%>
				<div class="alert-danger alert alert-dismissable align-middle">
					${requestScope.validaciones}</div>
				<% } %>
			</div>
			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<a class="btn btn-secondary"
					href="<%= request.getContextPath()%>/Admin/Materias?idCurso=<%=asig.getCurso().getIdCurso() %>&idMod=<%=asig.getCurso().getIdModalidad() %>" 
					>Volver</a>
			</div>
			<div class="form-row">
				<input type="hidden" id="idCurso" name="idCurso" value="<%= asig.getCurso().getIdCurso() %>">
				<input type="hidden" id="idMod" name="idMod" value="<%= asig.getCurso().getIdModalidad() %>">
				<input type="hidden" id="idMat" name="idMat" value="<%= asig.getMateria().getIdMateria() %>">
				<div class="row px-4">
					<label for="profesor">Seleccionar nuevo profesor:</label>
        			<select class="form-select" name="profesor" id="profesor" required>
        			<option value="" disabled <%=asig.getProfesor().getNombre()==null?"selected":""%>>
        				Seleccione un profesor
		            </option>
		            <% for (Profesor p : profesores) { %>
		                <option value="<%= p.getId() %>" <%=p.getId()==asig.getProfesor().getId()?"selected":""%>>
		                	<%= p.getApellido() %>, <%= p.getNombre() %>
		                </option>
		            <% } %>
        			</select>
        			<br>
				</div>
			</div>
			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
				<button class="btn btn-lg btn-primary btn-block mt-4"
					type="submit"
					style="border-color: #202f81; background-color: #07393b; width: 170px; height: 48px">Confirmar</button>
			</div>
		</form>
	</div>
</body>
</html>