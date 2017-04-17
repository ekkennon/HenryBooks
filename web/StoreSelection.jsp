<%-- 
    Document   : StoreSelection
    Created on : Apr 11, 2017, 5:07:23 PM
    Author     : raefo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script src="ajax.js" type="text/javascript"></script>
<script src="inventory.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
        function pageAction(storeid)
        {
            if (ajax) {
                ajax.open('get','StoreSelection?storeid='+storeid);
                ajax.send(null);
            } else {
                document.selection.submit();
            }
        }
    </script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Selection</title>
    </head>
        <body>
            <h1>Select Store for Inventory:</h1>
            <p>${ user.userid } - ${ user.username }, ${ user.adminLevel } Level</p>

            <form action="StoreSelection" id="selection" method="post">
                Stores:<br/>
                <select id="storeid" name="storeid">
                    <c:forEach var="store" items="${ stores }">
                        <option onclick="pageAction(${ store.storeid })" ${ store.storeid == user.storeid ? "selected" : "" } value="${ store.storeid }">${ store.storeName }</option>
                    </c:forEach>
                </select>
            </form>
            
            <div id="inventory"></div>
            
            <p>${ msg }</p>
        </body>
</html>
