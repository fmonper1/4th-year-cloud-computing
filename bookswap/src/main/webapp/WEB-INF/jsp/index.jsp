<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericpage>
    <jsp:attribute name="header">
      <h1>Welcome</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
        Add a new listing
    </jsp:attribute>
    <jsp:body>

        <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-blue rounded shadow-sm">
            <img class="mr-3" src="<c:url value="img/package.svg"/>" alt="" width="48" height="48">
            <div class="lh-100">
                <h6 class="mb-0 text-white lh-100">Search for specific items</h6>
                <small>Trade books directly with other students.</small>
            </div>
        </div>
        <c:url var="search_url" value="search"/>
        <form:form method="GET" action="${search_url}" >
            <div class="input-group mb-3">
                <input name="parameter" type="text" class="form-control form-control-lg" placeholder="Search by listing title, department code or module code" aria-label="Book Name" aria-describedby="button-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Search</button>
                </div>
            </div>
        </form:form>


        <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-sm">
            <img class="mr-3" src="<c:url value='img/package.svg'/>" alt="" width="48" height="48">
            <div class="lh-100">
                <h6 class="mb-0 text-white lh-100">Most Recent Listings</h6>
                <small>Listings created by other users appear here.</small>
            </div>
        </div>

        <div class="my-3 p-3 bg-white rounded shadow-sm">
            <!-- <h6 class="border-bottom border-gray pb-2 mb-0">Recent updates</h6> -->

            <c:forEach  items="${listings}" var="listing">
                <div class="media text-muted pt-3">
                    <c:if test="${listing.closed}"><svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#212529"></rect><text x="50%" y="50%" fill="#212529" dy=".3em">32x32</text></svg></c:if>
                    <c:if test="${!listing.closed}"><svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#e83e8c"></rect><text x="50%" y="50%" fill="#e83e8c" dy=".3em">32x32</text></svg></c:if>
                    <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                        <div class="d-flex justify-content-between align-items-center w-100">
                            <strong class="text-gray-dark">[${listing.moduleCode}] ${listing.title}</strong>
                            <a href="<c:url value="listing/${listing.id}/view"/>">View Listing</a>
                        </div>
                        <span class="d-block">${listing.description}</span>
                    </div>
                </div>

            </c:forEach>
        </div>


    </jsp:body>
</t:genericpage>
