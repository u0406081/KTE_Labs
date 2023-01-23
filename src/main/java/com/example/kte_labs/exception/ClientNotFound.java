package com.example.kte_labs.exception;

public class ClientNotFound extends Exception {
    public ClientNotFound(Long id) {
        super("Client with id " + id + " not found in database");
    }
}
