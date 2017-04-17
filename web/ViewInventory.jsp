<%-- 
    Document   : ViewInventory
    Created on : Apr 16, 2017, 4:39:46 PM
    Author     : raefo
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:if test="${user.adminLevel eq 'Admn'}">
            <input placeholder="Book Code"/>
            <button>Edit Record</button>
        </c:if>
    
        <table border="1">
            <tr>
                <th bgcolor="DBDAC1">
                    Store
                </th>
                <th bgcolor="DBDAC1">
                    Book Code
                </th>
                <th bgcolor="DBDAC1">
                    Title
                </th>
                <th bgcolor="DBDAC1">
                    Retail Price
                </th>
                <th bgcolor="DBDAC1">
                    Quantity
                </th>
            </tr>
            
            <c:forEach var="bk" items="${ booklist }">
                <tr>
                    <td align="right">
                        ${ bk.storeid }
                    </td>
                    <td align="right">
                        ${ bk.bookid }
                    </td>
                    <td align="right">
                        ${ bk.onhand }
                    </td>
                    <td align="right">
                        ${ bk.title }
                    </td>
                    <td align="right">
                        ${ bk.price }
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
