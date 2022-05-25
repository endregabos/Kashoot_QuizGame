package com.project.kashoot.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "user_password", nullable = false)
    private String userpassword;

    @Column(name = "user_is_admin", nullable = false)
    private boolean user_isAdmin;

    public User() {
        super();
    }

    public User(String username, String userpassword, boolean userisAdmin) {
        this.username = username;
        this.userpassword = userpassword;
        this.user_isAdmin = userisAdmin;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return username;
    }

    public void setUser_name(String user_name) {
        this.username = user_name;
    }

    public String getUser_password() {
        return userpassword;
    }

    public void setUser_password(String user_password) {
        this.userpassword = user_password;
    }

    public boolean isUser_isAdmin() {
        return user_isAdmin;
    }

    public void setUser_isAdmin(boolean user_isAdmin) {
        this.user_isAdmin = user_isAdmin;
    }

    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", user_name=" + username + ", user_password=" + userpassword + ", user_isAdmin=" + user_isAdmin + "]";
    }
}
