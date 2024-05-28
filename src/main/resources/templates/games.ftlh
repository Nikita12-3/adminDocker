<!DOCTYPE html>
<html>
<head>
    <title>SteamClone</title>
</head>
<body>
<h1>SteamClone</h1><hr>
<#if user.email??>
    <h3>Имя пользователя: <i>${user.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
    <#if user.isAdmin()>
        <br><a href="/admin">Панель администратора</a><br>
    </#if>
    <#if user.isSeller()>
        <br><a href="/seller">Панель продавца</a><br>
    </#if>
     <br><a href="/orders">Перейти к заказам</a><br>
     <br><a href="/persAcc">Перейти в личный кабинет</a><br>
<#else>
    <a href="/login">Войти</a></h1>
</#if>
<hr>
<h4>Список игр</h4>
<form action="/" method="get">
    Поиск по названию: <input type="text" name="title">
    <input type="submit" value="Поиск"/>
</form>
<#if games??>
    <table>
        <tr>
            <th>Название</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>Рейтинг</th>
            <th>Перейти к игре</th>
            <th>Добавить в корзину</th>
        </tr>
        <tr>
            <#list games as game>
                <div>
                    <th>${game.title}</th>
                    <th>${game.description}</th>
                    <th>${game.price}</th>
                    <th>${game.rating}</th>
                    <th><a href="/game/${game.id}">Подробнее...</a></th>
                    <th>
                        <form action="/game/addGame/${game.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="submit" value="Купить"/>
                        </form>
                    </th>
                </div>
                <tr></tr>
            </#list>
        </tr>
    </table>
    <#else>
    <h3>Игры пока не добавлены</h3>
</#if>
 <br><a href="/bucket">Перейти в корзину(${bucket.getSize()})</a><br>
</body>
</html>