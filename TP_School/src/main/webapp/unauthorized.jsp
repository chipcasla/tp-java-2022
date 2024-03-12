<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="es">
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

<title>Unauthorized</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/4.0/examples/sign-in/">

<!-- Bootstrap core CSS -->
<link href="style/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="style/signin.css" rel="stylesheet">

</head>

<body class="d-flex justify-content-center text-center vh-100"
	style="background-color: #07393b">
	<div>
		<div class="bg-light p-5 align-items-center text-secondary"
			style="border-radius: 15px; width: 28rem; background-color: rgb(164, 209, 255)">
				<h1 class="h3 mt-1 mb-3 font-weight-normal">No Autorizado</h1>
				<%
				if (request.getAttribute("validaciones") != null) {
				%>
				<div class="alert-danger alert alert-dismissable">
					${requestScope.validaciones}</div>
				<%
				}
				%>
				
				<a class="w-100 btn btn-lg btn-primary btn-block" href="Logout"
					style="background-color: #07393b">
					<span><i class="fa fa-sign-in"></i></span> Ir a Login
				</a>
		</div>
		<footer class="footer">
			<p class="mt-3 text-muted">&copy; 2022</p>
		</footer>
	</div>
</body>
</html>