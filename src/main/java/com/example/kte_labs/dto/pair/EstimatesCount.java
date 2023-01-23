package com.example.kte_labs.dto.pair;

public class EstimatesCount {
    private String pair;

    public EstimatesCount() {
    }

    public EstimatesCount(Integer estimate, Long quantity) {
        pair = estimate + " - " + quantity;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }
}
