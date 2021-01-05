package com.tuanla.springserver.service.impl;

import com.tuanla.springserver.entity.Product;
import com.tuanla.springserver.model.ProductRepo;
import com.tuanla.springserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getAll() {
        return (List<Product>) productRepo.findAll();
    }

    @Override
    public Optional<Product> getById(int id) {
        return productRepo.findById(id);
    }

    @Override
    public void create(Product amEmployee) {

    }

    @Override
    public void delete(Product amEmployee) {

    }
}
