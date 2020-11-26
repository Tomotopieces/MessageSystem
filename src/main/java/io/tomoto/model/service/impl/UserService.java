package io.tomoto.model.service.impl;

import io.tomoto.model.dao.impl.UserDao;
import io.tomoto.model.entity.User;
import io.tomoto.model.service.Service;

/**
 * @author Tomoto
 * <p>
 * 2020/11/26 15:46
 */
public class UserService implements Service {
    private final UserDao userDao = UserDao.getInstance();

    private UserService() {
    }

    public static UserService getInstance() {
        return Instance.INSTANCE;
    }

    public User getUser(Integer id) {
        return userDao.read(id);
    }

    public User getUser(String username) {
        return userDao.readByUsername(username);
    }

    public Boolean addUser(User user) {
        return userDao.create(user) == 1;
    }

    private static class Instance {
        public static final UserService INSTANCE = new UserService();
    }
}
