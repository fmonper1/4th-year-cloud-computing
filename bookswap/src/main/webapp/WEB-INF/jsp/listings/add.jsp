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
            <h4 class="border-bottom border-gray pb-2">Add A Listing</h4>
            <c:url var="search_url" value="listing/add"/>
            <form:form method="POST"
                       action="${search_url}" modelAttribute="createListingForm">
                ${createListingForm.error}
                ${error}
                <div class="form-group">
                    <form:label path="title">Title</form:label>
                    <form:input path="title" class="form-control" placeholder="Enter a title (min 4 characters)"/>
                </div>
                <div class="form-group">
                    <form:label path="description">Description</form:label>
                    <form:input path="description" class="form-control" placeholder="Enter a descriptive message giving other users more details about the book you offer or possible trades"/>
                </div>
                <div class="form-group">
                    <form:label path="moduleCode">Module Code</form:label>
                    <form:input path="moduleCode" class="form-control" placeholder="Enter a module code i.e. COM1002, AERO1230..."/>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form:form>
        </div>
    </jsp:body>
</t:genericpage>