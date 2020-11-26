package io.tomoto.control.servlet.impl;

import io.tomoto.control.servlet.BaseServlet;
import io.tomoto.model.entity.Message;
import io.tomoto.model.entity.User;
import io.tomoto.model.service.impl.MessageService;
import io.tomoto.model.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author Tomoto
 * <p>
 * 2020/11/26 15:58
 */
@WebServlet("/user.do")
public class UserServlet extends BaseServlet {
    private final UserService userService = UserService.getInstance();
    private final MessageService messageService = MessageService.getInstance();

    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getSession().getAttribute(KAPTCHA_SESSION_KEY).toString();
        if (token.equals(request.getParameter("verify"))) {
            response.sendRedirect("/MessageSystem/wrongVerifyCode.html");
            return;
        }

        User user = new User(0,
                request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("email"));
        userService.addUser(user);
        response.sendRedirect("/MessageSystem/registerSuccessful.html");
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.getUser(username);

        if (user == null) {
            response.sendRedirect("/MessageSystem/userNotExist.html");
            return;
        } else if (!password.equals(user.getPassword())) {
            response.sendRedirect("/MessageSystem/wrongPassword.html");
            return;
        }

        request.getSession().setAttribute("username", username);

        List<Message> emails = messageService.getUserMessages(user.getId());
        request.setAttribute("emails", emails);

        request.getRequestDispatcher("/space.jsp").forward(request, response);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("/MessageSystem/logout.html");
    }
}
