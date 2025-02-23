package com.balia.be.web.rest.payload.response;

public class CartResponse extends BaseResponse{
    private Long cartId;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
