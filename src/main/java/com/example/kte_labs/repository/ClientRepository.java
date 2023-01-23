package com.example.kte_labs.repository;

import com.example.kte_labs.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT new com.example.kte_labs.entity.Client(c.id) " +
            "FROM Client c " +
            "WHERE c.id = ?1")
    Optional<Client> getClientIdById(Long id);
}
