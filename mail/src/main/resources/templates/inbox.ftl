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

</head>

<body>
<form th:action="@{/inbox}" method="post" >
	<div><label> Login : <input type="text" name="userName"/> </label></div>
	<div><label> Password : <input type="text" name="password"/> </label></div>
	<input type="submit" onclick="addContact()" value="Send">
</form>
</body>
<html/>