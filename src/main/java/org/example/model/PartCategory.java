package org.example.model;

public class PartCategory {
    private int partId;
    private int categoryId;

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public PartCategory(int partId, int categoryId) {
        this.partId = partId;
        this.categoryId = categoryId;
    }
}
