<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default-template title="${status} Error">
    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading">Error ${status}: ${error}</h1>
            <p class="lead">${message}</p>
        </div>
    </section>
</t:default-template>
