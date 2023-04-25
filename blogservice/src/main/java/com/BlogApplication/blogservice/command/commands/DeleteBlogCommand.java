package com.BlogApplication.blogService.command.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteBlogCommand {
    @TargetAggregateIdentifier
    private UUID id;
    private String title;
}
