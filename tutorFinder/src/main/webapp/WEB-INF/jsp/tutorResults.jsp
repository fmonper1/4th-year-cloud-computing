<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:template>
    <jsp:body>
        <br></br>
            <c:url var="post_tutor"  value="/tutorResults" />
            <form:form method="POST" action="${post_tutor}">
                <div class="input-group mb-3">
                    <input type="text" class="form-control form-control-lg" placeholder="Search by Tutor's Name" name="search" id="search" value="${search}"/>
                    <div class="input-group-append">
                        <input type="submit"  class="btn btn-outline-secondary" value="Search"/>
                    </div>
                </div>
            </form:form>

            <br>
            <br>
            <c:if test="${empty(results)}">
                <strong class="text-gray-dark">Sorry no results were found for this tutor name</strong>
                <br>
                <br>
                <br>
            </c:if>
            <c:if test="${not empty(results)}">
            <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-sm">
                <img class="mr-3" src="<c:url value="/resources/img/learn.png" />"  alt="" width="48" height="48">
                <div class="lh-100">
                    <h6 class="mb-0 text-white lh-100">Results for Tutors</h6>
                    <small style="color: white">Contact the tutors below and find out more</small>
                </div>
            </div>
            <div class="my-3 p-3 bg-white rounded shadow-sm">
                <c:forEach  items="${results}" var="tutor">

                <div class="media text-muted pt-3">
                    <svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#e83e8c"></rect><text x="50%" y="50%" fill="#e83e8c" dy=".3em">32x32</text></svg>
                    <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                        <div class="d-flex justify-content-between align-items-center w-100">
                            <strong class="text-gray-dark">${tutor.firstName} ${tutor.lastName}</strong>
                            <c:url var="view_tutor"  value="/tutor/${tutor.id}/view" />
                            <a href="${view_tutor}">View Tutor Details</a>
                        </div>
                        <span class="d-block">Email Address: ${tutor.emailAddress}</span>
                        <span class="d-block">Phone Number: ${tutor.phoneNumber}</span>
                        <span class="d-block">Subjects Taught: <c:forEach  items="${tutor.subjects}" var="subject">${subject.name} </c:forEach> </span>
                    </div>
                    </c:forEach>
                </div>
                </c:if>

    </jsp:body>
</t:template>


