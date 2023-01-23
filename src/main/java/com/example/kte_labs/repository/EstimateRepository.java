package com.example.kte_labs.repository;

import com.example.kte_labs.dto.estimate.Estimate_Average;
import com.example.kte_labs.dto.pair.EstimatesCount;
import com.example.kte_labs.entity.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EstimateRepository extends JpaRepository<Estimate, Long> {
    @Query("SELECT new com.example.kte_labs.entity.Estimate(e.id) " +
            "FROM Estimate e " +
           "WHERE e.client.id = ?1 " +
             "AND e.product.id = ?2")
    Estimate getEstimateIdByClientIdAndProductId(Long client_id, Long product_id);

    @Query("SELECT new com.example.kte_labs.dto.estimate.Estimate_Average(avg(e.estimate)) " +
             "FROM Estimate e " +
            "WHERE e.product.id = ?1")
    Estimate_Average getAverageByProduct(Long product_id);

    @Query("SELECT new com.example.kte_labs.dto.pair.EstimatesCount(e.estimate, COUNT(e.id)) " +
             "FROM Estimate e " +
            "WHERE e.product.id = ?1 " +
         "GROUP BY e.estimate " +
         "ORDER BY e.estimate")
    List<EstimatesCount> getCountEstimatesByProduct(Long product_id);
}
