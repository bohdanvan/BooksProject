<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add New Record</title>
  <link rel="stylesheet" href="https://cdn.rawgit.com/twbs/bootstrap/v4-dev/dist/css/bootstrap.css">

  <link href="${booksCss}" rel="stylesheet">
  <script src="${booksCss}"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>
<body>
<h2 style="text-align: center">Add New Record</h2>
<div>
<form:form action="/web/book/save" modelAttribute="book" method="post">
  <label for="isbnInput">ISBN:</label>
  <form:input path="isbn" id="isbnInput"></form:input>
  <form:errors path="isbn" cssClass="error"></form:errors>
  <c:if test="${not empty bookExistsError}">
    Book with same ISBN already exists
  </c:if>
  <br />

  <label for="authorInput">Author:</label>
  <form:input path="author" id="authorInput"></form:input>
  <form:errors path="author" cssClass="error"></form:errors>
  <br />

  <label for="titleInput">Title:</label>
  <form:input path="title" id="titleInput"></form:input>
  <form:errors path="title" cssClass="error"></form:errors>
  <br />

  <input type="submit" value="Submit"/>
</form:form>
</div>

</body>
</html>