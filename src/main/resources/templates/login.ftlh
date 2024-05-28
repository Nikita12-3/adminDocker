
<!DOCTYPE html>
<html>
<head>
    <title>SteamClone</title>
</head>
<body>
<h1>SteamClone</h1><hr>
<h4>Авторизация</h4>
<form action="/login/process" method="post">
    Эл. почта: <input type="email" name="username"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вход"/>
</form><br>
<a href="/registration">Зарегистрироваться</a><br><br>
<a href="/">Войти как гость</a>
</body>
</html>