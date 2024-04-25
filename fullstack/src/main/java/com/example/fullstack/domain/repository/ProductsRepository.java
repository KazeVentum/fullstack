package com.example.fullstack.domain.repository;


import com.example.fullstack.persistence.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
