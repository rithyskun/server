package com.vpos.server.product;

import com.vpos.server.business.Business;
import com.vpos.server.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author Rithy SKUN
 * @created 12/04/2023 - 6:54 PM
 * @project server
 **/

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "productName", unique = true, nullable = false)
    private String productName;
    private String productDescription;
    private Double salePrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category productCategory;
    private String barcode;
    private String variant;

    @Column(name = "sku", unique = true)
    private String sku;
    private String brand;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id")
    private Business businesses;

    private Boolean status = true;
    private Boolean favorite = true;
    private Boolean isPosItem = true;
    private Boolean isStockCount;
    private Boolean isOptional = false;
    private Integer numberOfStockAlert;
    private Integer availableInStock;
    @Enumerated(EnumType.STRING)
    private ProductType productType = ProductType.Single;

    @Column(name = "productImageUrl")
    private String productImageUrl;

    public Product() {
    }

    public Product(String productName, String productDescription, Double salePrice, Category productCategory, String barcode, String variant, String sku, String brand, Business businesses, Boolean status, Boolean favorite, Boolean isPosItem, Boolean isStockCount, Boolean isOptional, Integer numberOfStockAlert, Integer availableInStock, ProductType productType, String productImageUrl) {

        this.productName = productName;
        this.productDescription = productDescription;
        this.salePrice = salePrice;
        this.productCategory = productCategory;
        this.barcode = barcode;
        this.variant = variant;
        this.sku = sku;
        this.brand = brand;
        this.businesses = businesses;
        this.status = status;
        this.favorite = favorite;
        this.isPosItem = isPosItem;
        this.isStockCount = isStockCount;
        this.isOptional = isOptional;
        this.numberOfStockAlert = numberOfStockAlert;
        this.availableInStock = availableInStock;
        this.productType = productType;
        this.productImageUrl = productImageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Business getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Business businesses) {
        this.businesses = businesses;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Boolean getPosItem() {
        return isPosItem;
    }

    public void setPosItem(Boolean posItem) {
        isPosItem = posItem;
    }

    public Boolean getStockCount() {
        return isStockCount;
    }

    public void setStockCount(Boolean stockCount) {
        isStockCount = stockCount;
    }

    public Boolean getOptional() {
        return isOptional;
    }

    public void setOptional(Boolean optional) {
        isOptional = optional;
    }

    public Integer getNumberOfStockAlert() {
        return numberOfStockAlert;
    }

    public void setNumberOfStockAlert(Integer numberOfStockAlert) {
        this.numberOfStockAlert = numberOfStockAlert;
    }

    public Integer getAvailableInStock() {
        return availableInStock;
    }

    public void setAvailableInStock(Integer availableInStock) {
        this.availableInStock = availableInStock;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", salePrice=" + salePrice +
                ", productCategory=" + productCategory +
                ", barcode='" + barcode + '\'' +
                ", variant='" + variant + '\'' +
                ", sku='" + sku + '\'' +
                ", brand='" + brand + '\'' +
                ", businesses=" + businesses +
                ", status=" + status +
                ", favorite=" + favorite +
                ", isPosItem=" + isPosItem +
                ", isStockCount=" + isStockCount +
                ", isOptional=" + isOptional +
                ", numberOfStockAlert=" + numberOfStockAlert +
                ", availableInStock=" + availableInStock +
                ", productType=" + productType +
                ", productImageUrl='" + productImageUrl + '\'' +
                '}';
    }
}
