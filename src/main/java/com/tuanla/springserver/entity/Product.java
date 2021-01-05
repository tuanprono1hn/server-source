package com.tuanla.springserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product", schema = "ecommerce")
@Getter
@Setter
public class Product implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "prod_name", nullable = false, length = 500)
    private String prodName;

    @Basic
    @Column(name = "prod_price", nullable = false)
    private int prodPrice;
}
