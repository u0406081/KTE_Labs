package com.example.kte_labs.component;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Receipt {
    private AtomicInteger receiptNumber = new AtomicInteger(100);

    public AtomicInteger getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(Integer num) {
        receiptNumber.set(num);
    }
}
