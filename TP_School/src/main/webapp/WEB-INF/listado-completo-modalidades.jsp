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

<%
@SuppressWarnings("unchecked")
LinkedList<Curso> lm = (LinkedList<Curso>) request.getAttribute("modalidades");
%>

</head>
<body style="background-color: #07393b; height: auto">
	<div class="bg-light"
		style="margin-top: -20px; padding: 30px; border-radius: 15px">
		<%
		if (request.getAttribute("msg") != null) {
		%>
		<div class="alert alert-success" role="alert">
			<span><i class="fa fa-check-circle"></i></span> ${requestScope.msg}
		</div>
		<%
		}
		%>
		<% if(request.getAttribute("validaciones") != null) {%>
		<div class="alert-danger alert alert-dismissable align-middle">
			${requestScope.validaciones}</div>
		<% } %>
		<h1 class="h4 text-center pb-2">Modalidades</h1>
		<div class="flex pl-4">
			<a class="btn btn-secondary pull-right mb-2" href="<%= request.getContextPath()%>/Admin/controllerAdmin?option=main">Volver</a>
			<a class="btn btn-primary pull-right mr-3 mb-2"
				href="<%= request.getContextPath()%>/Admin/controllerAdmin?option=cursos"><span><i
					class="fa fa-eye"></i></span> Todos los cursos</a> 
			<a class="btn btn-primary pull-right mr-3 mb-2"
				href="<%= request.getContextPath()%>/Admin/controllerAdmin?option=agregar-modalidad"><span><i
					class="fa fa-plus"></i></span> Añadir modalidad</a>
		</div>
		<div class="mt-5">
			<table class="table table-hover align-middle m-2">
				<thead class="table-dark">
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Descripción</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Curso c : lm) {
					%>
					<tr>
						<th scope="row"><%=c.getIdModalidad()%></th>
						<td><%=c.getModalidad()%></td>
						<td><form action="<%= request.getContextPath()%>/Admin/EditarModalidad" method="get">
	            			<input type="hidden" name="id" value="<%=c.getIdModalidad()%>" >
	            			<button type="submit" style="margin-bottom: 0; - -bs-btn-padding-y: .25rem; - -bs-btn-padding-x: .5rem; - -bs-btn-font-size: .75rem;"
	            			 class="btn btn-sm btn-outline-info">Editar</button>
	            		</form>
						</td>
						<td><form action="<%= request.getContextPath()%>/Admin/EliminarModalidad" method="post">
	            			<input type="hidden" name="modalidad" value="<%=c.getIdModalidad()%>" >
	            			<button type="submit" style="margin-bottom: 0; - -bs-btn-padding-y: .25rem; - -bs-btn-padding-x: .5rem; - -bs-btn-font-size: .75rem;"
	            			 class="btn btn-sm btn-outline-danger">Eliminar</button>
	            		</form>
						</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>