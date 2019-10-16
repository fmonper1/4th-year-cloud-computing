
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>

    <br>
    <br>
    <section class="jumbotron text-center">
        <div class="container">
            <p class="lead text-muted">Please Pay Now To Enjoy This Service</p>
            <p>
                <a href="${payUri}" class="btn btn-lg btn-success">Pay immediately</a>
            </p>
        </div>
    </section>

</t:template>