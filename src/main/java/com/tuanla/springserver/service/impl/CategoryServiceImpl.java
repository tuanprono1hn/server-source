package com.tuanla.springserver.service.impl;

import com.tuanla.springserver.entity.TblCategoryEntity;
import com.tuanla.springserver.entity.TblProductEntity;
import com.tuanla.springserver.model.CategoryRepo;
import com.tuanla.springserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<TblCategoryEntity> getAll() {
        return categoryRepo.findAll(Sort.by(Sort.Order.asc("idCategory")));
    }

    @Override
    public Optional<TblCategoryEntity> getById(int id) {
        return categoryRepo.findById(id);
    }

    @Override
    public void create(TblCategoryEntity entity) {
        categoryRepo.save(entity);
    }

    @Override
    public void delete(TblCategoryEntity entity) {
        categoryRepo.deleteById(entity.getIdCategory());
    }

    @Override
    public List<TblCategoryEntity> findAll(Specification where) {
        return categoryRepo.findAll(where);
    }
}
