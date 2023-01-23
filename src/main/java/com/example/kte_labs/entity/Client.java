package com.example.kte_labs.entity;

import javax.persistence.*;

@Entity
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer individual_discount_1;
    private Integer individual_discount_2;

    public Client() {
    }

    public Client(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndividual_discount_1() {
        return individual_discount_1;
    }

    public void setIndividual_discount_1(Integer individual_discount_1) {
        this.individual_discount_1 = individual_discount_1;
    }

    public Integer getIndividual_discount_2() {
        return individual_discount_2;
    }

    public void setIndividual_discount_2(Integer individual_discount_2) {
        this.individual_discount_2 = individual_discount_2;
    }
}
