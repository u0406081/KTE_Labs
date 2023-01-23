package com.example.kte_labs.exception;

public class DiscountNotFound extends Exception {
    public DiscountNotFound(Long id) {
        super("Discount with id " + id + " not found in database");
    }
}