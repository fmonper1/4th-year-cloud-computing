<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<t:genericpage>
    <jsp:attribute name="header">
      <h1>Welcome</h1>
    </jsp:attribute>

    <jsp:body>

        <div class="my-3 p-3 bg-white rounded shadow-sm">
            <h4 class="border-bottom border-gray pb-2">My Details</h4>
            <dl class="row">
                <dt class="col-sm-3">Display Name</dt>
                <dd class="col-sm-9">${user.name}</dd>

                <dt class="col-sm-3">University Email</dt>
                <dd class="col-sm-9">${user.email}</dd>

                <dt class="col-sm-3">Alternative Email</dt>
                <dd class="col-sm-9">${user.altEmail}</dd>

                <dt class="col-sm-3">Phone Number</dt>
                <dd class="col-sm-9">${user.phoneNumber}</dd>
            </dl>

            <h4 class="border-bottom border-gray pb-2">Update Details</h4>


            <form:form method="POST"
                       action="/user/update" modelAttribute="form">
                ${createListingForm.error}
                ${error}
                <div class="form-group">
                    <form:label path="name">Display Name</form:label>
                    <form:input path="name" class="form-control" placeholder="Your name to be displayed on the site"/>
                </div>
                <div class="form-group">
                    <form:label path="phoneNumber">Phone Number</form:label>
                    <form:input path="phoneNumber" class="form-control" placeholder="Your phone number"/>
                </div>
                <div class="form-group">
                    <form:label path="altEmail">Alternative Email</form:label>
                    <form:input path="altEmail" class="form-control" placeholder="An email different to that of the university"/>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form:form>
        </div>
    </jsp:body>
</t:genericpage>