<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
<h3>Update a listing</h3>
<form:form method="POST"
           action="./update" modelAttribute="listing">
    <table>
        <tr>
            <td><form:label path="id">id</form:label></td>
            <td><form:input path="id" readOnly="true"/></td>
        </tr>
        <tr>
            <td><form:label path="title">Title</form:label></td>
            <td><form:input path="title"/></td>
        </tr>
        <tr>
            <td><form:label path="description">Description</form:label></td>
            <td><form:input path="description"/></td>
        </tr>
        <tr>
            <td><form:label path="moduleCode">Module Code</form:label></td>
            <td><form:input path="moduleCode"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>