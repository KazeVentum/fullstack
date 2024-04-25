package com.example.fullstack.domain.repository;

import com.example.fullstack.persistence.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
