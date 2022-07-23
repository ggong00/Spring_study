<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/jsp/members/save.jsp" method="post" >
    <label>이름</label>
    <input type="text" name="username">
    <label>나이</label>
    <input type="text" name="age">
    <button type="submit">전송</button>
</form>

</body>
</html>
