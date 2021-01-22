package com.tuanla.springserver.controller;

import com.tuanla.springserver.entity.TblProductEntity;
import com.tuanla.springserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Controller
//@CrossOrigin(origins = "http://localhost:4200")
public class ProductController implements Serializable {
    private static final long serialVersionUID = 1L;

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "product")
    public ResponseEntity<List<TblProductEntity>> getAllEm(){
        List<TblProductEntity> productList = productService.getAll();
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping(value = "product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TblProductEntity> GetEmById(@PathVariable("id") int id) {
        Optional<TblProductEntity> employee = productService.getById(id);
        if (!employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    }
}
