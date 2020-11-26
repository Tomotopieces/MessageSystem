package io.tomoto.model.service.impl;

import io.tomoto.model.dao.impl.MessageDao;
import io.tomoto.model.entity.Message;
import io.tomoto.model.service.Service;

import java.util.List;

/**
 * @author Tomoto
 * <p>
 * 2020/11/26 20:09
 */
public class MessageService implements Service {
    private final MessageDao messageDao = MessageDao.getInstance();

    private MessageService() {
    }

    public static MessageService getInstance() {
        return Instance.INSTANCE;
    }

    public Message getMessage(Integer id) {
        return messageDao.read(id);
    }

    public List<Message> getMessages() {
        return messageDao.readAll();
    }

    public List<Message> getUserMessages(Integer userId) {
        return messageDao.readByUserId(userId);
    }

    public void setMessageRead(Integer id, Boolean state) {
        messageDao.update(id, "read", state);
    }

    private static class Instance {
        public static final MessageService INSTANCE = new MessageService();
    }
}
