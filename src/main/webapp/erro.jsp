<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Erros gerados</title>
</head>
<body>

	<h1>Erro, entre em contato com a equipe do suporte do sistema</h1>
	
	<%
		out.print(request.getAttribute("msg"));
	
	%>

</body>
</html>