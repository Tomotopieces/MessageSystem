<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ page import="io.tomoto.model.entity.User" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户列表</title>

    <link rel="stylesheet" href="css/style.css">
    <style>
        #list {
            margin-left: 30px;
            text-align: left;
            vertical-align: middle;
        }
    </style>
</head>

<body>
    <div id="mainContent">
        <br><hr><br>
        <h1>用户列表</h1>
        <br><hr><br>
        <ul id="list">
            <%
                List<User> users = (List<User>) request.getAttribute("users");
                for (User user : users) {
                    out.println("<li>" +
                        "<img width='32px' src='" +
                        user.getAvatarPath().substring(1) +
                        "' alt='avatar'>" +
                        user.getUsername() +
                        "</li>");
                }
            %>
        </ul>
        <br><hr><br>
        <br>
        <div><button onclick="window.location.href='/MessageSystem';">返回登陆界面</button></div>
        <br><hr><br>
    </div>
</body>

</html>