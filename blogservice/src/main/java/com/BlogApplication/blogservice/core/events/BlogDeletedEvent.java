package com.BlogApplication.blogService.core.events;

import lombok.Data;

import java.util.UUID;
@Data
public class BlogDeletedEvent extends BlogEvent {
    private String title;
}
