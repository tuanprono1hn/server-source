package com.tuanla.springserver.model;

import com.tuanla.springserver.entity.TblCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<TblCategoryEntity, Integer>, JpaSpecificationExecutor<TblCategoryEntity> {
}
