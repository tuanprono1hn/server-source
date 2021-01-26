package com.tuanla.springserver.controller;

import com.tuanla.springserver.entity.TblProductEntity;
import com.tuanla.springserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class ProductController implements Serializable {
    private static final long serialVersionUID = 1L;

    private ProductService service;

    @Autowired
    public ProductController(ProductService productService) {
        this.service = productService;
    }

    @GetMapping(value = "product")
    public ResponseEntity<List<TblProductEntity>> getAll() {
        List<TblProductEntity> entityList = service.getAll();
        if (entityList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entityList, HttpStatus.OK);
    }

    @GetMapping(value = "product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TblProductEntity> getById(@PathVariable("id") int id) {
        Optional<TblProductEntity> entity = service.getById(id);
        if (!entity.isPresent()) {
            return new ResponseEntity<>(entity.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entity.get(), HttpStatus.OK);
    }

    @GetMapping(value = "productByCate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TblProductEntity>> getByCateId(@PathVariable("id") int id) {
        List<TblProductEntity> entityList = service.getByCategory(id);
        if (entityList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entityList, HttpStatus.OK);
    }

    @PostMapping(value = "product")
    public ResponseEntity<TblProductEntity> create(@RequestBody TblProductEntity newEntity, UriComponentsBuilder builder) {
        service.create(newEntity);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("product/{id}").buildAndExpand(newEntity.getIdSp()).toUri());
        return new ResponseEntity<>(newEntity, HttpStatus.CREATED);
    }

    @PutMapping(value = "product/{id}")
    public ResponseEntity<TblProductEntity> update(@PathVariable("id") int id, @RequestBody TblProductEntity entity) {
        Optional<TblProductEntity> tempEntity = service.getById(id);
        if (!tempEntity.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        //tempEntity.get() = new TblProductEntity(product);

        tempEntity.get().setIdCategory(entity.getIdCategory());
        tempEntity.get().setIdCmt(entity.getIdCmt());
        tempEntity.get().setIdGallery(entity.getIdGallery());
        tempEntity.get().setIdPromo(entity.getIdPromo());
        tempEntity.get().setProdColor(entity.getProdColor());
        tempEntity.get().setProdName(entity.getProdName());
        tempEntity.get().setProdPrice(entity.getProdPrice());
        tempEntity.get().setProdLink(entity.getProdLink());
        tempEntity.get().setProdReleasedDate(entity.getProdReleasedDate());
        tempEntity.get().setProdStoke(entity.getProdStoke());
        tempEntity.get().setStatus(entity.getStatus());

        service.create(tempEntity.get());
        return new ResponseEntity<>(tempEntity.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "product/{id}")
    public ResponseEntity<TblProductEntity> delete(@PathVariable("id") int id) {
        Optional<TblProductEntity> entity = service.getById(id);
        if (!entity.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(entity.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
