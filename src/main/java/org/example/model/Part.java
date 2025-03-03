package org.example.model;

public class Part {
    private int id;
    private String name;
    private int personId;
    private int price;
    private String description;
    private int quantity_in_stock;

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

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public void setQuantity_in_stock(int quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public Part(String name, int personId, int price, String description, int quantity_in_stock) {
        this.name = name;
        this.personId = personId;
        this.price = price;
        this.description = description;
        this.quantity_in_stock = quantity_in_stock;
    }

    public Part(int id, String name, int personId, int price, String description, int quantity_in_stock) {
        this.id = id;
        this.name = name;
        this.personId = personId;
        this.price = price;
        this.description = description;
        this.quantity_in_stock = quantity_in_stock;
    }
}
