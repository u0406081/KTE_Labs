package com.example.kte_labs.repository;

import com.example.kte_labs.dto.product.Product_Id_Name_Price;
import com.example.kte_labs.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.example.kte_labs.dto.product.Product_Id_Name_Price(p.id, p.name, p.price) " +
            "FROM Product p " +
            "ORDER BY p.name ASC ")
    List<Product_Id_Name_Price> getAllProducts();

    @Query("SELECT new com.example.kte_labs.entity.Product(p.id) " +
            "FROM Product p " +
            "WHERE p.id = ?1")
    Optional<Product> getProductIdById(Long id);


    @Query("SELECT new com.example.kte_labs.entity.Product(p.id, p.price)" +
             "FROM Product p " +
            "WHERE p.id = ?1")
    Product getProductPriceById(Long id);

    @Query("SELECT MIN(p.id) FROM Product p")
    Long  getMinProductId();

    @Query("SELECT MAX(p.id) FROM Product p")
    Long  getMaxProductId();
}
