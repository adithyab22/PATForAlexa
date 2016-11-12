<%-- 
    Document   : prompt
    Created on : Nov 10, 2016, 4:25:08 PM
    Author     : Adithya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Enter Route Number</h1>
        <form action="TrackerService" method="GET"> 
            <input type="text" name="input" value="" /><br><br>
            <input type="submit" value="Search" /><br><br>
        </form>
    </body>
</html>
