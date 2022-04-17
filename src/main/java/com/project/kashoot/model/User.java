package com.project.kashoot.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    @Column(name = "user_name", nullable = false)
    private String user_name;

    @Column(name = "user_password", nullable = false)
    private String user_password;

    @Column(name = "user_isAdmin", nullable = false)
    private boolean user_isAdmin;

    public User() {
        super();
    }

    public User(String user_name, String user_password, boolean user_isAdmin) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_isAdmin = user_isAdmin;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public boolean isUser_isAdmin() {
        return user_isAdmin;
    }

    public void setUser_isAdmin(boolean user_isAdmin) {
        this.user_isAdmin = user_isAdmin;
    }

    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", user_name=" + user_name + ", user_password=" + user_password + ", user_isAdmin=" + user_isAdmin + "]";
    }
}
