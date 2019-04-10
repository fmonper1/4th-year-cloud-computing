<%@ attribute name="path" required="true" %>
<%@ attribute name="placeholder" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="type" required="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="form-group">
    <spring:bind path="${path}">
        <form:label path="${path}">${label}</form:label>
        <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="${path}" placeholder="${placeholder}" type="${type}"/>
        <form:errors path="${path}" cssClass="invalid-feedback" element="div" />
    </spring:bind>
</div>