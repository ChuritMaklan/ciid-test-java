package org.example.model;

public class PersonType {
    private int id;
    private String typeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public PersonType(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public PersonType(String typeName) {
        this.typeName = typeName;
    }
}
