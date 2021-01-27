package com.tuanla.springserver.service;

import com.tuanla.springserver.entity.TblCategoryEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<TblCategoryEntity> getAll();
    Optional<TblCategoryEntity> getById(int id);
    void create(TblCategoryEntity entity);
    void delete(TblCategoryEntity entity);

    List<TblCategoryEntity> findAll(Specification where);
}
