<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
</head>
<body>
    <h1>Index Page ${name}</h1>

    <form method="get" action="/search">
        Course Name: <input type="text" name="course" value="${course}">
        Instructor Name: <input type="text" name="faculty" id="${faculty}">
        <button type="submit">Search</button>
    </form>

    <table border="1" cellpadding="5">
        <tr>
            <td><B>ID</B></td>
            <td><b>Name</b></td>
        </tr>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.id}</td>
                <td>${course.name}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>