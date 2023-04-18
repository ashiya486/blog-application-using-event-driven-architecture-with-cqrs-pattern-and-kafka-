package com.BlogApplication.blogservice.blogCommand.events;

import com.BlogApplication.blogservice.blogCommand.entity.Blog;
import com.BlogApplication.blogservice.blogCommand.entity.Category;
import com.BlogApplication.blogservice.blogCommand.repository.Blogrepository;
import com.BlogApplication.blogservice.blogCommand.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BlogEventHandler {
    @Autowired
    private Blogrepository blogrepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
    @EventHandler
    public void on(BlogCreatedEvent blogCreatedEvent){
        log.info("inside event handler:create blog");
        Logger logger= LoggerFactory.getLogger(BlogEventHandler.class);
        log.info("publishing event to kafka");
       this.kafkaTemplate.send("BlogEventsTopic",blogCreatedEvent);

    }

}
