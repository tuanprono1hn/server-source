package com.tuanla.springserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TBL_PRODUCT")
public class TblProductEntity implements Serializable {
    private static final long serialVersionUID = 1;
    private Integer idSp;
    private TblCategoryEntity idCategory;
    private Integer idGallery;
    private Integer idPromo;
    private Integer idCmt;
    private String prodName;
    private String prodLink;
    private Integer prodPrice;
    private String prodColor;
    private Integer prodStoke;
    private Date prodReleasedDate;
    private Integer status;

    public TblProductEntity() {
    }

    @Id
    @Column(name = "id_sp")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdSp() {
        return idSp;
    }

    public void setIdSp(Integer idSp) {
        this.idSp = idSp;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_category", referencedColumnName = "id_category", nullable = false)
    public TblCategoryEntity getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(TblCategoryEntity idCategory) {
        this.idCategory = idCategory;
    }

    @Basic
    @Column(name = "id_gallery")
    public Integer getIdGallery() {
        return idGallery;
    }

    public void setIdGallery(Integer idGallery) {
        this.idGallery = idGallery;
    }

    @Basic
    @Column(name = "id_promo")
    public Integer getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Integer idPromo) {
        this.idPromo = idPromo;
    }

    @Basic
    @Column(name = "id_cmt")
    public Integer getIdCmt() {
        return idCmt;
    }

    public void setIdCmt(Integer idCmt) {
        this.idCmt = idCmt;
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
    @Column(name = "prod_link")
    public String getProdLink() {
        return prodLink;
    }

    public void setProdLink(String prodLink) {
        this.prodLink = prodLink;
    }

    @Basic
    @Column(name = "prod_price")
    public Integer getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Integer prodPrice) {
        this.prodPrice = prodPrice;
    }

    @Basic
    @Column(name = "prod_color")
    public String getProdColor() {
        return prodColor;
    }

    public void setProdColor(String prodColor) {
        this.prodColor = prodColor;
    }

    @Basic
    @Column(name = "prod_stoke")
    public Integer getProdStoke() {
        return prodStoke;
    }

    public void setProdStoke(Integer prodStoke) {
        this.prodStoke = prodStoke;
    }

    @Basic
    @Column(name = "prod_released_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy/MM/dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
//    @Temporal(TemporalType.TIMESTAMP)
    public Date getProdReleasedDate() {
        return prodReleasedDate;
    }

    public void setProdReleasedDate(Date prodReleasedDate) {
        this.prodReleasedDate = prodReleasedDate;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblProductEntity that = (TblProductEntity) o;
        return Objects.equals(idSp, that.idSp) &&
                Objects.equals(idCategory, that.idCategory) &&
                Objects.equals(idGallery, that.idGallery) &&
                Objects.equals(idPromo, that.idPromo) &&
                Objects.equals(idCmt, that.idCmt) &&
                Objects.equals(prodName, that.prodName) &&
                Objects.equals(prodLink, that.prodLink) &&
                Objects.equals(prodPrice, that.prodPrice) &&
                Objects.equals(prodColor, that.prodColor) &&
                Objects.equals(prodStoke, that.prodStoke) &&
                Objects.equals(prodReleasedDate, that.prodReleasedDate) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSp, idCategory, idGallery, idPromo, idCmt, prodName, prodLink, prodPrice, prodColor, prodStoke, prodReleasedDate, status);
    }
}
