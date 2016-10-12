<%@page import="source.DailyActionProfitDTO"
        import="java.util.ArrayList" %>

<%
    ArrayList<DailyActionProfitDTO> dap=new ArrayList<DailyActionProfitDTO>() ;
    dap=(ArrayList<DailyActionProfitDTO>)request.getAttribute("RESULT");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FinancilApp - Show profit</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" >
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <link rel="icon" href="img/favicon.ico">
    </head>
    
    <body>
        <nav class="navbar navbar-static-top navbar-dark bg-inverse">
            <a class="navbar-brand" href="./index.jsp">FinAPP</a>
            <ul class="nav navbar-nav">
              <li class="nav-item">
                <a class="nav-link" href="#">my potofolio <span class="sr-only">(current)</span></a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./strategy_history.jsp">my strategy</a>
              </li>
            </ul>
        </nav>
        
        <%
        out.println("<tr>");
        for(int i=1;i<dap.size();i++){
            out.println("<td align='right' width='20'>" + dap.get(i).getDate() + "</td>");
            out.println("<td align='right' width='20'>" + dap.get(i).getAction() + "</td>");
            out.println("<td align='right' width='20'>" + dap.get(i).getProfit() + "</td>");
            out.println("<br>");
        }
        out.println("</tr>");
        %>
    </body>
</html>
