package com.vpos.server.product;

import com.vpos.server.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Rithy SKUN
 * @created 12/04/2023 - 10:19 PM
 * @project server
 **/

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Collection<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) throws Exception {

       Optional<Product> _product = productRepository.findByProductName(product.getProductName());

       if(_product.isPresent()) {
           throw new IllegalStateException("The product name " + product.getProductName() + " already exists");
       }

       UUID newSku = null;
       if(product.getSku() == null || Objects.equals(product.getSku(), "")){
           newSku = AppUtils.generateRandomValue();
       }

       product.setSku(String.valueOf(newSku));

       return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        boolean exist = productRepository.existsById(id);

        if(!exist) {
           throw new IllegalStateException("The id " + id + " does not exists");
        }

        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws Exception {
        Product _product = productRepository.findById(id).orElseThrow(() -> new IllegalStateException("The id " + id + " does not exists"));

        _product.setProductName(product.getProductName());
        _product.setProductDescription(product.getProductDescription());
        _product.setSalePrice(product.getSalePrice());
        _product.setProductCategory(product.getProductCategory());
        _product.setBarcode(product.getBarcode());
        _product.setVariant(product.getVariant());
        _product.setSku(product.getSku());
        _product.setBrand(product.getBrand());
        _product.setBusinesses(product.getBusinesses());
        _product.setStatus(product.getStatus());
        _product.setFavorite(product.getFavorite());
        _product.setStockCount(product.getStockCount());
        _product.setPosItem(product.getPosItem());
        _product.setOptional(product.getOptional());
        _product.setNumberOfStockAlert(product.getNumberOfStockAlert());
        _product.setAvailableInStock(product.getAvailableInStock());
        _product.setProductType(product.getProductType());
        _product.setProductImageUrl(product.getProductImageUrl());

       return productRepository.save(_product);

    }
}
