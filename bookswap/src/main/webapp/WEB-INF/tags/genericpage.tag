<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Book Swap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/album/">

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
    <link href="album.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-md-7 py-4">
                </div>
                <div class="col-sm-4 offset-md-1 py-4">
                    <h4 class="text-white">Navigation</h4>
                    <ul class="list-unstyled">
                        <li><a href="/" class="text-white">Back to Dashboard</a></li>
                        <li><a href="/app_payments" class="text-white">Peanut Bank</a></li>
                        <li><a href="/app_tutor_finder" class="text-white">Tutor Finder</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container d-flex justify-content-between">
            <a href="<c:url value="/"/>" class="navbar-brand d-flex align-items-center">
                <img id="cloud" src="<c:url value="/img/open-book.svg"/>">
                <strong> Cloud Book Swap</strong>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>
</header>
<div class="nav-scroller bg-white shadow-sm">
    <div class="container">
        <nav class="nav nav-underline">
            <a class="nav-link" href="<c:url value="/"/>">Home</a>
            <a class="nav-link" href="<c:url value="/search"/>">Search</a>
            <a class="nav-link" href="<c:url value="/user/listings"/>">My Listings</a>
            <a class="nav-link" href="<c:url value="/listing/add"/>">Create Listing</a>
            <a class="nav-link" href="<c:url value="/user/profile"/>">Profile</a>
        </nav>
    </div>
</div>
<main role="main" class="container" role="main">
    <div id="body">
        <jsp:doBody/>
    </div>
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

