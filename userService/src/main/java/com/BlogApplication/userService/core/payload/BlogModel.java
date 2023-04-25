package com.BlogApplication.userService.core.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogModel {
    private UUID id;
    private String author;
    private String topic;
    private String content;
    private LocalDateTime timestamp;
    private CategoryModel category;

}
