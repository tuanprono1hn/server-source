package com.tuanla.springserver.service;

import com.tuanla.springserver.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll();
    Optional<Product> getById(int id);
    void create(Product product);
    void delete(Product product);
}
