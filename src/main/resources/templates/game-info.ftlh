<!DOCTYPE html>
<html>
<head>
    <title>SteamClone</title>
</head>
<body>
<h1>SteamClone</h1><hr>
<h4>Подробная информация о игру</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"/><br><br>
</#list>
<b>Название игры: </b>${game.title}<br>
<b>Описание игры: </b>${game.description}<br>
<b>Цена: </b>${game.price}<br>
<a href="/user/${game.user.id}">${game.user.name}</a><br>
</body>
</html>