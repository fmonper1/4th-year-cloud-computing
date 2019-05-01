# Payment Microservice

This subproject contains the application code for the payment microservice or 'Peanut Bank'.

This microservice authenticates with the _auth_ microservice, creating an account for each user. Two additional accounts are created initially for the _auth_ microservice and the _payment_ microservice itself. These have no owners.

## Connecting and viewing my account

You can simply head to the payment microservice from the cloud dashboard. To find your account details, follow the links on the page or go directly to `/account` on the payment microservice.

## Connecting my application with the microservice

This section explains how to link your application into the payment microservice, allowing you to charge users.

### Using the util classes

Currently, there is one utility class _BillUtility_. To use it, there is a little configuration required.

#### Application properties

Please set the following values in your application configuration file - this is often named _application.properties_.

- `payment.server.base_url=http://localhost:8080`
    - This is the root of the payment application. In some instances, it may instead be under a path on the host such as `/payment`.
- `payment.server.api_url=http://localhost:8080/api/v1`
    - This is the root of the HTTP REST API for the payment application.
- `payment.application_account_id=4`
    - This is the ID of the default account to send peanuts to when creating new bills. You can find your account ID by visiting the payment microservice.

#### Application class

You should have one class typically named _SomethingApplication.java_ with the `@SpringBootApplication` annotation. In order to use the _BillUtility_ class, you need to update the _scanBasePackages_ argument to include `uk.ac.shef.cc19grp01.utils.payment`. i.e. It should looks like this: `@SpringBootApplication(scanBasePackages = {"com.example.myapplication" ,"uk.ac.shef.cc19grp01.utils.payment"})`.

#### Using the API provided by _BillUtility_

##### Create a bill

You can create a bill using `BillUtility.createBill(int amount)`. This returns the ID as a _long_ referencing the created bill.

##### Payment of the bill

`BillUtility.redirectUserToBillPayment(long billId, String callbackUri)` will direct your user to the appropriate payment page. They will be asked to login if they weren't already. The callback URI is where you want your user to be sent after they accept or decline the payment. This should be an URL you have created, and the query parameter `success` will be attached with a value of `0` for failure or `1` for success. However, this callback is only informational and should not be trusted.

You can then use `BillUtility.verifyBillPaid(long billId)` to assert that a bill has been paid. This simply returns a boolean value.

### Using HTTP

We recommend using the utility class where possible, but some of the API methods are shown below.

| Method | Route                | Parameters            | Description                      |
|--------|----------------------|-----------------------|----------------------------------|
| GET    | `/api/v1/bills/<id>` | -                     | Gets the specified bill          |
| POST   | `/api/v1/bills/`     | `toAccount`, `amount` | Creates a new bill               |
| GET    | `/bills/<id>/pay`    | `callbackUri`         | Shows a payment form to the user |
