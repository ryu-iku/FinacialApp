<%@page import="source.ShowSimulationResult" %>
<%@page import="storage.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            StrategyDTO strategyDTO=(StrategyDTO)request.getAttribute("STRATEGY");
            DailyActionProfitDTO profitDTO=(DailyActionProfitDTO)request.getAttribute("PROFIT");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" >
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <link rel="icon" href="img/favicon.ico">
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {packages: ['corechart', 'bar']});
            google.charts.setOnLoadCallback(drawChart);
            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                  ['Element', 'Profit', { role: 'style' }],
                  [<%= profitDTO.getBrandCode()%>, <%=profitDTO.getProfit() %>, 'silver'],            // English color name
               ]);
               
               var options={};
               var chart=new google.visualization.BarChart(document.getElementById('chart_div'));
               chart.draw(data, options);
            }
        </script>
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
        
        <div class="container">
            <%
                if (strategyDTO!=null){
                    out.print("<h1>According to No."+strategyDTO.getStrategyId()+" strategy:</h1>");
                }
            %>
            <p>brand code: <%= profitDTO.getBrandCode()%></p>
            <p>profit: <%= profitDTO.getProfit()%></p>
            <div id="chart_div"></div>
            
            <h2><a href="./make_strategy.jsp">click here to make another strategy</a></h2>
            <h2><a href="./strategy_history.jsp">check more strategies</a></h2>
        </div>
        
    </body>
</html>
