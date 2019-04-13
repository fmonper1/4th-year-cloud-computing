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
<%@ taglib prefix="my" uri="http://utils.cc19grp10.shef.ac.uk/tags"%>

<html>
<head>
    <title>Create Application</title>
    <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
    <div class="container h-100">
        <div class="row h-100 justify-content-center align-items-center">
            <div class="col-6">
                <c:choose>
                    <c:when test="${application != null}">
                        <h1>Your application:</h1>
                        <table class="table">
                            <tr>
                                <td>Name:</td><td>${application.name}</td>
                            </tr>
                            <tr>
                                <td>Client ID:</td><td>${application.clientId}</td>
                            </tr>
                            <tr>
                                <td>Client Secret:</td><td>${application.encodedClientSecret}</td>
                            </tr>
                            <tr>
                                <td>Redirect Uri:</td><td>${application.redirectUri}</td>
                            </tr>
                            <tr>
                                <td>Database username:</td><td>${application.dbUsername}</td>
                            </tr>
                            <tr>
                                <td>Database url:</td><td>${application.dbUrl}</td>
                            </tr>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <h1>You do not have an application:</h1>
                        <a href="/developer/create" class="btn btn-primary btn-block">Create an application</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>
