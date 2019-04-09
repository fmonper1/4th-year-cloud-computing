<%--
  Created by IntelliJ IDEA.
  User: euan
  Date: 10/03/19
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
    <div class="container h-100">
        <div class="row h-100 justify-content-center align-items-center">
            <div class="col-4">
                <c:if test="${error != null}">
                    <div class="alert alert-info" role="alert">
                    ${error}
                    </div>
                </c:if>
                <form:form method="POST" modelAttribute="loginForm">
                    <div class="form-group">
                        <form:label path="username">Username</form:label>
                        <form:input class="form-control" path="username" placeholder="Username"/>
                    </div>
                    <div class="form-group">
                        <form:label path="password">Password</form:label>
                        <form:input class="form-control" path="password" type="password" placeholder="Password"/>
                    </div>
                    <input type="submit" value="Login" class="btn btn-primary btn-block"/>
                    <a href="signup" class="btn btn-primary btn-block" data-keep-params>Sign up</a>
                </form:form>
            </div>
        </div>
    </div>
    <script>
        document.querySelector('a[data-keep-params]').addEventListener('click',function(e) {
            e.preventDefault();
            console.log(e);
            window.location.href = this.getAttribute('href') + window.location.search;
        });
    </script>
</body>
</html>
