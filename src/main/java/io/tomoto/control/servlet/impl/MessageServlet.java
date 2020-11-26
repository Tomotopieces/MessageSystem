package io.tomoto.control.servlet.impl;

import io.tomoto.control.servlet.BaseServlet;
import io.tomoto.model.entity.Message;
import io.tomoto.model.service.impl.MessageService;
import io.tomoto.model.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tomoto
 * <p>
 * 2020/11/26 21:46
 */
@WebServlet("/message.do")
public class MessageServlet extends BaseServlet {
    private final MessageService messageService = MessageService.getInstance();
    private final UserService userService = UserService.getInstance();

    public void read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Message message = messageService.getMessage(Integer.parseInt(id));
        messageService.setMessageRead(message.getId(), true);

        String sender = userService.getUser(message.getFromUid()).getUsername();
        String receiver = userService.getUser(message.getToUid()).getUsername();
        request.setAttribute("sender", sender);
        request.setAttribute("receiver", receiver);
        request.setAttribute("title", message.getTitle());
        request.setAttribute("time", message.getCreateTime());
        request.setAttribute("content", message.getContent());

        request.getRequestDispatcher("/read.jsp").forward(request, response);
    }
}
