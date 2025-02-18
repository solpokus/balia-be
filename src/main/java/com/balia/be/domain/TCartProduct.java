package com.balia.be.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A t_cart_product.
 */
@Entity
@Table(name = "t_cart_product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TCartProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MProduct mProduct;

    @Column(name = "qty", nullable = false)
    private Integer qty;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "special_price", nullable = false)
    private Integer specialPrice;

    @Column(name = "currency", nullable = false)
    private String currency;
    
    @ManyToOne
    private TCart tcart;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MProduct getmProduct() {
        return mProduct;
    }

    public void setmProduct(MProduct mProduct) {
        this.mProduct = mProduct;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(Integer specialPrice) {
        this.specialPrice = specialPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public TCart getTcart() {
        return tcart;
    }

    public void setTcart(TCart tcart) {
        this.tcart = tcart;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TCartProduct mProduct = (TCartProduct) o;
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
        return "TCartProduct{" + "id=" + id + ", mProduct=" + mProduct + ", qty=" + qty + ", price=" + price + ", specialPrice=" + specialPrice + ", currency='" + currency + '\'' + ", tcart=" + tcart + ", createdBy='" + createdBy + '\'' + ", createdDate=" + createdDate + ", lastModifiedBy='" + lastModifiedBy + '\'' + ", lastModifiedDate=" + lastModifiedDate + '}';
    }
}
