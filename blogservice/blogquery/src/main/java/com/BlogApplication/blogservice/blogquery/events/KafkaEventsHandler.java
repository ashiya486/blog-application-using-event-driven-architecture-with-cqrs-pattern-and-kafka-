package com.BlogApplication.blogservice.blogquery.events;

import com.BlogApplication.blogservice.blogquery.entity.Blog;
import com.BlogApplication.blogservice.blogquery.entity.Category;
import com.BlogApplication.blogservice.blogquery.repository.BlogRepository;
import com.BlogApplication.blogservice.blogquery.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventsHandler {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BlogRepository blogRepository;
    Logger logger= LoggerFactory.getLogger(KafkaEventsHandler.class);
//    @KafkaListener(topics = "BlogEventsTopic",groupId = "blog-query")
    public void handleKafkaEvent(@Payload BlogCreatedEvent blogCreatedEvent){
        Blog blog= new Blog();

        BeanUtils.copyProperties(blogCreatedEvent,blog,"category");
        Category category=categoryRepository.findCategoryByName(blogCreatedEvent.getCategory());
        blog.setCategory(category);
        logger.error("kafka handler");
        blogRepository.save(blog);
    }

}
