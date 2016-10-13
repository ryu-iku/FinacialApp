<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FinancilApp - Make strategy</title>
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
        
        <div class="container">
            <h1>Choose <strong>conditions</strong> of holding and selling!</h1>

            <p>(Based on daily price change:)</p>
            
            <div>
                <h2>price change rate for holding</h2>
                <div class="col-xs-6">
                    <label for="holding_max">Max:</label>
                    <input type="text" class="form-control" id="holding_max"  name="holding_max" value="1" placeholder="Enter price increasing rate">
                </div>
                <div class="col-xs-6">
                    <label for="holding_min">Min:</label>
                    <input type="text" class="form-control" id="holding_min" name="holding_min" placeholder="Enter price increasing rate">
                </div>
            </div>

            <div>
                <h2>price change rate for selling</h2>
                <div class="col-xs-6">
                    <label for="selling_max">Max:</label>
                    <input type="text" class="form-control" id="selling_max" name="selling_max" placeholder="Enter price increasing rate">
                </div>
                <div class="col-xs-6">
                    <label for="selling_min">Min:</label>
                    <input type="text" class="form-control" id="selling_min" name="selling_min" placeholder="Enter price increasing rate">
                </div>
            </div>
            
            <h2><a href="ConfirmStrategy">Finish!</a></h2>

        </div>
        

        
        
    </body>
</html>
