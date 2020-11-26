package io.tomoto.model.dao.impl;

import io.tomoto.model.dao.Dao;
import io.tomoto.model.entity.User;
import io.tomoto.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao class for user table.
 * <p>
 * Singleton.
 *
 * @author Tomoto
 * <p>
 * 2020/11/26 15:09
 */
public class UserDao implements Dao<User, Integer> {
    private static final String CREATE_STATEMENT =
            "INSERT INTO `user`(`username`, `password`, `email`) VALUES (?, ?, ?);";
    private static final String DELETE_STATEMENT =
            "DELETE FROM `user` WHERE `id` = ?";
    private static final String READ_STATEMENT =
            "SELECT `id`, `username`, `password`, `email` FROM `user` WHERE `id` = ?;";
    private static final String READ_BY_USERNAME_STATEMENT =
            "SELECT `id`, `username`, `password`, `email` FROM `user` WHERE `username` = ?;";
    private static final String READ_ALL_STATEMENT =
            "SELECT `id`, `username`, `password`, `email` FROM `user`;";
    private static final String UPDATE_STATEMENT =
            "UPDATE `user` SET ? = ? WHERE `id` = ?;";

    private final QueryRunner runner = new QueryRunner();

    private UserDao() {
    }

    public static UserDao getInstance() {
        return Instance.INSTANCE;
    }

    @Override
    public Integer create(User user) {
        try (Connection connection = JdbcUtils.getConnection()) {
            return runner.update(connection, CREATE_STATEMENT, user.getUsername(), user.getPassword(), user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        try (Connection connection = JdbcUtils.getConnection()) {
            return runner.update(connection, DELETE_STATEMENT, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User read(Integer id) {
        try (Connection connection = JdbcUtils.getConnection()) {
            return runner.query(connection, READ_STATEMENT, new BeanHandler<>(User.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User readByUsername(String username) {
        try (Connection connection = JdbcUtils.getConnection()) {
            return runner.query(connection, READ_BY_USERNAME_STATEMENT, new BeanHandler<>(User.class), username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> readAll() {
        try (Connection connection = JdbcUtils.getConnection()) {
            return runner.query(connection, READ_ALL_STATEMENT, new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <P> Integer update(Integer id, String propertyName, P propertyValue) {
        try (Connection connection = JdbcUtils.getConnection()) {
            return runner.update(connection,
                    "UPDATE `user` SET `" + propertyName + "` = ? WHERE `id` = ?;", propertyValue, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class Instance {
        public static final UserDao INSTANCE = new UserDao();
    }
}
