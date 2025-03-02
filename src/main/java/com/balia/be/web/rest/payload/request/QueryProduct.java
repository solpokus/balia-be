package com.balia.be.web.rest.payload.request;

public class QueryProduct {
    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "QueryProduct{" + "categoryId=" + categoryId + '}';
    }
}
