<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 2019-04-24
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <style>
        table td{
            vertical-align:top;
            border:solid 1px #888;
            padding:10px;
        }
    </style>
</head>
<body>
<h1>My Error Page</h1>
<table>
    <tr>
        <td>Date</td>
        <td>${timestamp}</td>
    </tr>
    <tr>
        <td>Error</td>
        <td>${error}</td>
    </tr>
    <tr>
        <td>Status</td>
        <td>${status}</td>
    </tr>
    <tr>
        <td>Message</td>
        <td>${message}</td>
    </tr>
    <tr>
        <td>Exception</td>
        <td>${exception}</td>
    </tr>
    <tr>
        <td>Trace</td>
        <td>
            <pre>${trace}</pre>
        </td>
    </tr>
    <tr>
        <td>Session Variables</td>
        <td>
            <%
                java.util.Enumeration<String> sessEnum = request.getSession()
                        .getAttributeNames();
                while (sessEnum.hasMoreElements()) {
                    String s = sessEnum.nextElement();
                    out.print(s);
                    out.println("==" + request.getSession().getAttribute(s));
            %><br />
            <%
                }
            %>
        </td>
    </tr>
</table>
</body>
</html>