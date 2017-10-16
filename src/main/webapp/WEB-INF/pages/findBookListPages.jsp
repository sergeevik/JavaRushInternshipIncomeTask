<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP pavilion
  Date: 16.10.2017
  Time: 5:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find List</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="../resources/css/listTable.css"/>"/>
</head>
<body>
<table class="table table-hover">
    <thead>
    <tr>
        <th>id</th>
        <th>Title</th>
        <th>Author</th>
        <th>Description</th>
        <th>print year</th>
        <th>read already</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.description}</td>
            <td>${book.printYear}</td>
            <td>
                <c:choose>
                    <c:when test="${book.readAlready == true}">
                        <img src='<c:url value="../resources/image/Y.png"/>'/>
                    </c:when>
                    <c:otherwise>
                        <img src='<c:url value="../resources/image/X.png"/>'/>
                    </c:otherwise>
                </c:choose>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
