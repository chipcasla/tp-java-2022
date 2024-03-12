<%@page import="entities.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<%
Persona p = (Persona) session.getAttribute("user");
String urlVolver = "index.jsp";
if(p != null) {
	if(p.getTipo().equalsIgnoreCase("admin")) {
		urlVolver = request.getContextPath()+ "/Admin/controllerAdmin?option=main";
	} else if(p.getTipo().equalsIgnoreCase("alumno")) {
		urlVolver = "Alumno?opcion=main";
	}
}
%>
</head>
<body>
<h1>Error</h1>
<p>Ha ocurrido un error: </p>
<p>${requestScope.error}</p>

<a href=<%= urlVolver %>>Ir a Home</a>
</body>
</html>