package io.tomoto.model.dao.impl;

import io.tomoto.model.dao.Dao;
import io.tomoto.model.entity.Message;
import io.tomoto.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao class for message table.
 * <p>
 * Singleton.
 *
 * @author Tomoto
 * <p>
 * 2020/11/26 15:31
 */
public class MessageDao implements Dao<Message, Integer> {
    private static final String CREATE_STATEMENT =
            "INSERT INTO `message`(`fromUid`, `toUid`, `title`, `content`, `read`, `createTime`) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String DELETE_STATEMENT =
            "DELETE FROM `message` WHERE `id` = ?";
    private static final String READ_STATEMENT =
            "SELECT `id`, `fromUid`, `toUid`, `title`, `content`, `read`, `createTime` FROM `message` WHERE `id` = ?;";
    private static final String READ_BY_USER_ID_STATEMENT =
            "SELECT `id`, `fromUid`, `toUid`, `title`, `content`, `read`, `createTime` FROM `message` WHERE `toUid` = ?;";
    private static final String READ_ALL_STATEMENT =
            "SELECT `id`, `fromUid`, `toUid`, `title`, `content`, `read`, `createTime` FROM `message`;";
    private static final String UPDATE_STATEMENT =
            "UPDATE `message` SET ? = ? WHERE `id` = ?;";

    private final QueryRunner runner = new QueryRunner();

    private MessageDao() {
    }

    public static MessageDao getInstance() {
        return Instance.INSTANCE;
    }

    @Override
    public Integer create(Message message) {
        try (Connection connection = JdbcUtil.getConnection()) {
            return runner.update(connection, CREATE_STATEMENT,
                    message.getFromUid(), message.getToUid(), message.getTitle(), message.getContent(),
                    message.getRead(), message.getCreateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        try (Connection connection = JdbcUtil.getConnection()) {
            return runner.update(connection, DELETE_STATEMENT, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Message read(Integer id) {
        try (Connection connection = JdbcUtil.getConnection()) {
            return runner.query(connection, READ_STATEMENT, new BeanHandler<>(Message.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Message> readByUserId(Integer userId) {
        try (Connection connection = JdbcUtil.getConnection()) {
            return runner.query(connection, READ_BY_USER_ID_STATEMENT, new BeanListHandler<>(Message.class), userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Message> readAll() {
        try (Connection connection = JdbcUtil.getConnection()) {
            return runner.query(connection, READ_ALL_STATEMENT, new BeanListHandler<>(Message.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <P> Integer update(Integer id, String propertyName, P propertyValue) {
        try (Connection connection = JdbcUtil.getConnection()) {
            return runner.update(connection,
                    "UPDATE `message` SET `" + propertyName + "` = ? WHERE `id` = ?;", propertyValue, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class Instance {
        public static final MessageDao INSTANCE = new MessageDao();
    }
}
