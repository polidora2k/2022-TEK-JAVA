<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create User</title>

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
      crossorigin="anonymous"
    ></script>
</head>
<body>
  <div class="container">
    <div class="row mt-3 mb-1">
      <h2>Create User</h2>
    </div>
  
      <form action="/user/createuser" method="POST">
          <div class="mb-3">
              <label for="exampleInputEmail1" class="form-label">Email address</label>
              <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${form.email}">
              <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
          </div>
          
          <div class="row">
            <div class="mb-3 col-6">
                <label for="exampleInputPassword1" class="form-label">Password</label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" value="${form.password}" >
            </div>

            <div class="mb-3 col-6">
                <label for="confirmPassword" class="form-label">Confirm Password</label>
                <input type="password" name="confirmPassword" class="form-control" id="confirmPassword">
            </div>
        </div>
  
          <div class="row">
              <div class="mb-3 col-6">
                  <label for="firstName" class="form-label">First Name</label>
                  <input type="text" name="firstName" class="form-control" id="firstName" aria-describedby="firstNameHelp" value="${form.firstName}">
                  <div id="fristNameHelp" class="form-text">Please give us your first name</div>
              </div>
  
              <div class="mb-3 col-6">
                  <label for="lastName" class="form-label">Last Name</label>
                  <input type="text" name="lastName" class="form-control" id="lastName" aria-describedby="lastNameHelp" value="${form.lastName}">
                  <div id="lastNameHelp" class="form-text">Please give us your last name</div>
              </div>
          </div>
  
          <div class="mb-3">
              <label for="address" class="form-label">Address</label>
              <input type="text" name="address" class="form-control" id="address" aria-describedby="addressHelp" value="${form.address}">
              <div id="addressHelp" class="form-text">We want to send you physical advertisements every day</div>
          </div>
  
          <div class="row">
              <div class="mb-3 col-4">
                  <label for="city" class="form-label">City</label>
                  <input type="text" name="city" class="form-control" id="city" aria-describedby="cityHelp" value="${form.city}">
                  <div id="cityHelp" class="form-text"></div>
              </div>
  
              <div class="mb-3 col-4">
                  <label for="state" class="form-label">State</label>
                  <input type="text" name="state" class="form-control" id="state" aria-describedby="stateHelp" value="${form.state}">
                  <div id="stateHelp" class="form-text"></div>
              </div>
  
              <div class="mb-3 col-4">
                  <label for="zip" class="form-label">Zip</label>
                  <input type="text" name="zip" class="form-control" id="zip" aria-describedby="zipHelp" value="${form.zip}">
                  <div id="zipHelp" class="form-text"></div>
              </div>
          </div>
  
          <div class="mb-3">
              <label for="phone" class="form-label">Phone</label>
              <input type="tel" name="phone" class="form-control" id="phone" aria-describedby="phoneHelp" value="${form.phone}">
              <div id="phoneHelp" class="form-text">We will send you text messages even if you unsubscribe</div>
          </div>
  
          <button type="submit" class="btn btn-primary">Submit</button>
      </form>
      <c:forEach items="${errors}" var="error">
        <p style="color: red;">${error.defaultMessage}</p>
    </c:forEach>
    </div>
</body>
</html>