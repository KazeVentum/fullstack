package com.example.fullstack.domain.repository;

import com.example.fullstack.persistence.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
