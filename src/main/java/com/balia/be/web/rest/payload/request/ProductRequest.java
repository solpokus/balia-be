package com.balia.be.web.rest.payload.request;

import com.balia.be.domain.MCategories;
import com.balia.be.domain.MProduct;

import java.util.Date;
import java.util.Objects;

public class ProductRequest {
    
    private Long id;
    private String name;
    private String sku;
    private Integer price;
    private String currency;
    private Integer stock;
    private Integer status;
    private String color;
    private String size;
    private String shortDescription;
    private String longDescription;
    private MCategories mCategories;
    private String createdBy;
    private Date createdDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
    private String sustainabilityFeature;
    private String material;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public String getSustainabilityFeature() {
        return sustainabilityFeature;
    }

    public void setSustainabilityFeature(String sustainabilityFeature) {
        this.sustainabilityFeature = sustainabilityFeature;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MProduct mProduct = (MProduct) o;
        if (mProduct.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mProduct.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductRequest{" + "id=" + id + ", name='" + name + '\'' + ", sku='" + sku + '\'' + ", price=" + price + ", currency='" + currency + '\'' + ", stock=" + stock + ", status=" + status + ", color='" + color + '\'' + ", size='" + size + '\'' + ", shortDescription='" + shortDescription + '\'' + ", longDescription='" + longDescription + '\'' + ", mCategories=" + mCategories + ", createdBy='" + createdBy + '\'' + ", createdDate=" + createdDate + ", lastModifiedBy='" + lastModifiedBy + '\'' + ", lastModifiedDate=" + lastModifiedDate + ", sustainabilityFeature='" + sustainabilityFeature + '\'' + ", material='" + material + '\'' + '}';
    }
}
