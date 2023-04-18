package com.BlogApplication.blogservice.blogCommand.commands;

import com.BlogApplication.blogservice.blogCommand.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateBlogCommand {
    @TargetAggregateIdentifier
    private UUID id;
    private String author;
    private String topic;
    private String content;
    private LocalDateTime timestamp;
    private String category;

    public CreateBlogCommand(UUID id, String author, String topic, String content, LocalDateTime timestamp, String category) {
        this.id = id;
        this.author = author;
        this.topic = topic;
        this.content = content;
        this.timestamp = timestamp;
        this.category = category;
    }

    public CreateBlogCommand() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


}
