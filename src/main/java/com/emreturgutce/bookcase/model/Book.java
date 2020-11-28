package com.emreturgutce.bookcase.model;

public class Book {
    private String id;
    private String name;
    private String author_id;
    private final String created_at;
    private final String updated_at;

    public Book(String id, String name, String author_id, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.author_id = author_id;
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

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
