<!DOCTYPE html>
<html>
<head>
    <title>SteamClone</title>
</head>
<body>
<h1>SteamClone</h1><hr>
<h3>Имя пользователя: <i>${user.name}</i></h3>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Выйти"/>
</form>
<hr>
<h4>Список игр</h4>
<#if games??>
    <table>
        <tr>
            <th>Название</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>Рейтинг</th>
            <th>Перейти к игре</th>
        </tr>
        <tr>
            <#list games as game>
                <div>
                    <th>${game.title}</th>
                    <th>${game.description}</th>
                    <th>${game.price}</th>
                    <th>${game.rating}</th>
                    <th><a href="/game/${game.id}">Подробнее...</a></th>
                </div>
                <tr></tr>
            </#list>
        </tr>
    </table>
    <#else>
    <h3>Вы не добавили еще ничего в корзину</h3>
</#if>
<form action="/order/create" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" value="Оформить заказ"/>
</form><br>
</body>
</html>