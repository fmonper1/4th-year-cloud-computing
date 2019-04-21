<%--
  Created by IntelliJ IDEA.
  User: euan
  Date: 10/03/19
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="my" uri="http://utils.cc19grp10.shef.ac.uk/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <div class="container h-100">
        <div class="row h-100 justify-content-center align-items-center">
            <div class="col-6">
                <c:choose>
                    <c:when test="${app != null}">
                        <h1>Application: ${app.name}</h1>
                        <c:choose>
                            <c:when test="${dbApp != null}">
                                <h1>Database details:</h1>
                                <table class="table">
                                    <tr>
                                        <td>Database username:</td><td>${dbApp.dbUsername}</td>
                                    </tr>
                                    <tr>
                                        <td>Database url:</td><td>${dbApp.dbUrl}</td>
                                    </tr>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <h1>You have not setup your database:</h1>
                                <a href="/developer/db/create" class="btn btn-primary btn-block">Create database</a>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${authApp != null}">
                                <h1>Authentication Details:</h1>
                                <table class="table">
                                    </tr>
                                    <tr>
                                        <td>Auth: Client ID:</td><td>${authApp.clientId}</td>
                                    </tr>
                                    <tr>
                                        <td>Auth: Client Secret:</td><td>${authApp.encodedClientSecret}</td>
                                    </tr>
                                    <tr>
                                        <td>Auth:  Redirect Uri:</td><td>${authApp.redirectUri}</td>
                                    </tr>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <h1>You have not setup authentication:</h1>
                                <a href="/developer/auth/create" class="btn btn-primary btn-block">Create authentication credentials</a>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <h1>You have not created an application:</h1>
                        <a href="/developer/create" class="btn btn-primary btn-block">Create application</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</t:template>
