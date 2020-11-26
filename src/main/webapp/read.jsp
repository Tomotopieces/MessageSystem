<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>

    <link rel="stylesheet" href="css/style.css">
    <style>
        .annotation {
            font-size: 10px;
            color: gray;
        }
    </style>
</head>

<body>
    <div id="mainContent">
        <br><hr><br>

        <h1>${title}</h1>

        <br>

        <span class="annotation">
            发件人: 
            ${sender}
        </span>
        &nbsp;&nbsp;&nbsp;
        <span class="annotation">
            收件人: 
            ${receiver}
        </span>

        <br>

        <span class="annotation">
            时间: 
            ${time}
        </span>
        <br>

        <br><hr><br>

        <span>${content}</span>
        <br>

        <br><hr><br>

        <div><button onclick="window.location.href='/MessageSystem/space.jsp';">返回邮箱空间</button></div>

        <br><hr><br>
    </div>
</body>

</html>