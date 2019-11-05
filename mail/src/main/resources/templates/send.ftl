<!DOCTYPE html>
<html lang="en"
	  xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns:form="http://www.w3.org/1999/html"
	  xmlns:th="http://www.w3.org/1999/xhtml" >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<!--[if lt IE 9]><script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script><![endif]-->
	<title></title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<script src="https://code.jquery.com/jquery-1.10.2.js"
			type="text/javascript"></script>
	<script src="js/validate.js"></script>
</head>

<body>

		<form th:action="@{/send}" method="post" >
			<div><label> From : <input type="text" id="to"/> </label></div>
			<div><label> Subject : <input type="text" id="subject"/> </label></div>
			<div><label> Body : <input type="text" id="body"/> </label></div>
			<input type="button" onclick="sendMessage()" value="Send">
		</form>
</body>
<html/>