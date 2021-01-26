package com.tuanla.springserver.controller;

import com.tuanla.springserver.entity.TblCategoryEntity;
import com.tuanla.springserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController implements Serializable {
    private static final long serialVersionUID = 1L;

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping(value = "category")
    public ResponseEntity<List<TblCategoryEntity>> getAll() {
        List<TblCategoryEntity> entityList = service.getAll();
        if (entityList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entityList, HttpStatus.OK);
    }

    @GetMapping(value = "category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TblCategoryEntity> getById(@PathVariable("id") int id) {
        Optional<TblCategoryEntity> entity = service.getById(id);
        if (!entity.isPresent()) {
            return new ResponseEntity<>(entity.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entity.get(), HttpStatus.OK);
    }

    @PostMapping(value = "category")
    public ResponseEntity<TblCategoryEntity> create(@RequestBody TblCategoryEntity newEntity, UriComponentsBuilder builder) {
        service.create(newEntity);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("category/{id}").buildAndExpand(newEntity.getIdCategory()).toUri());
        return new ResponseEntity<>(newEntity, HttpStatus.CREATED);
    }

    @PutMapping(value = "category/{id}")
    public ResponseEntity<TblCategoryEntity> update(@PathVariable("id") int id, @RequestBody TblCategoryEntity entity) {
        Optional<TblCategoryEntity> tempEntity = service.getById(id);
        if (!tempEntity.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        tempEntity.get().setIdCategory(entity.getIdCategory());
        tempEntity.get().setCategory(entity.getCategory());

        service.create(tempEntity.get());
        return new ResponseEntity<>(tempEntity.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "category/{id}")
    public ResponseEntity<TblCategoryEntity> delete(@PathVariable("id") int id) {
        Optional<TblCategoryEntity> entity = service.getById(id);
        if (!entity.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(entity.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
