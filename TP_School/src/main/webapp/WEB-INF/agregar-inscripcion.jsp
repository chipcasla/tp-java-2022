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
<link href="<%= request.getContextPath()%>/style/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<%= request.getContextPath()%>/style/signin.css" rel="stylesheet">
<link href="<%= request.getContextPath()%>/style/fechas.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<% 

Alumno a = request.getAttribute("mi-alumno")==null?(Alumno) session.getAttribute("user"):(Alumno)request.getAttribute("mi-alumno");

@SuppressWarnings("unchecked")
LinkedList<Curso> lc = (LinkedList<Curso>) request.getAttribute("disponibles");
@SuppressWarnings("unchecked")
LinkedList<LinkedList<String>> lm = (LinkedList<LinkedList<String>>) request.getAttribute("mods");

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
		style="margin: 10px; padding: 30px; border-radius: 15px">
		<form action="AgregarInscripcion" method="post">
			<div class="form-row">
				<div>
					<span style="color: #6c757d"><i
						class="fa fa-folder-plus fa-2x pb-4 pt-3 pl-3"></i></span>
				</div>
				<div class="align-middle">
					<h1 class="text-secondary pb-4 pt-3 px-4" style="font-size: 1.5em">Nueva
						Inscripcion</h1>
				</div>
				<% if(request.getAttribute("validaciones") != null) {%>
				<div class="alert-danger alert alert-dismissable align-middle">
					${requestScope.validaciones}</div>
				<% } %>
			</div>
			<div class="form-row">
				<input type="hidden" id="alumno" name="id-alumno" value="<%=a.getIdAlumno()%>">
				<% if(request.getAttribute("mi-alumno")!=null) { %>
				<input type="hidden" id="user" name="user" value="admin">
				<% } %>
				<div class="col-md-5 mb-3">
					<label for="validationDefault01">Alumno</label> <input type="text"
						class="form-control-plaintext" id="validationDefault01" name="Alumno"
						required placeholder="Alumno"
						value="<%=a.getApellido()%>, <%=a.getNombre()%>"
						disabled readonly>
				</div>
			</div>
			<div class="form-row">
				<div class="col-md-5 mb-3 mx-2">
					<label for="cur">Curso</label> <select id="cur" name="cur" required
						class="form-select"  onchange="cargaModalidad()" aria-label="Select course">
						<option value="" disabled selected>Seleccione curso...</option>
						<% for(Curso cur : lc) { %>
						<option value="<%=cur.getIdCurso()%>"><%=cur.getNombre()%>
							año
						</option>
						<% } %>
					</select>
				</div>
				<div class="col-md-6 mb-3">
					<label for="mod">Modalidad</label> <select id="mod" name="mod"
						required class="form-select" aria-label="Select mod">
						<option value="" disabled selected>Seleccione
							modalidad...</option>

					</select>
				</div>
			</div>
			<div class="row align-bottom">
				<button class="btn btn-lg btn-primary btn-block mt-4 ml-3 mr-4"
					type="submit"
					style="width: 190px; height: 48px; background-color: #07393b">
					<span><i class="fa fa-plus"></i></span> Aceptar
				</button>
				<% if(request.getAttribute("mi-alumno")==null) { %>
				<a class="btn btn-lg btn-secondary btn-block mt-4"
					href="Alumno?opcion=inscripciones" style="width: 160px; height: 48px">Volver</a>
				<% } %>
			</div>
		</form>
	</div>
	<script>
			function cargaModalidad() {
				var curso = document.getElementById('cur');
				var cur = curso.value; //tomamos el valor seleccionado
                console.log("cur:", cur);// lo mostramos en el log
                var tipo = document.getElementById('user');
                if(tipo == null) {
                	var servlet = "CursoServlet";
                } else {
                	if(tipo.value == 'admin') {            		
	                	var servlet = "../CursoServlet";
                	}
                }
                //... y llamamos a nuestro servlet
                $.getJSON(servlet, //.. este es el servlet
                        {cur: cur}, //.. le pasamos el argumento
                        function (data, textStatus, jqXHR) {
                            //y construimos el combo
                            $("#mod").empty(); //limpiamos lo que hay
                            $("<option/>").attr("value", "").attr("disabled", "").attr("selected", "").text("Seleccione modalidad...").appendTo("#mod");
                            $.each(data, function (index, item) {
                                $("<option/>")
                                	.attr("value", item.modId)
                                	.text(item.modName)
                                	.appendTo("#mod");

                            });
                        });
            };
            
        </script>
<% } %>
</body>
</html>