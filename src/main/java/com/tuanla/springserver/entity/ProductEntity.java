package com.tuanla.springserver.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "ecommerce", catalog = "")
public class ProductEntity {
    private Integer id;
    private String prodName;
    private Integer prodPrice;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "prod_name")
    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    @Basic
    @Column(name = "prod_price")
    public Integer getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Integer prodPrice) {
        this.prodPrice = prodPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(prodName, that.prodName) && Objects.equals(prodPrice, that.prodPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prodName, prodPrice);
    }
}
