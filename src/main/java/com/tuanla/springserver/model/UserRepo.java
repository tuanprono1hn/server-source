package com.tuanla.springserver.model;

import com.tuanla.springserver.entity.TblUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<TblUserEntity, Integer> {
    TblUserEntity findByUsername(String username);
}
