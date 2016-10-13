<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FinancilApp - Top</title>
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
        
        <div class="jumbotron">
            <div class="container" id="top-text">
                <h1 class="display-3"><a href="./make_strategy.jsp">Make strategy</a></h1>
                <p>Make your own strategy and check out the profit!</p>
                <p><a class="btn btn-primary btn-lg" href="./make_strategy.jsp" role="button">Make strategy now &raquo;</a></p>
            </div>
        </div>
        
        <div class="container">
          <!-- Example row of columns -->
          <div class="row">
            <div class="col-md-6">
              <h2>Sell Alarm!</h2>
              <p>There are oo sell alarms.</p>
              <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
            </div>
            <div class="col-md-6">
              <h2>Buy Alarm!</h2>
              <p>There are oo buy alarms.</p>
              <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
           </div>
          </div>

    </body>
</html>
