<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="my" uri="http://utils.cc19grp10.shef.ac.uk/tags"%>--%>

<%--<html lang="en">--%>
<%--<head>--%>
    <%--<title>Signup</title>--%>
    <%--<link href="<c:url value="/resources/style.css" />" rel="stylesheet">--%>
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">--%>
    <%--<style>--%>
        <%--.bd-placeholder-img {--%>
            <%--font-size: 1.125rem;--%>
            <%--text-anchor: middle;--%>
            <%---webkit-user-select: none;--%>
            <%---moz-user-select: none;--%>
            <%---ms-user-select: none;--%>
            <%--user-select: none;--%>
        <%--}--%>

        <%--@media (min-width: 768px) {--%>
            <%--.bd-placeholder-img-lg {--%>
                <%--font-size: 3.5rem;--%>
            <%--}--%>
        <%--}--%>
        <%--#peanut {--%>
            <%--height: 2rem;--%>
        <%--}--%>
        <%--#cloud {--%>
            <%--height: 2rem;--%>
            <%--margin: 0 .5rem 0 0;--%>
        <%--}--%>
    <%--</style>--%>
    <%--<!-- Custom styles for this template -->--%>
    <%--<link href="album.css" rel="stylesheet">--%>
<%--</head>--%>
<%--<body>--%>
<%--<header>--%>
    <%--<div class="collapse bg-dark" id="navbarHeader">--%>
        <%--<div class="container">--%>
            <%--<div class="row">--%>
                <%--<div class="col-sm-8 col-md-7 py-4">--%>
                    <%--<h4 class="text-white"><img id="peanut" src="peanut.svg"> Peanut Bank</h4>--%>
                    <%--<p class="text-muted">Peanuts are the equivalent of credit. You will need peanuts in order to use the services available in the platform.</p>--%>
                    <%--<p class="text-white">Peanuts in bank: 15<br>Peanuts used in the last 30 days: 3/30</p>--%>
                <%--</div>--%>
                <%--<div class="col-sm-4 offset-md-1 py-4">--%>
                    <%--<h4 class="text-white">Navigation</h4>--%>
                    <%--<ul class="list-unstyled">--%>
                        <%--<li><a href="#" class="text-white">Profile</a></li>--%>
                        <%--<li><a href="#" class="text-white">Signout</a></li>--%>
                    <%--</ul>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="navbar navbar-dark bg-dark shadow-sm">--%>
        <%--<div class="container d-flex justify-content-between">--%>
            <%--<a href="#" class="navbar-brand d-flex align-items-center">--%>
                <%--<img id="cloud" src="cloud.svg">--%>
                <%--<strong> Cloud Student Suite</strong>--%>
            <%--</a>--%>
            <%--<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">--%>
                <%--<span class="navbar-toggler-icon"></span>--%>
            <%--</button>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</header>--%>

<%--<main role="main">--%>

    <%--<section class="jumbotron text-center">--%>
        <%--<div class="container">--%>
            <%--<h1 class="jumbotron-heading">Student Cloud Suite</h1>--%>
            <%--<p class="lead text-muted">Something short and leading about the collection below—its contents, the creator, etc. Make it short and sweet, but not too short so folks don’t simply skip over it entirely.</p>--%>
            <%--<p>--%>
                <%--<a href="#" class="btn btn-primary my-2">Signup</a>--%>
                <%--<a href="#" class="btn btn-secondary my-2">Login</a>--%>
            <%--</p>--%>
        <%--</div>--%>
    <%--</section>--%>

    <%--<div class="album py-5 bg-light">--%>
        <%--<div class="container">--%>

            <%--<div class="row">--%>
                <%--<div class="col-md-4">--%>
                    <%--<div class="card mb-4 shadow-sm">--%>
                        <%--<img class="bd-placeholder-img card-img-top" src="photo6076.png" />--%>
                        <%--<!-- <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/></svg> -->--%>
                        <%--<div class="card-body">--%>
                            <%--<p class="card-text">Find textbooks you need and trade you old ones for newer ones. This hub conects students in order to exhange any kind of book between them</p>--%>
                            <%--<div class="d-flex justify-content-between align-items-center">--%>
                                <%--<div class="btn-group">--%>
                                    <%--<button type="button" class="btn btn-sm btn-outline-secondary">Launch</button>--%>
                                    <%--<button type="button" class="btn btn-sm btn-outline-secondary">More info</button>--%>
                                <%--</div>--%>
                                <%--<!-- <small class="text-muted">9 mins</small> -->--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-md-4">--%>
                    <%--<div class="card mb-4 shadow-sm">--%>
                        <%--<img class="bd-placeholder-img card-img-top" src="http://i.pravatar.cc/300" />--%>
                        <%--<!-- <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/></svg> -->--%>
                        <%--<div class="card-body">--%>
                            <%--<p class="card-text">Need extra help with a subject? Want to learn a language? Find a tutor to further develop you skills outside from university.</p>--%>
                            <%--<div class="d-flex justify-content-between align-items-center">--%>
                                <%--<div class="btn-group">--%>
                                    <%--<button type="button" class="btn btn-sm btn-outline-secondary">Launch</button>--%>
                                    <%--<button type="button" class="btn btn-sm btn-outline-secondary">More info</button>--%>
                                <%--</div>--%>
                                <%--<!-- <small class="text-muted">9 mins</small> -->--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>

                <%--<div class="col-md-4">--%>
                    <%--<div class="card mb-4 shadow-sm">--%>
                        <%--<img class="bd-placeholder-img card-img-top" src="cloud-computing.jpg" />--%>
                        <%--<!-- <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/></svg> -->--%>
                        <%--<div class="card-body">--%>
                            <%--<p class="card-text">Another service provided by another group from the module. This would be its description.</p>--%>
                            <%--<div class="d-flex justify-content-between align-items-center">--%>
                                <%--<div class="btn-group">--%>
                                    <%--<button type="button" class="btn btn-sm btn-outline-secondary">Launch</button>--%>
                                    <%--<button type="button" class="btn btn-sm btn-outline-secondary">More info</button>--%>
                                <%--</div>--%>
                                <%--<!-- <small class="text-muted">9 mins</small> -->--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

