package com.BlogApplication.blogservice.blogquery.query;

public class GetBlogsByUsername {
    private String name;

    public GetBlogsByUsername() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GetBlogsByUsername(String name) {
        this.name = name;
    }
}
