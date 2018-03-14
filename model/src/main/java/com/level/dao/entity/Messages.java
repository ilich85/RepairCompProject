package com.level.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Messages implements Comparable {
    private long idMessage;
    private String date;
    private String text;

    public Messages() {
        text = null;
    }

    public Messages(String date, String text) {
        this.date = date;
        this.text = text;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_messages", unique = true)
    public long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(long idMessage) {
        this.idMessage = idMessage;
    }

    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String dateComment) {
        this.date = dateComment;
    }

    @Column(name = "text", length = 500)
    public String getText() {
        return text;
    }

    public void setText(String commentText) {
        this.text = commentText;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Messages messages = (Messages) object;

        if (idMessage != messages.idMessage) return false;
        if (date != null ? !date.equals(messages.date) : messages.date != null) return false;
        return text != null ? text.equals(messages.text) : messages.text == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idMessage ^ (idMessage >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        Messages services = (Messages) o;
        return Long.compare(this.getIdMessage(), services.getIdMessage());
    }
}