package com.balia.be.web.rest.payload.request;

import java.util.List;

public class CartRequest {

    private List<CartListRequest> data;

    public List<CartListRequest> getData() {
        return data;
    }

    public void setData(List<CartListRequest> data) {
        this.data = data;
    }
}
