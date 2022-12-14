<%@ page import="entities.Alumno" %>
<%@ page import="java.time.LocalDate" %>

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
	
    <% Alumno a = (Alumno) session.getAttribute("user");%>
</head>
<body style="background-color: #07393b">
<div class="bg-light" style="margin: 10px;padding:20px; border-radius: 15px">
<form action="update" method="post" style="padding:15px">
  <div class="form-row">
  <div>
  	<span style="color: #6c757d"><i class="fa fa-user-edit fa-2x pb-4 pt-3 pl-3"></i></span>
  </div>
  <div class="align-middle">
  	<h1 class="text-secondary pb-4 pt-4 px-4" style="font-size: 1.5em">Formulario - Editar mis datos</h1>
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
  </div>
  <div class="form-row">
    <div class="col-md-4 mb-3">
      <label for="validationDefault01">Nombre</label>
      <input type="text" class="form-control" id="validationDefault01" name="first-name" required placeholder="Nombre" value="<%=request.getAttribute("nom")==null?a.getNombre():request.getAttribute("nom")%>" required>
    </div>
    <div class="col-md-3 mb-3">
      <label for="validationDefault02">Apellido</label>
      <input type="text" class="form-control" id="validationDefault02" name="last-name" required placeholder="Apellido" value="<%=a.getApellido()%>" required>
    </div>
    <div class="col-md-5 mb-3">
      <label for="validationDefaultMail">Correo electrónico</label>
      <div class="input-group">
        <div class="input-group-prepend">
          <span class="input-group-text" id="inputGroupPrepend2">@</span>
        </div>
        <input type="email" class="form-control" id="validationDefaultMail" name="email" required placeholder="Correo" value="<%=a.getMail()%>" required>
      </div>
    </div>
  </div>
  <div class="form-row">
  <div class="col-md-2 mb-3">
    <label for="disabledLegajo">Legajo</label>
    <input class="form-control" id="disabledLegajo" type="text" required value="<%=a.getLegajo()%>" disabled>
  </div>
    <div class="col-md-4 mb-3">
      <label for="validationDefault03">Nro. Documento</label>
      <input type="text" inputmode="numeric" pattern="[0-9]{8}" oninvalid="setCustomValidity('Por favor, ingrese sus 8 dígitos de DNI')" onchange="try{setCustomValidity('')}catch(e){}"
      class="form-control" id="validationDefault03" name="dni" required placeholder="DNI" value="<%=a.getDni()%>" required>
    </div>
    <div class="col-md-3 mb-3">
      <label for="validationDefault04">Teléfono</label>
      <input type="tel" inputmode="numeric" pattern="[0-9]{10}" oninvalid="setCustomValidity('Por favor, ingrese telefono con 10 dígitos  (sin prefijo 15 ni +549)')" onchange="try{setCustomValidity('')}catch(e){}"
      class="form-control" id="validationDefault04" name="tel" required placeholder="Teléfono" value="<%=a.getTel()%>" required>
    </div>
    <div class="col-md-3 mb-3 form-date">
			<label class="form-date__label" for="input-date">Fecha de nacimiento</label>
			<input class="form-control form-date__input" type="date" id="input-date" name="date-birth" required value="<%=a.getFechaNac()%>" />
    </div>
  </div>
  <div class="form-row">
  	<a href="#"><span class="link mb-4 m-2">Cambiar contraseña</span></a>
  </div>
  <div class="row align-bottom">
  <button class="btn btn-lg btn-primary btn-block mt-4 mx-3" type="submit" style="border-color: #202f81; background-color: #07393b;width: 170px; height: 48px">Confirmar</button>
  <button class="btn btn-lg btn-secondary btn-block mt-4 mx-3" type="reset" style="width: 170px; height: 48px">Restaurar</button>
  <a class="btn btn-lg btn-secondary btn-block mt-4 mx-3" href="signin?option=main" style="width: 170px; height: 48px">Volver</a>
  </div>
</form>
</div>
</body>
</html>