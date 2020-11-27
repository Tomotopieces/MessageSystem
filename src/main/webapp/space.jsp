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
            vertical-align: top;
            text-align: left;
        }

        #avatar {
            float: left;
            margin-right: 30px;
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
        }

        .emailTitle:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>
    <div id="mainContent">
        <br>
        <hr><br>

        <h1>邮箱空间</h1>

        <br>
        <hr><br>

        <div id="userBar">
            <img id="avatar" width="64px" src="${avatar}" alt="avatar">
            您好，${username}。
            <button onclick="window.location.href='/MessageSystem/user.do?behavior=logout'">注销</button>
            <form style="text-align: left;" action="user.do?behavior=uploadAvatar" method="POST" enctype="multipart/form-data">
                上传头像: <input type="file" name="file"><br>
                <input type="submit" value="上传">
                <input id="downloadButton" type="button" value="下载">
            </form>
            <!-- <button onclick="window.location.href='/MessageSystem/user.do?behavior=downloadAvatar'">下载</button> -->
        </div>

        <br>
        <hr><br>

        <ul id="emails">
            <%
                List<Message> emails = (List<Message>) request.getSession().getAttribute("emails");
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

        <br>
        <hr><br>
    </div>

    <script>
        $(() => {
            $('.emailTitle').on('click', function () {
                let id = $(this).attr('id');
                window.location.replace("/MessageSystem/message.do?behavior=read&id=" + id);
            });

            $('#downloadButton').on('click', () => {
                window.location.href='/MessageSystem/user.do?behavior=downloadAvatar';
            });
        });
    </script>
</body>

</html>