package com.tuanla.springserver.service.impl;

import com.tuanla.springserver.entity.TblProductEntity;
import com.tuanla.springserver.model.ProductRepo;
import com.tuanla.springserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<TblProductEntity> getAll() {
        return (List<TblProductEntity>) productRepo.findAll(Sort.by(Sort.Order.desc("prodPrice")));
    }

    @Override
    public List<TblProductEntity> getByCategory(int id) {
        List<TblProductEntity> productList = null;
        List<TblProductEntity> tempList = productRepo.findAll();
        productList = tempList.stream().filter(product -> product.getIdCategory().getIdCategory() == id).collect(Collectors.toList());
        return (List<TblProductEntity>) productList;
    }

    @Override
    public Optional<TblProductEntity> getById(int id) {
        return productRepo.findById(id);
    }

    @Override
    public void create(TblProductEntity entity) {
        productRepo.save(entity);
    }

    @Override
    public void delete(TblProductEntity entity) {
        productRepo.deleteById(entity.getIdSp());
    }
}
