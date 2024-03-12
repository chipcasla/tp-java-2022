<%@page import="entities.Materia"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@ page import="entities.Curso"%>
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
<!-- Font Awesome CSS -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">

<title>School management</title>

<!-- Bootstrap core CSS -->
<link href="../style/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="../style/signin.css" rel="stylesheet">

<script type="text/javascript">
	function confirmarDelete() {
		var rta = window
				.confirm("Desea confirmar los cambios?");
		return rta;
	}
</script>
<%
@SuppressWarnings("unchecked")
LinkedList<Materia> materias = (LinkedList<Materia>) request.getAttribute("materiasDisponibles");
@SuppressWarnings("unchecked")
LinkedList<Materia> materiasCurso = (LinkedList<Materia>) request.getAttribute("materiasCurso");

Curso curso = (Curso) request.getAttribute("curso");

String urlVolver = request.getContextPath()+"/Admin/Materias?idCurso="+curso.getIdCurso()+"&idMod="+curso.getIdModalidad();

%>

</head>
<body style="background-color: #07393b; height: auto">
<div class="d-flex flex-column bd-highlight mb-3">
	<div class="bg-light"
		style="margin-top: 20px; padding: 15px; border-radius: 15px">
		<%
		if (request.getAttribute("msg") != null) {
		%>
		<div class="alert alert-success" role="alert">
			<span><i class="fa fa-check-circle"></i></span> ${requestScope.msg}
		</div>
		<%
		}
		%>
		<div class="pl-4 align-middle">
			<a class="btn btn-secondary pull-right" href="<%=urlVolver%>">Volver</a> 
			<h1 class="h4 text-center"><span><i
					class="fa fa-folder-open"></i></span> Modificar materias</h1>
		</div>
		</div>
		<div class="bg-light"
		style="margin-top: 20px; padding: 30px; border-radius: 15px">
			<form action="Materias" method="post">
			<div class="form-row">
				<div class="align-middle">
					<h1 class="text-secondary pb-4 pt-3 px-4" style="font-size: 1.5em"><span><i
					class="fa fa-plus"></i></span>
					Nueva materia</h1>
				</div>
				<% if(request.getAttribute("validaciones") != null) { %>
				<div class="alert-danger alert alert-dismissable align-middle">
					${requestScope.validaciones}</div>
				<% } %>
			</div>
			<div class="form-row">
			<div class="col-md-6 mb-3">
				<input type="hidden" value="<%=curso!=null?curso.getIdCurso():""%>" id="curso" name="curso">
				<input type="hidden" value="<%=curso!=null?curso.getIdModalidad():""%>" id="mod" name="mod">
				<label for="materia">Nombre materia</label> <input type="text"
						value="" class="form-control" id="materia"
						name="materia" required placeholder="Nombre materia">
				</div>
				<div class="col-md-6 mb-3">
			<button class="btn btn-lg btn-primary btn-block mt-4 ml-3 mr-4"
					type="submit"
					style="width: 190px; height: 48px; background-color: #07393b">
					<span><i class="fa fa-plus"></i></span> Agregar
				</button>
				</div>
			</div>
		</form>
		</div>
		<% if (curso!=null) { %>
		<div class="bg-light" style="margin-top: 20px; padding: 30px; border-radius: 15px"> 
		<form action="AgregarAsignaturas" method="post" id="cursoModalidad" name="cursoModalidad">
			<div class="form-row">
				<div class="align-middle">
					<h1 class="text-secondary pt-3 px-4" style="font-size: 1.5em">
					Materias</h1>
					<h1 class="h3 text-secondary pb-2 px-4" style="font-size: 1.1em">
					<%=curso.getNombre() %> año - <%=curso.getModalidad()%></h1>
				</div>
				<% if(request.getAttribute("validaciones2") != null) {%>
				<div class="alert-danger alert alert-dismissable align-middle">
					${requestScope.validaciones2}</div>
				<% } %>
			</div>
			<input type="hidden" value="<%=curso.getIdCurso()%>" id="curso" name="curso">
			<input type="hidden" value="<%=curso.getIdModalidad()%>" id="mod" name="mod">
			<% for (Materia materia : materias) { %>
            <input type="checkbox" name="materia" value="<%= materia.getIdMateria() %>" <%=materiasCurso.contains(materia)?"checked":""%>>
            	<%= materia.getNombre() %><br>
        	<% } %>
			<div class="mb-3 mt-4">
				<button class="btn btn-lg btn-primary btn-block"
					type="submit"
					style="width: 190px; height: 48px; background-color: #07393b">
					<span><i class="fa fa-plus"></i></span> Confirmar
				</button>
			</div>
		</form>
		</div>
		<% } %>
		</div>
</body>
</html>