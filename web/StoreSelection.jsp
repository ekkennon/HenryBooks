<%-- 
    Document   : StoreSelection
    Created on : Apr 11, 2017, 5:07:23 PM
    Author     : raefo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Selection</title>
    </head>
    <body>
        <h1>Select Store for Inventory:</h1>
        <p>${ user.userid } - ${ user.username }, ${ user.adminLevel } Level</p>
        
        <form action="" id="selection" method="post">
            Stores:<br/>
            <select id="storeid" name="storeid">
                <c:forEach var="store" items="${ stores }">
                    <option ${ store.storeid == user.storeid ? "selected" : "" } value="${ store.storeid }">${ store.storeName }</option>
                </c:forEach>
            </select>
        </form>
        
        <p>${ msg }</p>
    </body>
</html>
