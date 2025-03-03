package org.example.model;

public class OrderItem {
    private int id;
    private int orderId;
    private int partId;
    private int quantity;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public OrderItem(int id, int orderId, int partId, int quantity, int price) {
        this.id = id;
        this.orderId = orderId;
        this.partId = partId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem(int orderId, int partId, int quantity, int price) {
        this.orderId = orderId;
        this.partId = partId;
        this.quantity = quantity;
        this.price = price;
    }
}
