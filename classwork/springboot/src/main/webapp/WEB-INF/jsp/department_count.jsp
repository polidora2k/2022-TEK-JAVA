<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <table class="table table-striped table-dark" border="1">
        <thead>
          <tr>
            <th scope="col">Name</th>
            <th scope="col">Count</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${departmentCounts}" var="deptCnt">
            <tr>
              <td>${deptCnt.name}</td>
              <td>${deptCnt.cnt}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
</body>
</html>