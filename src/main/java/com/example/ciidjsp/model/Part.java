package com.example.ciidjsp.model;

public class Part {
    private int id;
    private String name;
    private int personId;
    private int price;
    private String description;
    private int quantityInStock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int supplierId) {
        this.personId = supplierId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Part(String name, int personId, int price, String description, int quantityInStock) {
        this.name = name;
        this.personId = personId;
        this.price = price;
        this.description = description;
        this.quantityInStock = quantityInStock;
    }

    public Part(int id, String name, int personId, int price, String description, int quantityInStock) {
        this.id = id;
        this.name = name;
        this.personId = personId;
        this.price = price;
        this.description = description;
        this.quantityInStock = quantityInStock;
    }
}
