package com.example.ciidtestspring;

public enum PersonTypeEnum {
    CUSTOMER(1),
    SUPPLIER(2) ;
    private long id;

    public long getId() {
        return id;
    }

    PersonTypeEnum(long id) {
        this.id = id;
    }
}
