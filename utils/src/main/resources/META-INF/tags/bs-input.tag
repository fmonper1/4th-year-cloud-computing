<%@ attribute name="path" required="true" %>
<%@ attribute name="placeholder" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="type" required="true" %>
<%@ attribute name="help" required="false"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="form-group">
    <spring:bind path="${path}">
        <form:label path="${path}">${label}</form:label>
        <form:input class="${type == 'file'? 'form-control-file': 'form-control' } ${status.error ? 'is-invalid' : ''}" path="${path}" placeholder="${placeholder}" type="${type}" aria-described-by="${path}HelpBlock" />
        <form:errors path="${path}" cssClass="invalid-feedback" element="div" />
        <c:if test="${not empty help}" >
            <small id="${path}HelpBlock" class="form-text text-muted">
                ${help}
            </small>
        </c:if>
    </spring:bind>
</div>