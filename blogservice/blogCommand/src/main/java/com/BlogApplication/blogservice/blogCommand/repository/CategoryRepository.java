package com.BlogApplication.blogservice.blogCommand.repository;

import com.BlogApplication.blogservice.blogCommand.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findCategoryByName(String name);
}
