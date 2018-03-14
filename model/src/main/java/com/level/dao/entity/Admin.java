package com.level.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admin implements Comparable {
    private long idAdmin;
    private String adminName;
    private String email;
    private String password;

    public Admin() {
    }

    public Admin(String adminName, String email, String password) {
        this.adminName = adminName;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_admin", unique = true)
    public long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(long idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Column(name = "admin_name", length = 25, unique = true, nullable = false)
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }


    @Column(name = "email", length = 25, unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", length = 25, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Admin admin = (Admin) object;

        if (idAdmin != admin.idAdmin) return false;
        if (adminName != null ? !adminName.equals(admin.adminName) : admin.adminName != null) return false;
        if (email != null ? !email.equals(admin.email) : admin.email != null) return false;
        return password != null ? password.equals(admin.password) : admin.password == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idAdmin ^ (idAdmin >>> 32));
        result = 31 * result + (adminName != null ? adminName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        Admin admin = (Admin) o;
        return Long.compare(this.getIdAdmin(), admin.getIdAdmin());
    }
}