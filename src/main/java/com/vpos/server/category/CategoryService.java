package com.vpos.server.category;

/*
 * @created 11/04/2023 - 4:51 PM
 * @project server
 * @author Rithy SKUN
 */

import java.util.Collection;

public interface CategoryService {
    Collection<Category> getCategories();

    Category createCategory(Category category);

    void deleteCategory(Long id);

    Category updateCategory(Long id, Category category);
}
