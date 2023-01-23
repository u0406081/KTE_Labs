package com.example.kte_labs.exception;

public class ProductNotFound extends Exception {
    public ProductNotFound(Long id) {
        super("Product with id " + id + " not found in database");
    }
}
