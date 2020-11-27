package io.tomoto.control.servlet.impl;

import io.tomoto.control.servlet.BaseServlet;
import io.tomoto.model.entity.User;
import io.tomoto.model.service.impl.UserService;
import io.tomoto.util.FileUtil;

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

    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getSession().getAttribute(KAPTCHA_SESSION_KEY).toString();
        if (!token.equals(request.getParameter("verify"))) {
            response.sendRedirect("/MessageSystem/wrongVerifyCode.html");
            return;
        }

        User user = new User(
                request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("email"));
        userService.addUser(user);
        response.sendRedirect("/MessageSystem/registerSuccessful.html");
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        // save user info to session
        request.getSession().setAttribute("username", username);
        request.getSession().setAttribute("userId", user.getId());
        request.getSession().setAttribute("avatar", user.getAvatarPath().substring(1));

        response.sendRedirect("/MessageSystem/message.do?behavior=space");
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/MessageSystem/logout.html");
    }

    public void downloadAvatar(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html; charset=utf-8");
            request.setCharacterEncoding("utf-8");

            Integer userId = getUserId(request);
            String filePath = request.getServletContext().getRealPath(userService.getUser(userId).getAvatarPath());
            FileUtil.download(request, response, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadAvatar(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        // update database, session and local disk avatar info
        Integer userId = getUserId(request);
        String avatarPath = "/resources/image/avatar/" + userId + "_avatar.png";
        String fullPath = request.getServletContext().getRealPath(avatarPath);
        userService.setUserAvatar(userId, avatarPath);
        request.getSession().setAttribute("avatar", avatarPath.substring(1));
        try {
            FileUtil.upload(request, response, fullPath);
            response.sendRedirect("/MessageSystem/message.do?behavior=space");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        List<User> users = userService.getUsers();
        request.setAttribute("users", users);
        try {
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets user id in session.
     *
     * @param request a request
     * @return current user id
     */
    private Integer getUserId(HttpServletRequest request) {
        return Integer.parseInt(request.getSession().getAttribute("userId").toString());
    }
}
