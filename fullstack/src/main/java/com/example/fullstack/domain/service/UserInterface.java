package com.example.fullstack.domain.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInterface<T>{
    List<T> getAll();
    ResponseEntity<T> getById(Object id);
    ResponseEntity<T> save(T t);
    ResponseEntity<Void> delete(Long id);
    ResponseEntity<T> update(Object id, T t);
}
