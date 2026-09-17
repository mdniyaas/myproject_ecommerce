package com.example.demo.repository;

import com.example.demo.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByUsername(String username);

    @Query("""
           SELECT SUM(o.price * o.quantity)
           FROM OrderEntity o
           """)
    Double getTotalRevenue();
}