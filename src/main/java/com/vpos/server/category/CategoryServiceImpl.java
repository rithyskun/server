package com.vpos.server.category;

/*
 * @created 11/04/2023 - 4:53 PM
 * @project server
 * @author Rithy SKUN
 */

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Collection<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        Optional<Category> _category = categoryRepository.findByCategoryName(category.getCategoryName());

        if(_category.isPresent()) {
            throw new IllegalStateException("The categoryName " + category.getCategoryName() + " already exists");
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Category updateCategory(Long id, Category category) {
        Category _category = categoryRepository.findById(id).orElseThrow(() -> {
            throw new IllegalStateException("The id " + id + " does not exists");
        });

        Optional<Category> exist = categoryRepository.findByCategoryName(category.getCategoryName());

        if(exist.isPresent()) {
            throw new IllegalStateException("The category name " + category.getCategoryName() + " already exists");
        }

        if(_category != null) {
            _category.setCategoryName(category.getCategoryName());
            _category.setCategoryImageUrl(category.getCategoryImageUrl());
            _category.setStatus(category.getStatus());

        }
        return categoryRepository.save(_category);

    }
}
