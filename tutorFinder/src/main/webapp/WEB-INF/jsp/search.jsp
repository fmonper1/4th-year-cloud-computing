<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Album example · Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/album/">

    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com/docs/4.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }
        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
        #peanut {
            height: 2rem;
        }
        #cloud {
            height: 2rem;
            margin: 0 .5rem 0 0;
        }
        .bg-purple {
            background: purple;
        }
        .bg-blue{
            background: #007bff
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="album.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-md-7 py-4">
                    <h4 class="text-white"><img id="peanut" src="<c:url value="/resources/img/peanut.svg" />"> Peanut Bank</h4>
                    <p class="text-muted">Peanuts are the equivalent of credit. You will need peanuts in order to use the services available in the platform.</p>
                    <p class="text-white">Peanuts in bank: 15<br>Peanuts used in the last 30 days: 3/30</p>
                </div>
                <div class="col-sm-4 offset-md-1 py-4">
                    <h4 class="text-white">Navigation</h4>
                    <ul class="list-unstyled">
                        <li><a href="#" class="text-white">Profile</a></li>
                        <li><a href="#" class="text-white">Signout</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container d-flex justify-content-between">
            <a href="/demo/index" class="navbar-brand d-flex align-items-center">
                <img id="cloud" src="<c:url value="/resources/img/hat.png" />">
                <strong> Cloud Tutor Finder</strong>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>
</header>

<main role="main" class="container">
    <%--<div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-sm">--%>
    <%--<img class="mr-3" src="img/speech-bubbles-comment-option.svg" alt="" width="48" height="48">--%>
    <%--<div class="lh-100">--%>
    <%--<h6 class="mb-0 text-white lh-100">Latest Trade Offers</h6>--%>
    <%--<small>Trade books directly with other students.</small>--%>
    <%--</div>--%>
    <%--</div>--%>

    <%--<div class="my-3 p-3 bg-white rounded shadow-sm">--%>
    <%--<!-- <h6 class="border-bottom border-gray pb-2 mb-0">Recent updates</h6> -->--%>

    <%--<c:forEach  items="${tutors}" var="tutor">--%>

    <%--<div class="media text-muted pt-3">--%>
    <%--<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#e83e8c"></rect><text x="50%" y="50%" fill="#e83e8c" dy=".3em">32x32</text></svg>--%>
    <%--<div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">--%>
    <%--<div class="d-flex justify-content-between align-items-center w-100">--%>
    <%--<strong class="text-gray-dark">${tutor.firstName} ${tutor.lastName}</strong>--%>
    <%--<a href="tutor/${tutor.id}/view">View Tutor Details</a>--%>
    <%--</div>--%>
    <%--<span class="d-block">Address: ${tutor.address}</span>--%>
    <%--<span class="d-block">Phone Number: ${tutor.phoneNumber}</span>--%>
    <%--<span class="d-block">Subjects Taught: <c:forEach  items="${tutor.subjects}" var="subject">${subject.name}, </c:forEach> </span>--%>

    <%--</div>--%>
    <%--</div>--%>

    <%--</c:forEach>--%>
    <%--&lt;%&ndash;<small class="d-block text-right mt-3">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<a href="#">All trade offers</a>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</small>&ndash;%&gt;--%>
    <%--</div>--%>

    <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-sm">
        <img class="mr-3" src="<c:url value="/resources/img/learn.png" />" alt="" width="48" height="48">
        <div class="lh-100">
            <h6 class="mb-0 text-white lh-100">Search for Tutors by Name</h6>
            <small>You can search using their first or last name</small>
        </div>
    </div>
    <c:url var="post_tutor"  value="/demo/tutorResults" />
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
    <c:url var="post_subject"  value="/demo/subjectResults" />
    <form:form method="POST" action="${post_subject}">
        <div class="input-group mb-3">
            <input type="text" class="form-control form-control-lg" placeholder="Search by Subject" name="searchSubject" id="searchSubject" value="${searchSubject}"/>
            <div class="input-group-append">
                <input type="submit"  class="btn btn-outline-secondary" value="Search"/>
            </div>
        </div>
    </form:form>



</main>


</main>

<footer class="text-muted">
    <div class="container">
        <p class="float-right">
            <a href="#">Back to top</a>
        </p>
        <p>University Of Sheffield 2019 - Cloud Computing Group 1</p>
    </div>
</footer>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="https://getbootstrap.com/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script><script src="https://getbootstrap.com/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script></body>
</html>