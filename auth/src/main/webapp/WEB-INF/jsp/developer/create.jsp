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
            <div class="col-4">
                <form:form method="POST" modelAttribute="createApplicationForm">
                    <form:errors cssClass="alert alert-danger" element="div"/>
                    <my:bs-input path="applicationName" placeholder="Application Name" label="Application Name" type="text"/>
                    <my:bs-input path="redirectUri" placeholder="Redirect Uri" label="Redirect Uri" type="text"/>
                    <my:bs-input path="databasePassword" placeholder="Database Password" label="Database Password" type="password"/>
                    <my:bs-input path="confirmDatabasePassword" placeholder="Confirm Database Password" label="Confirm Database Password" type="password"/>
                    <input type="submit" value="Create Application" class="btn btn-primary btn-block"/>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>
