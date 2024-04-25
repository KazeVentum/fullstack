package com.example.fullstack.web.controller;


import com.example.fullstack.domain.service.customer.CustomerInterfaceImp;
import com.example.fullstack.persistence.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/register")

public class CustomerController {
    private final CustomerInterfaceImp customerInterfaceImp;

    @Autowired
    public CustomerController(CustomerInterfaceImp customerInterfaceImp) {
        this.customerInterfaceImp = customerInterfaceImp;
    }
    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerInterfaceImp.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Object id) {
        return customerInterfaceImp.getById(id);
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customerSave) {
        return customerInterfaceImp.save(customerSave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        return customerInterfaceImp.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer( @PathVariable Object id, @RequestBody Customer customerUpdate) {
        return customerInterfaceImp.update(id, customerUpdate);
    }

}