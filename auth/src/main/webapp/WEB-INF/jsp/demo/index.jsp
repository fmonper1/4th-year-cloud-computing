<%--
  Created by IntelliJ IDEA.
  User: euan
  Date: 10/03/19
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Demo</title>
</head>
<body>
    <table>
        <thead>
            <tr><th>Id</th><th>SomeField</th></tr>
        </thead>
        <tbody>
            <c:forEach items="${demoEntities}" var="demoEntity">
                <tr><td>${demoEntity.id}</td><td>${demoEntity.someField}</td></tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
