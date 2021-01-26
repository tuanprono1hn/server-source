package com.tuanla.springserver.service;

import com.tuanla.springserver.entity.TblProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<TblProductEntity> getAll();
    List<TblProductEntity> getByCategory(int id);
    Optional<TblProductEntity> getById(int id);
    void create(TblProductEntity product);
    void delete(TblProductEntity product);
}
