package com.example.ciidjsp.model;

import java.sql.Date;

public class Order {
    private int id;
    private int personId;
    private Date orderDate;

    public int getId() {
        return id;
    }

    public void setId(int orderId) {
        this.id = orderId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setClientId(int personId) {
        this.personId = personId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Order(int orderId, int personId, Date orderDate) {
        this.id = orderId;
        this.personId = personId;
        this.orderDate = orderDate;
    }

    public Order(int personId, Date orderDate) {
        this.personId = personId;
        this.orderDate = orderDate;
    }
}
