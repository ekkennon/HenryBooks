<%-- 
    Document   : StoreSelection
    Created on : Apr 11, 2017, 5:07:23 PM
    Author     : ekk
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script src="ajax.js" type="text/javascript"></script>
<script src="inventory.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
        function pageAction()
        {
            var sl = document.getElementById("storeid");
            var storeid = sl.options[sl.selectedIndex].value;
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

            <form action="StoreSelection" method="post"  id="selection" >
                Stores:<br/>
                <select id="storeid" name="storeid">
                    <c:forEach var="s" items="${ stores }">
                        <option ${ s.storeid == user.storeid ? "selected" : "" } value="${ s.storeid }">${ s.storeName }</option>
                    </c:forEach>
                </select>
            </form>
            <input type="submit" onclick="pageAction()" value="View"/>
            
            <div id="inventory"></div>
            
            <p>${ msg }</p>
        </body>
</html>
