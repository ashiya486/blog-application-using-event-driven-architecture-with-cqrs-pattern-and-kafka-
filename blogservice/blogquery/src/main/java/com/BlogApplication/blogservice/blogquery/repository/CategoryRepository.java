package com.BlogApplication.blogservice.blogquery.repository;

import com.BlogApplication.blogservice.blogquery.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findCategoryByName(String name);
}
