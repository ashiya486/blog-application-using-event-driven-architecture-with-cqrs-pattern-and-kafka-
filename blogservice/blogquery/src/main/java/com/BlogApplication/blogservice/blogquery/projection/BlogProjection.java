package com.BlogApplication.blogservice.blogquery.projection;

import com.BlogApplication.blogservice.blogquery.entity.Blog;
import com.BlogApplication.blogservice.blogquery.entity.Category;
import com.BlogApplication.blogservice.blogquery.payload.BlogModel;
import com.BlogApplication.blogservice.blogquery.query.GetAllBlogsQuery;
import com.BlogApplication.blogservice.blogquery.query.GetBlogsBetweenDates;
import com.BlogApplication.blogservice.blogquery.query.GetBlogsByCategory;
import com.BlogApplication.blogservice.blogquery.query.GetBlogsByUsername;
import com.BlogApplication.blogservice.blogquery.repository.BlogRepository;
import com.BlogApplication.blogservice.blogquery.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BlogProjection {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @QueryHandler
    public List<BlogModel> handle(GetAllBlogsQuery getAllBlogsQuery){
        List<Blog> list_blog_ent=this.blogRepository.findAll();
        List<BlogModel> list_blog_model=list_blog_ent.stream().map(blog -> new BlogModel(blog.getId(),blog.getAuthor(),blog.getTopic(),blog.getContent(),blog.getTimestamp(),blog.getCategory())).collect(Collectors.toList());
        return list_blog_model;

    }
    @QueryHandler List<BlogModel>handle(GetBlogsByCategory getBlogsByCategory){
        log.info("inside query handler");
        Category category= categoryRepository.findCategoryByName(getBlogsByCategory.getName());
        List<Blog>blogs_ent=blogRepository.findByCategoryId(category.getId());
        List<BlogModel>blogs_mod=new ArrayList<>();
        blogs_ent.stream().forEach(blog ->blogs_mod.add(new BlogModel(blog.getId(),blog.getAuthor(),blog.getTopic(),blog.getContent(),blog.getTimestamp(),blog.getCategory())) );
        return blogs_mod;
    }
    @QueryHandler List<BlogModel> handle(GetBlogsByUsername getBlogsByUsername){
        log.info("inside query handler");
        List<Blog> blogs_ent= this.blogRepository.findAllByAuthor(getBlogsByUsername.getName());
        List<BlogModel>blogs_mod=new ArrayList<>();
        blogs_ent.forEach(blog ->blogs_mod.add(new BlogModel(blog.getId(),blog.getAuthor(),blog.getTopic(),blog.getContent(),blog.getTimestamp(),blog.getCategory())) );
        return blogs_mod;
    }
    @QueryHandler List<BlogModel> handle(GetBlogsBetweenDates getBlogsBetweenDates){
        log.info("inside query handler");
        LocalDate startLocalDate = LocalDate.parse(getBlogsBetweenDates.getStartDate());
        LocalDate endLocalDate=LocalDate.parse(getBlogsBetweenDates.getEndDate());
        LocalDateTime start=startLocalDate.atStartOfDay();
        LocalDateTime end=endLocalDate.atStartOfDay();
        List<Blog> blogs_ent=this.blogRepository.findAllByTimestampBetween(start,end);
        List<BlogModel>blogs_mod=new ArrayList<>();
        blogs_ent.forEach(blog ->blogs_mod.add(new BlogModel(blog.getId(),blog.getAuthor(),blog.getTopic(),blog.getContent(),blog.getTimestamp(),blog.getCategory())) );
    return blogs_mod;

    }
}
