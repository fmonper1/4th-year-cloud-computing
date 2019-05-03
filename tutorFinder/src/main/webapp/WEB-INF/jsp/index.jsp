<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template>
    <jsp:body>

        <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-sm">
            <img class="mr-3" src="<c:url value="/resources/img/learn.png" />" alt="" width="48" height="48">
            <div class="lh-100">
                <h6 class="mb-0 text-white lh-100">Search for Tutors by Name</h6>
                <small>You can search using their first or last name</small>
            </div>
        </div>
        <c:url var="post_tutor"  value="/tutorResults" />
        <form:form method="POST" action="${post_tutor}">
            <div class="input-group mb-3">
                <input type="text" class="form-control form-control-lg" placeholder="Search by Name" name="search" id="search" value="${search}"/>
                <div class="input-group-append">
                    <input type="submit"  class="btn btn-outline-secondary" value="Search"/>
                </div>
            </div>
        </form:form>

        <br>
        <br>
        <br>



        <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-sm">
            <img class="mr-3" src="<c:url value="/resources/img/subject.png" />" alt="" width="38" height="48">
            <div class="lh-100">
                <h6 class="mb-0 text-white lh-100">Search for Tutors by Subject</h6>
                <small>Search for any subject and find tutors that can help you learn more</small>
            </div>
        </div>
        <c:url var="post_subject"  value="/subjectResults" />
        <form:form method="POST" action="${post_subject}">
            <div class="input-group mb-3">
                <input type="text" class="form-control form-control-lg" placeholder="Search by Subject" name="searchSubject" id="searchSubject" value="${searchSubject}"/>
                <div class="input-group-append">
                    <input type="submit"  class="btn btn-outline-secondary" value="Search"/>
                </div>
            </div>
        </form:form>


    </jsp:body>
</t:template>