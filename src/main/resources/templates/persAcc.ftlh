<!DOCTYPE html>
<html>
<head>
    <title>SteamClone</title>
</head>
<body>
<h1>SteamClone</h1><hr>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Выйти"/>
</form>
<br><br>
<form action="/user/editBal/${user.id}" method="post">
    <input type="number" name="balance"><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Пополнить баланс"/>
</form>
<br><br>

<h3>Имя пользователя: <i>${user.name}</i></h3>
<h3>Почта: <i>${user.email}</i></h3>
<h3>Баланс: <i>${user.balance}</i></h3>
<h3>Имя пользователя: <i>${user.name}</i></h3>

<br><a href="/">Вернуться к списку игр</a><br>
</body>
</html>