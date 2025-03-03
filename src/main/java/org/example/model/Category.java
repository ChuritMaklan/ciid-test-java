package org.example.model;

public class Category {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int categoryId) {
        this.id = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(int categoryId, String name) {
        this.id = categoryId;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }
}
