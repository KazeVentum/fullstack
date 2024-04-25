package com.example.fullstack.web.controller;

import com.example.fullstack.domain.service.orderDetail.OrderDetailInterface;
import com.example.fullstack.domain.service.orderDetail.OrderDetailInterfaceImp;
import com.example.fullstack.domain.service.orders.OrdersInterfaceImp;
import com.example.fullstack.persistence.entity.OrderDetail;
import com.example.fullstack.persistence.entity.Orders;
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
public class OrderDetailController {
    private final OrderDetailInterfaceImp orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailInterfaceImp orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/orderDetail/all")
    public List<OrderDetail> getAllOrderDetails(){
        return orderDetailService.getAll();
    }

}
