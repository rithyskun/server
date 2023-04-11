package com.vpos.server.category;

/*
 * @created 11/04/2023 - 4:55 PM
 * @project server
 * @author Rithy SKUN
 */

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String CategoryName);
}
