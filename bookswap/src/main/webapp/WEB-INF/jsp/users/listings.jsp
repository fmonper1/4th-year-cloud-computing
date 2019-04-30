
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericpage>
    <jsp:attribute name="header">
      <h1>Welcome</h1>
    </jsp:attribute>

    <jsp:body>
        <div class="my-3 p-3 bg-white rounded shadow-sm">
            <h3>My Listings</h3>
            <c:forEach  items="${listings}" var="listing">
                <p>
                    <strong class="text-gray-dark">Looking for ${listing.title}</strong>
                    <a href="/listing/${listing.id}/view">View Listing</a>
                    <span class="d-block">${listing.description}</span>
                </p>
            </c:forEach>
        </div>
    </jsp:body>
</t:genericpage>