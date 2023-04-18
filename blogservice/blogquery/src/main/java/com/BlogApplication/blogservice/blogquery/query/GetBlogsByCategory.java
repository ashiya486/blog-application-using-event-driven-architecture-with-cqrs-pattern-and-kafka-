package com.BlogApplication.blogservice.blogquery.query;

public class GetBlogsByCategory {
    private String name;

    public GetBlogsByCategory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GetBlogsByCategory(String name) {
        this.name = name;
    }
}
