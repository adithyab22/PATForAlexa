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
        <h1>Enter Source </h1>
        <form action="MapsService" method="GET"> 
            <input type="text" name="source" value="" /><br><br>
            <h1>Enter Destination</h1><br>
            <input type="text" name="destination" value="" /><br><br>
            <input type="submit" value="Search" /><br><br>
        </form>
    </body>
</html>
