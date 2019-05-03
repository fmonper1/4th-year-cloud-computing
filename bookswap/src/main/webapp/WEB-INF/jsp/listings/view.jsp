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
        <c:if test="${!listing.closed}">    <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-blue rounded shadow-sm">    </c:if>
        <c:if test="${listing.closed}">    <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-dark rounded shadow-sm">    </c:if>
        <img class="mr-3" src="<c:url value="/img/package.svg"/>" alt="" width="48" height="48">
        <div class="lh-100">
            <h6 class="mb-0 text-white lh-100"><c:if test="${listing.closed}">[CLOSED]</c:if> Viewing Lisiting for ${listing.title}</h6>
            <small>Module Code: ${listing.moduleCode}. Created at: ${listing.createDateTime}</small>
        </div>
        </div>


        <div class="my-3 p-3 bg-white rounded shadow-sm">
            <!-- <h6 class="border-bottom border-gray pb-2 mb-0">Recent updates</h6> -->
            <h4 class="border-bottom border-gray pb-2">Listing Details</h4>

            <p>${listing.description}</p>
            <p>created by: ${listing.user.name}, ${listing.user.email}, ${listing.user.altEmail}</p>

            <c:if test="${isOwner}">
                <a class="btn btn-primary" href="<c:url value="update"/>">Edit</a>
                <a class="btn btn-primary" href="<c:url value="close"/>">Close</a>
                <a class="btn btn-primary" href="<c:url value="delete"/>">Delete</a>
            </c:if>



            <hr/>
            <div class="row">
                <div class="col-sm">
                    <c:forEach  items="${searchResults}" var="searchResults">
                        <div class="media text-muted pt-3">
                            <c:if test="${searchResults.closed}"><svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#212529"></rect><text x="50%" y="50%" fill="#212529" dy=".3em">32x32</text></svg></c:if>
                            <c:if test="${!searchResults.closed}"><svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#e83e8c"></rect><text x="50%" y="50%" fill="#e83e8c" dy=".3em">32x32</text></svg></c:if>
                            <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                                <div class="d-flex justify-content-between align-items-center w-100">
                                    <strong class="text-gray-dark">[${searchResults.moduleCode}] ${searchResults.title}</strong>
                                    <a href="<c:url value="/listing/${searchResults.id}/view"/>">View Listing</a>
                                </div>
                                <span class="d-block">${searchResults.description}</span>
                            </div>
                        </div>
                    </c:forEach>
                    <a href="<c:url value="/"/>search?parameter=${listing.moduleCode}">View all listings for this module</a>

                </div>
                <div class="col-sm">
                    <c:forEach  items="${searchResults2}" var="searchResults2">
                        <div class="media text-muted pt-3">
                            <c:if test="${searchResults2.closed}"><svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#212529"></rect><text x="50%" y="50%" fill="#212529" dy=".3em">32x32</text></svg></c:if>
                            <c:if test="${!searchResults2.closed}"><svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#e83e8c"></rect><text x="50%" y="50%" fill="#e83e8c" dy=".3em">32x32</text></svg></c:if>
                            <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                                <div class="d-flex justify-content-between align-items-center w-100">
                                    <strong class="text-gray-dark">[${searchResults2.moduleCode}] ${searchResults2.title}</strong>
                                    <a href="<c:url value="/listing/${searchResults2.id}/view"/>">View Listing</a>
                                </div>
                                <span class="d-block">${searchResults2.description}</span>
                            </div>
                        </div>
                    </c:forEach>
                    <a href="<c:url value="/"/>search?parameter=${listing.moduleCode.replaceAll("\\d","")}">View all listings for this department</a>

                </div>
            </div>

        </div>

    </jsp:body>
</t:genericpage>
