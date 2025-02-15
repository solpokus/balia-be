package com.balia.be.web.rest.payload.response;

import com.balia.be.web.rest.payload.response.dto.MProductImageDTO;

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
    private String color;
    private String size;
    private String shortDescription;
    private String longDescription;
    private Long mCategoriesId;
    private String createdBy;
    private Date createdDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
    private String sustainabilityFeature;
    private String material;
    private List<MProductImageDTO> mProductImages;

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

    public Long getmCategoriesId() {
        return mCategoriesId;
    }

    public void setmCategoriesId(Long mCategoriesId) {
        this.mCategoriesId = mCategoriesId;
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

    public List<MProductImageDTO> getmProductImages() {
        return mProductImages;
    }

    public void setmProductImages(List<MProductImageDTO> mProductImages) {
        this.mProductImages = mProductImages;
    }

    public MProductResponse(Long id, String name, String sku, Integer price, String currency, Integer stock, Integer status,
                            String color, String size, String shortDescription, String longDescription, Long mCategoriesId, String createdBy,
                            Date createdDate, String lastModifiedBy, Date lastModifiedDate, String sustainabilityFeature, String material) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.currency = currency;
        this.stock = stock;
        this.status = status;
        this.color = color;
        this.size = size;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.mCategoriesId = mCategoriesId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
        this.sustainabilityFeature = sustainabilityFeature;
        this.material = material;
    }
    
    public MProductResponse(){
    }

    public MProductResponse(MProductResponse mProductResponse){
    }

    @Override
    public String toString() {
        return "MProductResponse{" + "id=" + id + ", name='" + name + '\'' + ", sku='" + sku + '\'' + ", price=" + price + ", currency='" + currency + '\'' + ", stock=" + stock + ", status=" + status + ", color='" + color + '\'' + ", size='" + size + '\'' + ", shortDescription='" + shortDescription + '\'' + ", longDescription='" + longDescription + '\'' + ", mCategoriesId=" + mCategoriesId + ", createdBy='" + createdBy + '\'' + ", createdDate=" + createdDate + ", lastModifiedBy='" + lastModifiedBy + '\'' + ", lastModifiedDate=" + lastModifiedDate + ", sustainabilityFeature='" + sustainabilityFeature + '\'' + ", material='" + material + '\'' + ", mProductImages=" + mProductImages + '}';
    }
}
