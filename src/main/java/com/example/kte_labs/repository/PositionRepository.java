package com.example.kte_labs.repository;

import com.example.kte_labs.dto.Statistic;
import com.example.kte_labs.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("SELECT new com.example.kte_labs.dto.Statistic(" +
            "count(f.receiptNumber), " +
            "sum(f.price), " +
            "sum(p.originalPrice * p.amount), " +
            "sum(p.finalDiscount)" +
            ") " +
             "FROM FactOfSale f " +
       "INNER JOIN Position p ON f.id = p.factOfSale.id " +
            "WHERE f.client.id = ?1 " +
                  "AND p.product.id = ?2 ")
    Statistic getStatistic(Long client_id, Long product_id);
}
