<%@ page import="ac.uk.shef.cc19grp10.payment.data.Account" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default-template title="Account">
    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading">Welcome to the Peanut Bank</h1>
            <p class="lead">The Peanut Bank will have its due, they say.</p>
        </div>
    </section>

    <section>
        <div class="py-3">
            <div class="container">
                <h1>Your Account</h1>
            </div>
        </div>

        <div class="py-5">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-md-4">
                        <div class="card mb-4 shadow-sm">
                            <div class="card-header">
                                Account Details
                            </div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item font-weight-bold">Balance: <span class="float-right">${account.balance} peanuts</span></li>
                                <c:if test="${account.owner != null}">
                                    <li class="list-group-item">Owner: <span class="float-right">${account.owner.name}</span></li>
                                </c:if>
                                <li class="list-group-item">Account ID: <span class="float-right">${account.id}</span></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-8">
                        <div class="card mb-4 shadow-sm">
                            <div class="card-header">
                                Transactions
                            </div>

                            <c:if test="${transactions.size() == 0}">
                                <div class="card-body">
                                    <span>No transactions yet</span>
                                </div>
                            </c:if>

                            <c:if test="${transactions.size() > 0}">
                                <ul class="list-group list-group-flush">
                                    <c:forEach items="${transactions}" var="transaction">
                                        <li class="list-group-item list-group-item-action">
                                            <div class="d-flex w-100 justify-content-between">
                                                <h6 class="mb-1">${transaction.fromAccountName} -> ${transaction.toAccountName}</h6>
                                                <small>${transaction.createdAt}</small>
                                            </div>
                                            <c:if test="${transaction.credit}">
                                                <span class="mb-1 text-success">+${transaction.amount}</span>
                                            </c:if>
                                            <c:if test="${transaction.debit}">
                                                <span class="mb-1 text-danger">-${transaction.amount}</span>
                                            </c:if>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</t:default-template>