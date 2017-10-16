<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP pavilion
  Date: 15.10.2017
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info book</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="../../resources/css/info.css"/>"/>


</head>
<body>
<div class="header">
    List Books.
</div>
<table>
    <tbody>
        <tr>
            <td>
                <label>Автор</label>
            </td>
            <td>
                <label class="data">${book.author}</label>
            </td>
        </tr>

        <tr>
            <td>
                <label>Название</label>
            </td>
            <td>
                <label class="data">${book.title}</label>
            </td>
        </tr>

        <tr>
            <td>
                <label>ISBN номер</label>
            </td>
            <td>
                <label class="data">${book.isbn}</label>
            </td>
        </tr>

        <tr>
            <td>
                <label>Описание</label>
            </td>
            <td>
                <label class="data">${book.description}</label>
            </td>
        </tr>

        <tr>
            <td>
                <label>Прочитана?</label>
            </td>
            <td>
                <label class="data">${book.readAlready}</label>
            </td>
        </tr>
    </tbody>

</table>

</br>
<button class="button">
    <a href="<c:url value="/books/list"/>"> return to main </a>
</button>
</body>
</html>
