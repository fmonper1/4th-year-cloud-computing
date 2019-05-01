<%--
  Created by IntelliJ IDEA.
  User: euan
  Date: 10/03/19
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>

    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading">Student Cloud Suite</h1>
            <p class="lead text-muted">Something short and leading about the collection below—its contents, the creator, etc. Make it short and sweet, but not too short so folks don’t simply skip over it entirely.</p>
            <p>
                <c:choose>
                    <c:when test="${user != null}">
                        Hello ${username}!
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value="/login"/>" class="btn btn-primary my-2">Signup or login</a>
                    </c:otherwise>
                </c:choose>
            </p>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">

            <div class="row">
                <c:forEach var="app" items="${apps}">
                    <div class="col-md-4">
                        <div class="card h-100 mb-4 shadow-sm">
                            <img class="bd-placeholder-img card-img-top" src="<c:url value="/api/app/${app.id}/image" />" />
                            <div class="card-body">
                                <p class="card-text">${app.description}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <a type="button" class="btn btn-sm btn-outline-secondary" href="${app.url}">Launch</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</t:template>
