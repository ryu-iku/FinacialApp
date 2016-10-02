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
        <title>JSP Page</title>
    </head>
    <body>
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
