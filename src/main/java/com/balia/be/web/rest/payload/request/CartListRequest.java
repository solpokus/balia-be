package com.balia.be.web.rest.payload.request;

import com.balia.be.domain.MProduct;
import com.balia.be.domain.MUser;

public class CartListRequest {

    private Long id;
    private MProduct mProduct;
    private Integer quantity;
    private MUser mUser;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MUser getmUser() {
        return mUser;
    }

    public void setmUser(MUser mUser) {
        this.mUser = mUser;
    }
}
