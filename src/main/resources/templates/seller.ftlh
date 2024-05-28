<!DOCTYPE html>
<html>
<head>
    <title>SteamClone</title>
</head>
<body>
<h1>Личный кабинет продавца</h1><hr>
<a href="/seller-stat">Общая статистика продаж</a><br><br>
<a href="/seller-addGame">Добавить новую игру</a><br><br>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Выйти"/>
</form>
<hr>
<h4>Список моих игр</h4>
<form action="/seller" method="get">
    Поиск по названию: <input type="text" name="title">
    <input type="submit" value="Поиск"/>
</form>
<br>
<#if games??>
    <table>
        <tr>
            <th>Название</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>Рейтинг</th>
            <th>Выставлен на продажу</th>
            <th>Об игре</th>
            <th>Статус игры</th>
            <th>Редактировать игру</th>
        </tr>
        <tr>
            <#list games as game>
                <div>
                    <th>${game.title}</th>
                    <th>${game.description}</th>
                    <th>${game.price}</th>
                    <th>${game.rating}</th>
                    <th><#if game.active>Продается<#else>Снят с продажи</#if></th>
                    <th><a href="/game/${game.id}">Подробнее...</a></th>
                    <th>
                        <form action="/game/delete/${game.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="submit" <#if game.active>value = "Снять" <#else>value = "Выставить"</#if>"/>
                        </form>
                    </th>
                    <th><a href="/seller-editGame/${game.id}">Редактировать</a></th>
                </div>
                <tr></tr>
            </#list>
        </tr>
    </table>
    <#else>
    <h3>Игры пока не добавлены</h3>
</#if>
<table>
</body>
</html>