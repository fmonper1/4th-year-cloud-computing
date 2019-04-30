<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
          <h4 class="text-white"><img id="peanut" src="img/peanut.svg"> Peanut Bank</h4>
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
      <a href="#" class="navbar-brand d-flex align-items-center">
        <img id="cloud" src="img/open-book.svg">
        <strong> Cloud Book Swap</strong>
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    </div>
  </div>
</header>

        <main role="main" class="container">
            <br>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand" href="/">Navbar</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                  <ul class="navbar-nav">
                    <li class="nav-item active">
                      <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="/listing/add">Create Listing</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="/user/listings">My Listings</a>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Dropdown link
                      </a>
                      <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                      </div>
                    </li>
                  </ul>
                </div>
              </nav>

            <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-sm">
              <img class="mr-3" src="img/speech-bubbles-comment-option.svg" alt="" width="48" height="48">
              <div class="lh-100">
                <h6 class="mb-0 text-white lh-100">Latest Trade Offers</h6>
                <small>Trade books directly with other students.</small>
              </div>
            </div>

            <div class="my-3 p-3 bg-white rounded shadow-sm">
              <!-- <h6 class="border-bottom border-gray pb-2 mb-0">Recent updates</h6> -->
            
              <c:forEach  items="${listings}" var="listing">
                <div class="media text-muted pt-3">
                    <svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#e83e8c"></rect><text x="50%" y="50%" fill="#e83e8c" dy=".3em">32x32</text></svg>
                    <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                        <div class="d-flex justify-content-between align-items-center w-100">
                          <strong class="text-gray-dark">[${listing.moduleCode}] ${listing.title}</strong>
                          <a href="listing/${listing.id}/view">View Listing</a>
                        </div>
                        <span class="d-block">${listing.description}</span>
                      </div>
                  </div>
                  
            </c:forEach>
              <small class="d-block text-right mt-3">
                <a href="#">All trade offers</a>
              </small>
            </div>
          
            <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-blue rounded shadow-sm">
              <img class="mr-3" src="img/package.svg" alt="" width="48" height="48">
              <div class="lh-100">
                <h6 class="mb-0 text-white lh-100">Search for specific items</h6>
                <small>Trade books directly with other students.</small>
              </div>
            </div>
            <form:form method="GET" action="/search" >
          <div class="input-group mb-3">
              <input name="parameter" type="text" class="form-control form-control-lg" placeholder="Search by title, author or editorial" aria-label="Book Name" aria-describedby="button-addon2">
              <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Search</button>
              </div>
            </div>
            </form:form>
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
