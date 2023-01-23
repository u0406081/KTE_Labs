package com.example.kte_labs.exception;

public class CalculatedTotalCostDoesNotEqualsPassed extends Exception {
    public CalculatedTotalCostDoesNotEqualsPassed(Double calculatedCost, Double passedCost) {
        super("Calculated total cost " + calculatedCost + " does not equals passed " + passedCost);
    }
}
