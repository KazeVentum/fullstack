package com.example.fullstack.web.controller;

import com.example.fullstack.domain.repository.CustomerRepository;
import com.example.fullstack.domain.security.JWTAuthtenticationConfig;
import com.example.fullstack.persistence.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private CustomerRepository customerRepository;

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email){

        Customer customer = customerRepository.findByUsername(username);

        if (customer != null && customer.getPassword().equals(password) && customer.getEmail().equals(email)) {
            String token = jwtAuthtenticationConfig.getJWTToken(username);
            customerRepository.save(customer);
            return token;
        } else {
            throw new RuntimeException("Invalid Information");
        }
    }
}

