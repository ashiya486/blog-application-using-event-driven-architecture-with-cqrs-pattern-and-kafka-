package com.BlogApplication.blogservice.blogCommand.payload;

import com.BlogApplication.blogservice.blogCommand.entity.Blog;

import java.util.HashSet;
import java.util.Set;

public class CategoryModel {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }


    private Set<Blog> blogs=new HashSet<>();
}
