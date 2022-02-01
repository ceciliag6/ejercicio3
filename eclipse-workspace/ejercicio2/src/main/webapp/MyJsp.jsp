<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bienvenido</title>
</head>
<body>
	<h1>Mi Formulario</h1>
	<h2>Metodo GET</h2>
	<form action="myservlet" method="get">
	  <label for="username">First name:</label><br>
	  <input type="text" id="username" name="username"><br>
	  <label for="password">Last name:</label><br>
	  <input type="text" id="password" name="password"><br><br>
	  <input type="submit" value="Submit">
	</form> 
	<h2>Metodo POST</h2>
	<form action="myservlet" method="post">
	  <label for="username">First name:</label><br>
	  <input type="text" id="username" name="username"><br>
	  <label for="password">Last name:</label><br>
	  <input type="text" id="password" name="password"><br><br>
	  <input type="submit" value="Submit">
	</form> 	
</body>
</html>