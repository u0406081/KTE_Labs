package com.example.kte_labs.repository;

import com.example.kte_labs.dto.discount.Discount_Discount_ProductId;
import com.example.kte_labs.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    @Query("SELECT MAX(d.id) FROM Discount d")
    Long getMaxDiscountId();

    @Query("SELECT new com.example.kte_labs.dto.discount." +
            "Discount_Discount_ProductId(d.discount, d.product.id) " +
             "FROM Discount d " +
            "WHERE d.current = true")
    Discount_Discount_ProductId getCurrentDiscount();
}
