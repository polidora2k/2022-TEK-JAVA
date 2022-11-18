<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Course</title>
</head>
<body>
    <h1>Index Page ${name}</h1>

    <form method="get" action="/courseSubmit">
        Course Name: <input type="text" name="courseName" id="courseName">
        Department Id: <input type="text" name="departmentId" id="departmentId">
        <button type="submit">Search</button>
    </form>
    <c:forEach items="${messages}" var="message">
        <c:if test="${success}">
            <p style="color: green;">${message}</p>
        </c:if>
        <c:if test="${!success}">
            <p style="color: red;">${message}</p>
        </c:if>
    </c:forEach>
        
</html>