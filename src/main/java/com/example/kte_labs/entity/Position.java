package com.example.kte_labs.entity;

import javax.persistence.*;

@Entity
@Table(name="position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="fact_of_sale_id", nullable=false)
    private FactOfSale factOfSale;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @Column(name="amount")
    Integer amount;

    @Column(name="original_price")
    Double originalPrice;

    @Column(name="final_price")
    Double finalPrice;

    @Column(name="final_discount")
    Integer finalDiscount;

    public FactOfSale getFactOfSale() {
        return factOfSale;
    }

    public void setFactOfSale(FactOfSale factOfSale) {
        this.factOfSale = factOfSale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getFinalDiscount() {
        return finalDiscount;
    }

    public void setFinalDiscount(Integer finalDiscount) {
        this.finalDiscount = finalDiscount;
    }
}
