
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Voting</title>
    <meta charset="utf-8">
</head>
<body>
<form action="http://localhost:8080/vote-app-1.0-SNAPSHOT/vote" method="POST">
    <p>Выбери артиста: </p>
        <c:forEach items="${singers}" var="item">
                <input type="radio" name="singer" value="<c:out value="${item}"/>"/><span>${item}</span></br>
            </c:forEach>
    </select>
    </br>
    <p>Styles: </p>
    <c:forEach items="${styles}" var="item">
        <input type="checkbox" name="style" value="<c:out value="${item}"/>"/><span>${item}</span></br>
    </c:forEach>
    </br>
    <p>Text:</p>
    <textarea name="message"></textarea>
    </br>
    <input type="submit" value="Send">
</form>
</body>
</html>