<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ page import="io.tomoto.model.entity.Message" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>邮箱空间</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <link rel="stylesheet" href="css/style.css">
    <style>
        #userBar {
            margin-left: 35px;
            text-align: left;
        }

        #emails {
            margin-left: 30px;
            text-align: left;
        }

        .unreadEmail {
            list-style-image: url('resources/image/UnreadLetter.png');
        }

        .readEmail {
            list-style-image: url('resources/image/ReadLetter.png');
        }

        .emailTitle {
            text-decoration: none;
            font-size: 20px;
            font-weight: bold;
        }

        .emailTitle:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>
    <div id="mainContent">
        <br><hr><br>

        <h1>邮箱空间</h1>

        <br><hr><br>

        <div id="userBar">
            您好，${username}。
            <br><br>
            <button onclick="window.location.href='/MessageSystem/user.do?behavior=logout'">注销</button>
        </div>

        <br><hr><br>

        <ul id="emails">
            <%
                List<Message> emails = (List<Message>) request.getAttribute("emails");
                if (emails != null) {
                    for (Message email : emails) {
                        if (email.getRead()) {
                            out.print("<li class='readEmail'>");
                        } else {
                            out.print("<li class='unreadEmail'>");
                        }

                        out.print("<span ");
                        out.print("id='" + email.getId() + "'");
                        out.print("class='emailTitle'>");
                        out.print(email.getTitle());
                        out.print("</span>");

                        out.print("</li>");
                    }
                }
            %>
        </ul>

        <br><hr><br>
    </div>

    <script>
        $(() => {
            $('.emailTitle').on('click', function() {
                let id = $(this).attr('id');
                window.location.replace("/MessageSystem/message.do?behavior=read&id=" + id);
            });
        });
    </script>
</body>

</html>