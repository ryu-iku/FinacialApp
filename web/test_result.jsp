<%-- 
    Document   : test_result
    Created on : 2016/09/05, 20:48:44
    Author     : y.liu
--%>

<%@page import="source.TestResult"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            TestResult result=(TestResult)request.getAttribute("RESULT");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            out.print("start!!");
            if (result!=null){
                out.print("<h1>the strategy scenerio is --brand:"+result.getBrand()+"</h1>");
                out.print("<h1>the test result is --price:"+result.getPrice()+"</h1>");
            }
        %>
        <h1>under the strategy of your scenerio, you would make % profit in a month</h1>
        <h2>scenerio graph</h2>
        <h2>know more about this condition-action</h2>
        <h2>how about <a href="./codition_set.jsp">change this condition-action</a>?</h2>
        </br>
        <h1>suggestion</h1>
        <h2>buy this</h2>
        <h3>OK, <a href="./record_action.jsp">bought it</a></h3>
        <h2>sell this</h2>
        <h3>OK, <a href="./record_action.jsp">sold it</a></h3>
        <br>
        <h1><a href="./strategy_history.jsp">save this scenerio to the history</a></h1>
    </body>
</html>
