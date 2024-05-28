<!DOCTYPE html>
<html>
<head>
    <title>SteamClone</title>
</head>
<body>
<h1>Личный кабинет продавца</h1><hr>
<h3>Редактирование игры</h3>
<form action="/game/edit/${game.id}" method="post" enctype="multipart/form-data">
    Название игры: <input type="text" name="title" value = '${game.title}'/><br><br>
    Описание игры: <input type="text" name="description" value = '${game.description}'/><br><br>
    Цена: <input type="number" name="price" value = "${game.price}"/><br><br>
    Первая фотография: <input type="file" name="file1"/><br><br>
    Вторая фотография: <input type="file" name="file2"/><br><br>
    Третья фотография: <input type="file" name="file3"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Сохранить изменения"/>
</form>
</body>
</html>