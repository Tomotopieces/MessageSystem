package io.tomoto.model.entity;

import java.util.Objects;

/**
 * @author Tomoto
 * <p>
 * 2020/11/26 15:00
 */
public class Message {
    private Integer id;
    private Integer fromUid;
    private Integer toUid;
    private String title;
    private String content;
    private Boolean read;
    private String createTime;

    public Message() {
    }

    public Message(Integer id, Integer fromUid, Integer toUid, String title, String content, Boolean read, String createTime) {
        this.id = id;
        this.fromUid = fromUid;
        this.toUid = toUid;
        this.title = title;
        this.content = content;
        this.read = read;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromUid=" + fromUid +
                ", toUid=" + toUid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", read=" + read +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(fromUid, message.fromUid) &&
                Objects.equals(toUid, message.toUid) &&
                Objects.equals(title, message.title) &&
                Objects.equals(content, message.content) &&
                Objects.equals(read, message.read) &&
                Objects.equals(createTime, message.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromUid, toUid, title, content, read, createTime);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public Integer getToUid() {
        return toUid;
    }

    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
