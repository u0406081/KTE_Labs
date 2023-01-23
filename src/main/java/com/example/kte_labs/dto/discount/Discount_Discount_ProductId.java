package com.example.kte_labs.dto.discount;

public class Discount_Discount_ProductId {
    private Integer discount;
    private Long productId;

    public Discount_Discount_ProductId(Integer discount, Long productId) {
        this.discount = discount;
        this.productId = productId;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Long getProductId() {
        return productId;
    }
}
