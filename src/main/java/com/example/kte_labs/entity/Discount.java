package com.example.kte_labs.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="datetime")
    private LocalDateTime dateTime;
    private Integer discount;
    @OneToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    private Boolean current;

    public Discount() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }
}
