<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавление исполнителей</title>
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


    <h4>Существующие исполнители:</h4>
    <c:forEach var="singer" items="${requestScope.artist}">
        <span>${singer.value}</span>
    </c:forEach>

    <form action="${pageContext.request.contextPath}/admin/artist" method="post">
        <h4>Добавить нового исполнителя:</h4>

        <label for="artistID">
            <input type="text" name="artist" id="artistID" size="80" required>
        </label>
        <button type="submit">Отправить</button>

        <c:if test="${not empty requestScope.artistErr}">
            <div class="error-message">
                <span>${requestScope.artistErr}</span>
            </div>
        </c:if>
        <c:if test="${not empty requestScope.artistAdd}">
            <div class="success-message">
                <span>${requestScope.artistAdd}</span>
            </div>
        </c:if>
    </form>

    <h3>Удалить исполнителя:</h3>
    <form action="${pageContext.request.contextPath}/admin/artist" method="post">
        <h4>Выберите исполнителя для удаления:</h4>

        <select name="deleteArtist" id="delete">
            <c:forEach var="artist" items="${requestScope.artist}">
                <option value="${artist.key}">${artist.value}</option>
            </c:forEach>
        </select>
        <button type="submit">Удалить</button>

        <c:if test="${not empty requestScope.deleteArtistErr}">
            <div class="error-message">
                <span>${requestScope.deleteArtistErr}</span>
            </div>
        </c:if>
        <c:if test="${not empty requestScope.deleteArtist}">
            <div class="success-message">
                <span>${requestScope.deleteArtist}</span>
            </div>
        </c:if>
    </form>
</div>

</body>
</html>
