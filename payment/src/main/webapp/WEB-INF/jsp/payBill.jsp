<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="my" uri="http://utils.cc19grp10.shef.ac.uk/tags"%>

<html lang="en">
    <head>
        <title>Pay Bill</title>
        <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
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
                        <h4 class="text-white"><img id="peanut" src="peanut.svg"> Peanut Bank</h4>
                        <p class="text-muted">Peanuts are the equivalent of credit. You will need peanuts in order to use the services available in the platform.</p>
                        <p class="text-white">Peanuts in bank: -<br>Peanuts used in the last 30 days: -</p>
                    </div>
    <%--                <div class="col-sm-4 offset-md-1 py-4">--%>
    <%--                    <h4 class="text-white">Navigation</h4>--%>
    <%--                    <ul class="list-unstyled">--%>
    <%--                        <li><a href="#" class="text-white">Profile</a></li>--%>
    <%--                        <li><a href="#" class="text-white">Signout</a></li>--%>
    <%--                    </ul>--%>
    <%--                </div>--%>
                </div>
            </div>
        </div>
        <div class="navbar navbar-dark bg-dark shadow-sm">
            <div class="container d-flex justify-content-between">
                <a href="#" class="navbar-brand d-flex align-items-center">
                    <img id="cloud" src="cloud.svg">
                    <strong> Cloud Student Suite</strong>
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
        </div>
    </header>

    <main role="main">

        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Make A Payment</h1>
            </div>
        </section>

        <div class="album py-5 bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-md-4 offset-md-1">
                        <div class="card mb-4 shadow-sm">
                            <div class="card-header">
                                Who are you paying?
                            </div>
                            <div class="card-body">
                                <p class="card-text">You're actually gonna pay some account with ID ${bill.toAccount.id}.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <div class="card mb-4 shadow-sm">
                            <div class="card-header">
                                Payment Details
                            </div>
                            <div class="card-body">
                                <c:if test="${error != null}">
                                    <div class="alert alert-danger" role="alert">
                                        ${error}
                                    </div>
                                </c:if>

                                <p class="card-text">Do you want to make a payment of ${bill.amount} peanuts to the account ${bill.toAccount.id}?</p>

                                <div class="d-flex justify-content-between align-items-center">
                                    <form:form method="POST">
                                        <div class="btn-group">
                                            <button name="accept" type="submit" value="no" class="btn btn-outline-secondary">Cancel</button>
                                            <button name="accept" type="submit" value="yes" class="btn btn-outline-success">Accept Payment</button>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
