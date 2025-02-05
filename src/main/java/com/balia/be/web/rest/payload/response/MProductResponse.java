package com.balia.be.web.rest.payload.response;

import com.balia.be.domain.MCategories;
import com.balia.be.domain.MProductImage;

import java.util.Date;
import java.util.List;

public class MProductResponse {
    private Long id;
    private String name;
    private String sku;
    private Integer price;
    private String currency;
    private Integer stock;
    private Integer status;
    private String shortDescription;
    private String longDescription;
    private MCategories mCategories;
    private String createdBy;
    private Date createdDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
    private List<MProductImage> mProductImages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public MCategories getmCategories() {
        return mCategories;
    }

    public void setmCategories(MCategories mCategories) {
        this.mCategories = mCategories;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<MProductImage> getmProductImages() {
        return mProductImages;
    }

    public void setmProductImages(List<MProductImage> mProductImages) {
        this.mProductImages = mProductImages;
    }

    public MProductResponse(Long id, String name, String sku, Integer price, String currency, Integer stock, Integer status, 
                            String shortDescription, String longDescription, MCategories mCategories, String createdBy, 
                            Date createdDate, String lastModifiedBy, Date lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.currency = currency;
        this.stock = stock;
        this.status = status;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.mCategories = mCategories;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }
    
    public MProductResponse(){
    }

    public MProductResponse(MProductResponse mProductResponse){
    }

    @Override
    public String toString() {
        return "MProductResponse{" + "id=" + id + ", name='" + name + '\'' + ", sku='" + sku + '\'' + ", price=" + price + ", currency='" + currency + '\'' + ", stock=" + stock + ", status=" + status + ", shortDescription='" + shortDescription + '\'' + ", longDescription='" + longDescription + '\'' + ", mCategories=" + mCategories + ", createdBy='" + createdBy + '\'' + ", createdDate=" + createdDate + ", lastModifiedBy='" + lastModifiedBy + '\'' + ", lastModifiedDate=" + lastModifiedDate + ", mProductImages=" + mProductImages + '}';
    }
}
