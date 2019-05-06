<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:template>
    <jsp:body>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Error ${status}: ${error}</h1>
                <p class="lead">${message}</p>
            </div>
        </section>
    </jsp:body>
</t:template>