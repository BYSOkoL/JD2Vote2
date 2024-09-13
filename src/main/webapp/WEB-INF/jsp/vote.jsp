<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Голосование</title>
    <style>
        body {
            background-color: #f0f0f0;
            font-family: Arial, sans-serif;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
            position: relative;
        }

        h2 {
            color: #4CAF50;
            text-align: center;
        }

        h4 {
            color: #333;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"] {
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

        .error-messages {
            color: #FF0000;
            margin-top: 20px;
        }

        .error-messages span {
            display: block;
        }

        .admin-links {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .admin-links a {
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }

        .admin-links a:hover {
            color: #45a049;
        }

        .link-left {
            position: absolute;
            left: 20px;
            top: 20px;
        }

        .link-right {
            position: absolute;
            right: 20px;
            top: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="admin-links">
        <a href="${pageContext.request.contextPath}/admin/artist" class="link-left">Управление артистами</a>
        <a href="${pageContext.request.contextPath}/admin/genre" class="link-right">Управление Жанрами</a>
    </div>

    <h2>Голосование</h2>
    <form action="${pageContext.request.contextPath}/vote" method="post">
        <div>
            <h4>Выберите исполнителя</h4>
            <c:forEach var="singer" items="${requestScope.singers}">
                <label>
                    <input type="radio" name="singer" value="${singer.key}">${singer.value}
                </label>
            </c:forEach>
        </div>

        <div>
            <h4>Выберите жанр</h4>
            <c:forEach var="genre" items="${requestScope.genres}">
                <label>
                    <input type="checkbox" name="genre" value="${genre.key}">${genre.value}
                </label>
            </c:forEach>
        </div>

        <div>
            <label for="infoID">
                <h4>Напишите краткое сообщение</h4>
                <input type="text" name="info" id="infoID" required>
            </label>
        </div>

        <button type="submit">Отправить</button>

        <c:if test="${not empty requestScope.voteErrors}">
            <div class="error-messages">
                <c:forEach var="error" items="${requestScope.voteErrors}">
                    <span>${sessionScope.lang != null ? (sessionScope.lang == 'ru_RU' ? error.rusDescription : error.description) : error.rusDescription}</span>
                </c:forEach>
            </div>
        </c:if>
    </form>
</div>

</body>
</html>
