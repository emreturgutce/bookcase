package com.emreturgutce.bookcase.model;

public class Users_BooksModel {
    private String id;
    private String user_id;
    private String book_id;
    private final String created_at;
    private final String updated_at;

    public Users_BooksModel(String id, String user_id, String book_id, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
