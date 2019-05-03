<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default-template title="Make a payment">
    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading">Make A Payment</h1>
        </div>
    </section>

    <div class="py-5">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-4 offset-md-1">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-header">
                            Who are you paying?
                        </div>
                        <div class="card-body">
                            <p class="card-text">This payment will be deposited into account number ${bill.toAccount.id}.</p>
                            <c:if test="${bill.toAccount.owner != null}">
                                <p class="card-text">This account is owned by ${bill.toAccount.owner.name}.</p>
                            </c:if>
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

                            <p class="card-text">Do you want to make a payment of ${bill.amount} peanuts to account number ${bill.toAccount.id}?</p>

                            <div class="d-flex justify-content-between align-items-center">
                                <form:form method="POST">
                                    <div class="btn-group">
                                        <button name="accept" type="submit" value="no" class="btn btn-outline-secondary">Cancel</button>
                                        <button name="accept" type="submit" value="yes" class="btn btn-outline-primary">Accept Payment</button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:default-template>