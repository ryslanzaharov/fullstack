<!DOCTYPE html>
<html xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="css/style.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="js/room.js"></script>
</head>
<body>
<div align="center">
    Chats
    <div id="result"></div>
</div>
<form action="./logout" method="get">
    <button type="submit">Logout</button>
</form><div class="ajax">
    <legend>Rooms</legend>
    <#list rooms as room>
    <input type="button" id="${room.id}" value="${room.room_name}"/><br/>
    </#list>
    <br/>
    <div id="result4"></div>
</div>
</body>
</html>

