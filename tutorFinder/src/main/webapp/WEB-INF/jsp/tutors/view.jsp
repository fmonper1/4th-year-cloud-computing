<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template>
    <jsp:body>
        <br>
        <br>
        <div class="row justify-content-center">
            <div class="col-md-10">
                <div class="d-flex align-items-center p-3 text-white-50 bg-blue rounded shadow-sm text-center">
                    <div class="text-center">
                        <h3 class="media-heading" style="color: white">${firstName} ${lastName}</h3>
                    </div>
                </div>
                <div id="container">
                    <span><strong>Subjects Taught: </strong></span>
                    <c:forEach  items="${subjects}" var="subject">
                        <span class="tags">${subject.name}</span>
                    <%--<span class="label label-info">Microsoft Office</span>--%>
                    <%--<span class="label label-success">Windows XP, Vista, 7</span>--%>
                    </c:forEach>
                    <br>
                    <br>
                    <span><strong>Email Address: </strong></span>
                        ${email}
                    <br>
                    <br>
                    <span><strong>Phone Number: </strong></span>
                    ${phoneNumber}
                    <br>
                    <br>
                    <span><strong>Address: </strong></span>
                    ${address}
                    <hr>
                    <p class="text-left"><strong>Bio: </strong>
                        ${bio}

                    </p>
                </div>
            </div>
        </div>
        </div>
        <c:if test="${not empty(relatedSubjects)}">
            <div class="row justify-content-center">
                <c:forEach  items="${relatedSubjects}" var="subject">

                    <div class="col-md-4">
                        <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-sm">
                            <img class="mr-3" src="<c:url value="/resources/img/subject.png" />"  alt="" width="48" height="48">
                            <div class="lh-100">
                                <h6 class="mb-0 text-white lh-100">Other tutors who teach ${subject.name}</h6>
                                <%--<small>Trade books directly with other students.</small>--%>
                            </div>
                        </div>


                        <c:forEach  items="${subject.tutors}" var="tutor">
                            <c:if test="${tutor.id != id}">
                            <div class="media text-muted pt-3">
                                <svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#e83e8c"></rect><text x="50%" y="50%" fill="#e83e8c" dy=".3em">32x32</text></svg>
                                <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                                    <div class="d-flex justify-content-between align-items-center w-100">
                                        <strong class="text-gray-dark">${tutor.firstName} ${tutor.lastName}</strong>
                                        <c:url var="view_tutor"  value="/tutor/${tutor.id}/view" />
                                        <a href="${view_tutor}">View Tutor Details</a>
                                    </div>
                                    <span class="d-block">Address: ${tutor.address}</span>
                                    <span class="d-block">Phone Number: ${tutor.phoneNumber}</span>
                                    <span class="d-block">Subjects Taught: <c:forEach  items="${tutor.subjects}" var="subject">${subject.name}, </c:forEach> </span>

                                </div>
                            </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>

        </c:if>

        <br>
        <br>

    </jsp:body>
</t:template>