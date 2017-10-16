<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP pavilion
  Date: 16.10.2017
  Time: 4:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find book</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="../resources/css/add-edit-find.css"/>"/>
</head>
<body>

<div class="header">
    List Finds Books.
    </button>
</div>


<c:url value="/books/findBookList" var="findBookAction"/>

<spring:form modelAttribute="findBook" action="${findBookAction}" method="post">

    <table>
        <thead>
            <tr>
                <td width = "10%"></td>
                <td width = "90%"></td>
            </tr>
        </thead>
        <tbody>

        <tr>
            <td>
                <label>Автор:</label>
            </td>
            <td>
                <spring:input path="author" cssClass="inputData"/>
            </td>
        </tr>
        <tr>
            <td>
                <label>Название:</label>
            </td>
            <td>
                <spring:input path="title" cssClass="inputData"/>
            </td>
        </tr>

        <tr>
            <td>
                <label>ISBN:</label>
            </td>
            <td>
                <spring:input path="isbn" type="number" cssClass="inputData"/>
            </td>
        </tr>

        <tr>
            <td>
                <label>Год:</label>
            </td>
            <td>
                <spring:input path="yearBefore" type="number" cssClass="inputYear"/>
                -
                <spring:input path="yearAfter" type="number" cssClass="inputYear"/>
            </td>
        </tr>

        <tr>
            <td>
                <label>Прочитана?</label>
            </td>
            <td>
                <spring:select path="readAlready">
                    <spring:option value="1">"Да"</spring:option>
                    <spring:option value="-1">"Нет"</spring:option>
                    <spring:option value="0">"Не важно"</spring:option>
                </spring:select>
            </td>
        </tr>

        </tbody>


    </table>

    <spring:button> Поиск </spring:button>

</spring:form>


</body>
</html>
