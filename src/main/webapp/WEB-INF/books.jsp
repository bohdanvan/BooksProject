<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
  <title>Books</title>
  <link rel="stylesheet" href="https://cdn.rawgit.com/twbs/bootstrap/v4-dev/dist/css/bootstrap.css">
</head>
<body>
<div class="container" id="table">
  <h2 style="text-align: center">Book List</h2>
  <form class="form-inline" role="form" method="get">
    <input type="submit" class="btn btn-primary" value="Add New Record"
            formaction="/web/book/save/form">
  </form>
  <form class="form-inline" role="form">

    <c:choose>
      <c:when test="${empty books}">
        No Records
      </c:when>
      <c:otherwise>
        <table class="table table-striped" id="bookTable">
          <thead>
          <tr>
            <td><b>ISBN</b></td>
            <td><b>Author</b></td>
            <td><b>Title</b></td>
          </tr>
          </thead>
          <c:forEach items="${books}" var="books">
            <tr>
              <td><b>${books.isbn}</b></td>
              <td>${books.author}</td>
              <td>${books.title}</td>
            </tr>
          </c:forEach>

        </table>
      </c:otherwise>
    </c:choose>

  </form>
</div>
</body>
</html>
