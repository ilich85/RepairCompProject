package com.level.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class Services implements Comparable{
    private long idService;
    private String description;
    private int price;

    public Services() {
        this.description = null;
    }

    public Services( String description, int price) {
        this.description = description;
        this.price = price;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_order", unique = true)
    public long getIdService() {
        return idService;
    }

    public void setIdService(long idService) {
        this.idService = idService;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Services services = (Services) object;

        if (idService != services.idService) return false;
        if (price != services.price) return false;
        return description != null ? description.equals(services.description) : services.description == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idService ^ (idService >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }

    @Override
    public int compareTo(Object o) {
        Services services = (Services) o;
        return Long.compare(this.getIdService(), services.getIdService());
    }
}