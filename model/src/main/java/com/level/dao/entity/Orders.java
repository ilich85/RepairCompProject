package com.level.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Orders implements Comparable {
    private long idOrder;
    private String text;
    private String date;
    private User user;

    public Orders() {
        text = null;
    }

    public Orders(String text, String date, User user) {
        this.text = text;
        this.date = date;
        this.user = user;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_order", unique = true)
    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    @Column(name = "text", length = 500)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Orders orders = (Orders) object;

        if (idOrder != orders.idOrder) return false;
        if (text != null ? !text.equals(orders.text) : orders.text != null) return false;
        if (date != null ? !date.equals(orders.date) : orders.date != null) return false;
        return user != null ? user.equals(orders.user) : orders.user == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idOrder ^ (idOrder >>> 32));
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        Orders order = (Orders) o;
        return Long.compare(this.getIdOrder(), order.getIdOrder());
    }
}