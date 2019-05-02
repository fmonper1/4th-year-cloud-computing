<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default-template title="Home">
    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading">Welcome to the Peanut Bank</h1>
            <p class="lead">The Peanut Bank will have its due, they say.</p>
        </div>
    </section>
    <section class="text-center">
        <a class="btn btn-outline-primary btn-lg" href="<c:url value="/account" />" role="button">View Your Account</a>
    </section>
</t:default-template>
