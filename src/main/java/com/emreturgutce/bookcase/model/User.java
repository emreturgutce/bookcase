package com.emreturgutce.bookcase.model;

import java.sql.Timestamp;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private final Long created_at;
    private final Long updated_at;

    public User(String id, String name, String email, String password, long created_at, long updated_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public Long getUpdated_at() {
        return updated_at;
    }
}
