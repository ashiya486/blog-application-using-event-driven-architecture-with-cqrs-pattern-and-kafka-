package com.BlogApplication.blogservice.blogquery.repository;

import com.BlogApplication.blogservice.blogquery.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface BlogRepository extends JpaRepository<Blog, UUID> {
    List<Blog> findByCategoryId(Long id);
    List<Blog> findAllByAuthor(String name);
    List<Blog> findAllByTimestampBetween(
            LocalDateTime publicationTimeStart,
            LocalDateTime publicationTimeEnd);
}

