package com.BlogApplication.blogservice.blogCommand.controller;

import com.BlogApplication.blogservice.blogCommand.commands.CreateBlogCommand;
import com.BlogApplication.blogservice.blogCommand.entity.Category;
import com.BlogApplication.blogservice.blogCommand.payload.BlogRequest;
import com.BlogApplication.blogservice.blogCommand.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;
@RestController
@Slf4j
@RequestMapping("/blog/command")
public class BlogController {
    private CommandGateway commandGateway;
    @Autowired
    private CategoryRepository categoryRepository;

    public BlogController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
    @GetMapping
    public String test(){
        return "test";
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody BlogRequest blogRequest){
        log.info("blog controller");
        CreateBlogCommand createBlogCommand=new CreateBlogCommand(UUID.randomUUID(), blogRequest.getAuthor(), blogRequest.getTopic(), blogRequest.getContent(), LocalDateTime.now(), blogRequest.getCategory());
    return ResponseEntity.ok(this.commandGateway.sendAndWait(createBlogCommand));
    }

//    @PostMapping("/restore")
//    public ResponseEntity<?>restore() {
//        Category category = new Category(1, "sci-fi",null);
//        Category category2 = new Category(2, "science",null);
//        categoryRepository.save(category);
//        categoryRepository.save(category2);
//return ResponseEntity.ok("successful");
//    }
    @DeleteMapping("delete/{title}")
    public void deleteBlog(@PathVariable("title")String title){

    }

}
