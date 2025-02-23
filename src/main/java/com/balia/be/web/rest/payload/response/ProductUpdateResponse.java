package com.balia.be.web.rest.payload.response;

import com.balia.be.domain.MProduct;
import com.balia.be.domain.MProductImage;

import java.util.List;

public class ProductUpdateResponse {
    private String status;
    private String message;
    private MProduct mProduct;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MProduct getmProduct() {
        return mProduct;
    }

    public void setmProduct(MProduct mProduct) {
        this.mProduct = mProduct;
    }
}
