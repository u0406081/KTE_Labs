package com.example.kte_labs.dto.product;

public class Product_Price_Discount {
    private Double price;
    private Integer discount;

    public Product_Price_Discount(Double price, Integer discount) {
        this.price = price;
        this.discount = discount;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getDiscount() {
        return discount;
    }
}