<%--</main>--%>

<%--<footer class="text-muted">--%>
    <%--<div class="container">--%>
        <%--<p class="float-right">--%>
            <%--<a href="#">Back to top</a>--%>
        <%--</p>--%>
        <%--<p>University Of Sheffield 2019 - Cloud Computing Group 1</p>--%>
    <%--</div>--%>
<%--</footer>--%>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<%--<script>window.jQuery || document.write('<script src="https://getbootstrap.com/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script><script src="https://getbootstrap.com/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script></body>--%>
<%--</html>--%>



















<%--<html lang="en"><head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
    <%--<meta name="description" content="">--%>
    <%--<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">--%>
    <%--<meta name="generator" content="Jekyll v3.8.5">--%>
    <%--<title>Signin Template · Bootstrap</title>--%>

    <%--<link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/sign-in/">--%>

    <%--<!-- Bootstrap core CSS -->--%>
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">--%>


    <%--<style>--%>
        <%--.bd-placeholder-img {--%>
            <%--font-size: 1.125rem;--%>
            <%--text-anchor: middle;--%>
            <%---webkit-user-select: none;--%>
            <%---moz-user-select: none;--%>
            <%---ms-user-select: none;--%>
            <%--user-select: none;--%>
        <%--}--%>

        <%--@media (min-width: 768px) {--%>
            <%--.bd-placeholder-img-lg {--%>
                <%--font-size: 3.5rem;--%>
            <%--}--%>
        <%--}--%>
    <%--</style>--%>
    <%--<!-- Custom styles for this template -->--%>
    <%--<link href="signin.css" rel="stylesheet">--%>
<%--</head>--%>
<%--<body class="text-center">--%>
<%--<form class="form-signin">--%>
    <%--<img class="mb-4" src="/docs/4.3/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">--%>
    <%--<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>--%>
    <%--<label for="inputEmail" class="sr-only">Email address</label>--%>
    <%--<input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">--%>
    <%--<label for="inputPassword" class="sr-only">Password</label>--%>
    <%--<input type="password" id="inputPassword" class="form-control" placeholder="Password" required="">--%>
    <%--<div class="checkbox mb-3">--%>
        <%--<label>--%>
            <%--<input type="checkbox" value="remember-me"> Remember me--%>
        <%--</label>--%>
    <%--</div>--%>
    <%--<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>--%>
    <%--<p class="mt-5 mb-3 text-muted">© 2017-2019</p>--%>
<%--</form>--%>


<%--<footer class="text-muted">--%>
    <%--<div class="container">--%>
        <%--<p class="float-right">--%>
            <%--<a href="#">Back to top</a>--%>
        <%--</p>--%>
        <%--<p>University Of Sheffield 2019 - Cloud Computing Group 1</p>--%>
    <%--</div>--%>
<%--</footer>--%>

<%--</body></html>--%>