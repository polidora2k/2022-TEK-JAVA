<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Student</title>
</head>
<body>
    <h1>Add A New Student</h1>

    <form method="get" action="/studentSubmit">
        Student First Name: <input type="text" name="studentFirstName" id="studentFirstName">
        Student Last Name: <input type="text" name="studentLastName" id="studentLastName">
        <br>
        City: <input type="text" name="city" id="city">
        State: <input type="text" name="state" id="state">
        Zip Code: <input type="text" name="zipcode" id="zipcode">
        <br>
        Major Id: <input type="text" name="majorId" id="majorId">
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