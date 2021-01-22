package com.tuanla.springserver.model;

import com.tuanla.springserver.entity.TblProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<TblProductEntity, Integer> {
}
