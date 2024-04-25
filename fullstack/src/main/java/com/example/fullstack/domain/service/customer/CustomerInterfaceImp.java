package com.example.fullstack.domain.service.customer;

import com.example.fullstack.domain.exeptions.customer.CustomerIdNotFoundException;
import com.example.fullstack.domain.exeptions.customer.CustomerNotFoundException;
import com.example.fullstack.domain.repository.CustomerRepository;
import com.example.fullstack.persistence.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerInterfaceImp implements CustomerInterface<Customer> {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerInterfaceImp(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    };

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public ResponseEntity<Customer> getById(Object id) {
        try{
            Long  newId = Long.parseLong(id.toString());
            Customer optionalCustomer = customerRepository.findById(newId)
                    .orElseThrow(() -> new CustomerNotFoundException("User not found"));
            return ResponseEntity.ok(optionalCustomer);

        }catch (NumberFormatException e){
            throw new CustomerIdNotFoundException("You have entered a letter or another character other than a Long type Number");
        }
    }

    @Override
    public ResponseEntity<Customer> save(Customer customerSave) {
        customerRepository.save(customerSave);
        return ResponseEntity.ok(customerSave);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Customer> update(Object id, Customer customerUpdate) {
        try{
            Long newId = Long.parseLong(id.toString());
            Optional<Customer> optionalUser = customerRepository.findById(newId);
            if(optionalUser.isPresent()){

                Customer customerSend = optionalUser.get();
                customerSend.setUsername(customerUpdate.getUsername());
                customerSend.setPassword(customerUpdate.getPassword());
                customerSend.setEmail(customerUpdate.getEmail());
                customerRepository.save(customerSend);

                return ResponseEntity.ok(customerSend);
            } else {
                throw new CustomerNotFoundException("Admin not found by said ID to update");
            }
        }catch (NumberFormatException e){
            throw new CustomerIdNotFoundException("You have entered a letter or/or another character other than a Long type Number");
        }
    }
}

