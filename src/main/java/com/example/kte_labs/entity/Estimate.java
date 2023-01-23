package com.example.kte_labs.entity;

import javax.persistence.*;

@Entity
@Table(name="estimate")
public class Estimate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=true)
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    @ManyToOne(optional=true)
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    Integer estimate;

    public Estimate() {
    }

    public Estimate(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getEstimate() {
        return estimate;
    }

    public void setEstimate(Integer estimate) {
        this.estimate = estimate;
    }
}
