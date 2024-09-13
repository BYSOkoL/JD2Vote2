<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Результаты голосования</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 40px;
            border-radius: 10px;
        }
        h1, h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .footer {
            text-align: center;
            padding: 10px;
            background-color: #333;
            color: #fff;
            position: fixed;
            width: 100%;
            bottom: 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Результаты голосования</h1>

        <h2>Количество голосов по жанрам</h2>
        <table>
            <thead>
                <tr>
                    <th>Название жанра</th>
                    <th>Количество голосов</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="genreVote" items="${genreVoteCounts}">
                    <tr>
                        <td>${genreVote.genreName}</td>
                        <td>${genreVote.voteCount}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h2>Количество голосов по артистам</h2>
        <table>
            <thead>
                <tr>
                    <th>Название артиста</th>
                    <th>Количество голосов</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="artistVote" items="${artistVoteCounts}">
                    <tr>
                        <td>${artistVote.artistName}</td>
                        <td>${artistVote.voteCount}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h2>Сообщения и время</h2>
        <table>
            <thead>
                <tr>
                    <th>Сообщение</th>
                    <th>Время</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="voteMessage" items="${voteMessages}">
                    <tr>
                        <td>${voteMessage.message}</td>
                        <td>${voteMessage.timestamp}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>