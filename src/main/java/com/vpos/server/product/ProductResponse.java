package com.vpos.server.product;

import com.vpos.server.category.Category;

/**
 * @author Rithy SKUN
 * @created 17/04/2023 - 3:33 PM
 * @project server
 **/

public record ProductResponse(
        Long id,
        String productName,
        Double salePrice,
        Boolean status,

        Category productCategory

) {
}
