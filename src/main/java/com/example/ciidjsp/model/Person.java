package com.example.ciidjsp.model;

public class Person {
    private int id;
    private String email;
    private String phone;
    private String name;
    private int typeId;

    public Person(String email, String phone, String name, int typeId) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.typeId = typeId;
    }

    public Person(int id, String email, String phone, String name, int typeId) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int clientId) {
        this.id = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Person(){}
}

