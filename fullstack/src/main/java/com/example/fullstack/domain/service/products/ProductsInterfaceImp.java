package com.example.fullstack.domain.service.products;

import com.example.fullstack.domain.exeptions.products.ProductNotFoundException;
import com.example.fullstack.domain.exeptions.products.ProductsIdNotFoundException;
import com.example.fullstack.domain.repository.ProductsRepository;
import com.example.fullstack.persistence.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsInterfaceImp implements ProductsInterface<Products> {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsInterfaceImp(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    };

    @Override
    public List<Products> getAll() {
        return productsRepository.findAll();
    }

    @Override
    public ResponseEntity<Products> getById(Object id) {
        try{
            Long  newId = Long.parseLong(id.toString());
            Products optionalProducts = productsRepository.findById(newId)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));
            return ResponseEntity.ok(optionalProducts);

        }catch (NumberFormatException e){
            throw new ProductsIdNotFoundException("You have entered a letter or another character other than a Long type Number");
        }
    }

    @Override
    public ResponseEntity<Products> save(Products productsSave) {
        productsRepository.save(productsSave);
        return ResponseEntity.ok(productsSave);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        if (productsRepository.existsById(id)) {
            productsRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Products> update(Object id, Products productsUpdate) {
        try{
            Long newId = Long.parseLong(id.toString());
            Optional<Products> optionalProducts = productsRepository.findById(newId);
            if(optionalProducts.isPresent()){

                Products productSend = optionalProducts.get();
                productSend.setName(productsUpdate.getName());
                productSend.setDescription(productsUpdate.getDescription());
                productSend.setPrice(productsUpdate.getPrice());
                productSend.setStock_quantity(productsUpdate.getStock_quantity());
                productsRepository.save(productSend);

                return ResponseEntity.ok(productSend);
            } else {
                throw new ProductNotFoundException("Product not found by said ID to update");
            }
        }catch (NumberFormatException e){
            throw new ProductsIdNotFoundException("You have entered a letter or/or another character other than a Long type Number");
        }
    }
}
