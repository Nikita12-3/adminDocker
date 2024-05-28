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
<h4>Список заказов</h4>
<#if orders??>
    <table>
        <tr>
            <th>Дата заказа</th>
            <th>Пользователь</th>
        </tr>
        <tr>
            <#list orders as order>
                <div>
                    <th>${order.dateOfCreated}</th>
                    <th>${order.user.name}</th>
                </div>
                <tr></tr>
            </#list>
        </tr>
    </table>
    <#else>
    <h3>Вы не оформили еще ни одного заказа</h3>
</#if>
 <br><a href="/">Вернуться</a><br>
</body>
</html>