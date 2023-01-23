package com.example.kte_labs.dto;

public class Statistic {
    Long numberOfReceipts;
    Double totalCostReceipts;
    Double totalCostPositionsOriginalPrice;
    Long amountOfDiscountsForReceiptPositions;

    public Statistic(Long numberOfReceipts,
                     Double totalCostReceipts,
                     Double totalCostPositionsOriginalPrice,
                     Long amountOfDiscountsForReceiptPositions) {
        this.numberOfReceipts = numberOfReceipts;
        this.totalCostReceipts = totalCostReceipts;
        this.totalCostPositionsOriginalPrice = totalCostPositionsOriginalPrice;
        this.amountOfDiscountsForReceiptPositions = amountOfDiscountsForReceiptPositions;
    }

    public Long getNumberOfReceipts() {
        return numberOfReceipts;
    }

    public void setNumberOfReceipts(Long numberOfReceipts) {
        this.numberOfReceipts = numberOfReceipts;
    }

    public Double getTotalCostReceipts() {
        return totalCostReceipts;
    }

    public void setTotalCostReceipts(Double totalCostReceipts) {
        this.totalCostReceipts = totalCostReceipts;
    }

    public Double getTotalCostPositionsOriginalPrice() {
        return totalCostPositionsOriginalPrice;
    }

    public void setTotalCostPositionsOriginalPrice(Double totalCostPositionsOriginalPrice) {
        this.totalCostPositionsOriginalPrice = totalCostPositionsOriginalPrice;
    }

    public Long getAmountOfDiscountsForReceiptPositions() {
        return amountOfDiscountsForReceiptPositions;
    }

    public void setAmountOfDiscountsForReceiptPositions(Long amountOfDiscountsForReceiptPositions) {
        this.amountOfDiscountsForReceiptPositions = amountOfDiscountsForReceiptPositions;
    }
}
