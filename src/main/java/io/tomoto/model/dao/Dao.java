package io.tomoto.model.dao;

import io.tomoto.model.entity.User;

import java.util.List;

/**
 * @author Tomoto
 * <p>
 * 2020/11/26 15:02
 */
public interface Dao<T, ID> {
    Integer create(T t);

    Integer delete(ID id);

    T read(ID id);

    List<T> readAll();

    <P> Integer update(ID id, String propertyName, P propertyValue);
}
