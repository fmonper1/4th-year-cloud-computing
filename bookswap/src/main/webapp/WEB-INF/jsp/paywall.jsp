<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:genericpage>
    <jsp:attribute name="header">
      <h1>Welcome</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
        Add a new listing
    </jsp:attribute>
    <jsp:body>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Student Cloud Suite</h1>
                <p class="lead text-muted">PAY</p>
                <p>
                    <a href="${payUri}" class="btn btn-lg btn-danger">Pay immediately</a>
                </p>
            </div>
        </section>
    </jsp:body>
</t:genericpage>