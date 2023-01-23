package com.example.kte_labs.dto.product;

public class Product_Pair {
    Long id;
    Integer howMany;

    public Product_Pair() {
    }

    public Product_Pair(Long id, Integer howMany) {
        this.id = id;
        this.howMany = howMany;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHowMany() {
        return howMany;
    }

    public void setHowMany(Integer howMany) {
        this.howMany = howMany;
    }
}
