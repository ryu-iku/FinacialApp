<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FinancilApp - Error</title>
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
        
        エラーが発生しました。以下の項目を確認してください。<br>
        <%=request.getAttribute("error")%><br><br>
    </body>
</html>
