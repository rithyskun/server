package com.vpos.server.product;

import java.util.Collection;

/**
 * @author Rithy SKUN
 * @created 12/04/2023 - 10:14 PM
 * @project server
 **/

public interface ProductService {
    Collection<Product> getProducts();

    Product createProduct(Product product) throws Exception;

    void deleteProduct(Long id);

    Product updateProduct(Long id, Product product) throws Exception;

}
