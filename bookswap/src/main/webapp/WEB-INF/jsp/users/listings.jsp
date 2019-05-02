
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:genericpage>
    <jsp:attribute name="header">
      <h1>Welcome</h1>
    </jsp:attribute>

    <jsp:body>

        <div class="my-3 p-3 bg-white rounded shadow-sm">
            <h4 class="border-bottom border-gray pb-2">My Listings</h4>

            <ul>
            <c:forEach  items="${listings}" var="listing">
                <li>
                    <strong class="text-gray-dark">Looking for </strong>
                    <a href="/listing/${listing.id}/view">${listing.moduleCode} - ${listing.title}</a>
                    <span class="d-block"><fmt:formatDate value="${listing.createDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                </li>
            </c:forEach>
            </ul>
        </div>
    </jsp:body>
</t:genericpage>