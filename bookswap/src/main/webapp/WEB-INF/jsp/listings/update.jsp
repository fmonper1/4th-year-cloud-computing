<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:genericpage>
    <jsp:attribute name="header">
      <h1>Welcome</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
        Add a new listing
    </jsp:attribute>
    <jsp:body>
        <div class="my-3 p-3 bg-white rounded shadow-sm">
            <h3>Update a listing</h3>
            <c:url var="search_url" value="update"/>
            <form:form method="POST"
                       action="${search_url}" modelAttribute="listing">
                <div class="form-group">

                    <form:label path="id">id</form:label>
                    <form:input path="id" class="form-control" readOnly="true"/>
                </div>
                <div class="form-group">
                    <form:label path="title">Title</form:label>
                    <form:input path="title" class="form-control" placeholder="Enter email"/>
                </div>
                <div class="form-group">
                    <form:label path="description">Description</form:label>
                    <form:input path="description" class="form-control" placeholder="Enter a descriptive message giving other users more details about the book you offer or possible trades"/>
                </div>
                <div class="form-group">
                    <form:label path="moduleCode">Module Code</form:label>
                    <form:input path="moduleCode" class="form-control" placeholder="Enter email"/>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>

            </form:form>
        </div>
    </jsp:body>
</t:genericpage>