<%-- 
    Document   : ViewInventory
    Created on : Apr 16, 2017, 4:39:46 PM
    Author     : ekk
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory</title>
    </head>
    <body>
        <p>Branch #: ${ store.storeid } </p>
        <p>Branch Name: ${ store.storeName } </p>
        <p>Branch Location: ${ store.storeAddress } </p>
        <c:if test="${user.adminLevel eq 'Admn'}">
            <form action="ViewInventory" method="post">
                <input name="bookid" id="bookid" placeholder="Book Code"/>
                <input hidden name="storeid" id="storeid" value="${ store.storeid }" />
                <button>Edit Record</button>
            </form>
            <br/>
        </c:if>
    
        <table border="1">
            <tr>
                <th bgcolor="D5F2F2">
                    Store
                </th>
                <th bgcolor="D5F2F2">
                    Book Code
                </th>
                <th bgcolor="D5F2F2">
                    Title
                </th>
                <th bgcolor="D5F2F2">
                    Retail Price
                </th>
                <th bgcolor="D5F2F2">
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
        <p class="error">${msg}</p>
    </body>
</html>
