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
            <div>
                <c:if test="${error != null}">
                    <div class="alert alert-info" role="alert">
                            ${error}
                    </div>
                </c:if>
                Do you want to authorise the application "${app.name}" to view your details?
                <form:form method="POST">
                    <input name="accept" type="submit" value="yes" class="btn btn-primary btn-block"/>
                    <input name="accept" type="submit" value="no" class="btn btn-warn btn-block"/>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>
