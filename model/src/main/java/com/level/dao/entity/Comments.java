package com.level.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comments implements Comparable {
    private long idComment;
    private String date;
    private String text;
    private User user;

    public Comments() {
        text = null;
    }

    public Comments(String date, String text, User user) {
        this.date = date;
        this.text = text;
        this.user = user;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_comments", unique = true)
    public long getIdComment() {
        return idComment;
    }

    public void setIdComment(long idComment) {
        this.idComment = idComment;
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int compareTo(Object o) {
        Comments comments = (Comments) o;
        return Long.compare(this.getIdComment(), comments.getIdComment());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Comments comments = (Comments) object;

        if (idComment != comments.idComment) return false;
        if (date != null ? !date.equals(comments.date) : comments.date != null) return false;
        if (text != null ? !text.equals(comments.text) : comments.text != null) return false;
        return user != null ? user.equals(comments.user) : comments.user == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idComment ^ (idComment >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}