<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP pavilion
  Date: 14.10.2017
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All book</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="../resources/css/listTable.css"/>"/>
</head>
<tbody>

<div class="header">
    Info Book.
</div>

<table class="table table-hover">
    <thead>
        <tr>
            <th>id</th>
            <th>Title</th>
            <th>Author</th>
            <th>print year</th>
            <th>read already</th>
            <th width="100"></th>
            <th width="100">

                <button class="button headerButton">
                    <a href='<c:url value="/books/addBook"/>'>New book</a>
                </button>


            </th>
            <th width="100">

                <button class="button headerButton">
                    <a href='<c:url value="/books/findBookView"/>'>Find book</a>
                </button>

            </th>
        </tr>
    </thead>

    <tbody>
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
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


                <td>
                    <button class="button buttonInfo">
                        <a href="<c:url value="/books/infoBook/${book.id}"/>"> Info</a>
                    </button>
                </td>
                <td>
                    <button class="button buttonEdit">
                        <a href="<c:url value="/books/editBook/${book.id}"/>"> Edit</a>
                    </button>
                </td>
                <td>
                    <button class="button buttonDelete">
                        <a href="<c:url value="/books/deleteBook/${book.id}?page=${page}"/>"> Delete</a>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</br>
<div class="center">
    <div class="pagination">
        <c:url value="/books/list" var="prev">
            <c:param name="page" value="${page-1}"/>
        </c:url>
        <c:choose>
            <c:when test="${page > 1}">
                <a href='<c:out value="${prev}" />' class="previous round">&laquo;</a>
            </c:when>
            <c:otherwise>
                <a href='#' class="previous round">&laquo;</a>
            </c:otherwise>
        </c:choose>

        <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
            <c:choose>
                <c:when test="${page == i.index}">
                    <span class="active">${i.index}</span>
                </c:when>
                <c:otherwise>
                    <c:url value="/books/list" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <a href='<c:out value="${url}" />'>${i.index}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:url value="/books/list" var="next">
            <c:param name="page" value="${page + 1}"/>
        </c:url>
        <c:choose>
            <c:when test="${page + 1 <= maxPages}">
                <a href='<c:out value="${next}" />' class="next round">&raquo;</a>
            </c:when>
            <c:otherwise>
                <a href='#' class="next round">&raquo;</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>

</tbody>
</html>
