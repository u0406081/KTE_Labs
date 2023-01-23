package com.example.kte_labs.repository;

import com.example.kte_labs.dto.response.Product_Add_Information;
import com.example.kte_labs.entity.FactOfSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FactOfSaleRepository extends JpaRepository<FactOfSale, Long> {

    @Query("SELECT new com.example.kte_labs.dto.response." +
            "Product_Add_Information(p.description, est.estimate) " +
            "FROM FactOfSale f " +
      "INNER JOIN f.positions pos " +
      "INNER JOIN pos.product p "+
       "LEFT JOIN p.estimates as est on est.client.id = ?1 " +
           "WHERE f.client.id = ?1 " +
             "AND pos.product.id = ?2 " +
        "GROUP BY p.description, est.estimate")
    Product_Add_Information getProductAddInfo(Long client_id, Long product_id);
}
