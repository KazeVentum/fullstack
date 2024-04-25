package com.example.fullstack.web.controller;

import com.example.fullstack.domain.service.orderDetail.OrderDetailInterfaceImp;
import com.example.fullstack.domain.service.products.ProductsInterfaceImp;
import com.example.fullstack.persistence.entity.OrderDetail;
import com.example.fullstack.persistence.entity.Products;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fullstack")
@CrossOrigin("https://localhost:8080")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")

public class ProductsController {
    private final ProductsInterfaceImp productsService;

    @Autowired
    public ProductsController(ProductsInterfaceImp productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products/all")
    public List<Products> getAllOrderDetails(){
        return productsService.getAll();
    }
}
