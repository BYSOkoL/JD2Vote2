<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавление жанров</title>
    <style>
        body {
            background-color: #D3D3D3;
            font-family: Arial, sans-serif;
            color: #333;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h4, h3 {
            color: #4CAF50;
        }

        label, span {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"], select {
            width: calc(100% - 20px);
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            display: block;
            margin: 20px auto 0;
        }

        button:hover {
            background-color: #45a049;
        }

        .error-message {
            color: #FF0000;
            margin-top: 20px;
        }

        .success-message {
            color: #228B22;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="container">


    <h4>Существующие жанры:</h4>
    <c:forEach var="janre" items="${requestScope.janres}">
        <span>${janre.value}</span>
    </c:forEach>

    <form action="${pageContext.request.contextPath}/service/genre" method="post">
        <h4>Добавить новый жанр:</h4>

        <label for="janreID">
            <input type="text" name="janre" id="janreID" size="80" required>
        </label>
        <button type="submit">Отправить</button>

        <c:if test="${not empty requestScope.janreErr}">
            <div class="error-message">
                <span>${requestScope.janreErr}</span>
            </div>
        </c:if>
        <c:if test="${not empty requestScope.janreAdd}">
            <div class="success-message">
                <span>${requestScope.janreAdd}</span>
            </div>
        </c:if>
    </form>

    <h3>Удалить жанр:</h3>
    <form action="${pageContext.request.contextPath}/service/genre" method="post">
        <h4>Выберите жанр для удаления:</h4>

        <select name="deleteGenre" id="delete">
            <c:forEach var="janre" items="${requestScope.janres}">
                <option value="${janre.key}">${janre.value}</option>
            </c:forEach>
        </select>
        <button type="submit">Удалить</button>

        <c:if test="${not empty requestScope.deleteGenreErr}">
            <div class="error-message">
                <span>${requestScope.deleteGenreErr}</span>
            </div>
        </c:if>
        <c:if test="${not empty requestScope.deleteGenre}">
            <div class="success-message">
                <span>${requestScope.deleteGenre}</span>
            </div>
        </c:if>
    </form>
</div>

</body>
</html>
