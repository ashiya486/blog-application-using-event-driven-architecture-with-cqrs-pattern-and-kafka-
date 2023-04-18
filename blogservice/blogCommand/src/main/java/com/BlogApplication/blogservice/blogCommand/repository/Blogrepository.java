package com.BlogApplication.blogservice.blogCommand.repository;

import com.BlogApplication.blogservice.blogCommand.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Blogrepository extends JpaRepository<Blog, UUID> {
}
