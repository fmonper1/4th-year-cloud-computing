<%@ tag import="java.util.Calendar" %>
<%@ tag description="General page template" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true" type="java.lang.String" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
    <head>
        <title>Peanut Bank - ${title}</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
    </head>
    <body class="d-flex flex-column">
        <header>
            <div class="collapse bg-dark" id="navbarHeader">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-md-7 py-4">
                            <h4 class="text-white"><img id="peanut" src="<c:url value="/resources/img/peanut.svg" />"> Peanut Bank</h4>
                            <p class="text-light">Peanuts are the equivalent of credit. You will need peanuts in order to use the services available in the platform.</p>

                            <c:if test="${currentUser != null && currentUser.account != null}">
                                <p class="text-white">Peanuts in bank: ${currentUser.account.balance}</p>
                            </c:if>
                        </div>
                        <div class="col-sm-4 offset-md-1 py-4">
                            <h4 class="text-white">Navigation</h4>
                            <ul class="list-unstyled">
                                <c:if test="${currentUser == null}">
                                    <li><a href="<c:url value="/account" />" class="text-white">View Your Account</a></li>
                                </c:if>
                                <c:if test="${currentUser != null}">
                                    <li><a href="<c:url value="/account" />" class="text-white">${currentUser.name}'s Account</a></li>
                                </c:if>
<%--                                <li><a href="#" class="text-white">Signout</a></li>--%>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="navbar navbar-dark bg-dark shadow-sm">
                <div class="container d-flex justify-content-between">
                    <a href="<c:url value="/" />" class="navbar-brand d-flex align-items-center">
                        <img id="cloud" src="<c:url value="/resources/img/cloud.svg" />">
                        <strong>Cloud Student Suite</strong>
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                </div>
            </div>
        </header>


        <main role="main">
            <jsp:doBody />
        </main>

        <footer class="footer mt-auto py-3 bg-light">
            <div class="container">
                <span class="text-muted">
                    <span class="d-none d-md-inline">University Of Sheffield</span><span class="d-md-none d-inline">UoS</span> - COM4519 Group 1 <%= Calendar.getInstance().get(Calendar.YEAR) %>
                </span>
                <span class="text-muted float-right">Peanut Bank</span>
            </div>
        </footer>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script>window.jQuery || document.write('<script src="https://getbootstrap.com/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script><script src="https://getbootstrap.com/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
    </body>
</html>