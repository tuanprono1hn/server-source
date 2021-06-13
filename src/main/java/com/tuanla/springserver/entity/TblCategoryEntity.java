package com.tuanla.springserver.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TBL_CATEGORY")
public class TblCategoryEntity {
    private Integer idCategory;
    private String category;

    @Id
    @Column(name = "id_category")
    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @OneToMany(mappedBy = "id_category", cascade = CascadeType.ALL)
    private List<TblProductEntity> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblCategoryEntity that = (TblCategoryEntity) o;
        return Objects.equals(idCategory, that.idCategory) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategory, category);
    }
}
