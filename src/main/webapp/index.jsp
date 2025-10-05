<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>The SQL Gateway</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css">
</head>
<body>
<div class="container">
    <form class="card" action="execute" method="post">
        <h1>The SQL Gateway</h1>
        <h2>Enter an SQL statement</h2>

        <div class="form-group">
            <label for="statement">SQL Statement</label>
            <textarea id="statement" name="statement" placeholder="VD: SELECT * FROM users; hoặc SELECT * FROM products WHERE price > 10000000;" required><c:out value="${statement}" /></textarea>
        </div>

        <div class="actions">
            <button type="submit" class="btn">Execute</button>
        </div>
        
        <p class="version-text">oanh-version</p>
    </form>
    
    <div class="result-container">
        <p><b>SQL result:</b></p>
        <c:if test="${not empty result}">
            <c:out value="${result}" escapeXml="false" />
        </c:if>
    </div>
</div>
</body>
</html>