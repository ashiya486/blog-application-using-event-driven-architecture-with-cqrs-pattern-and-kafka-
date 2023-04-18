package com.BlogApplication.blogservice.blogquery.controller;

import com.BlogApplication.blogservice.blogquery.entity.Blog;
import com.BlogApplication.blogservice.blogquery.payload.BlogModel;
import com.BlogApplication.blogservice.blogquery.query.GetAllBlogsQuery;
import com.BlogApplication.blogservice.blogquery.query.GetBlogsBetweenDates;
import com.BlogApplication.blogservice.blogquery.query.GetBlogsByCategory;
import com.BlogApplication.blogservice.blogquery.query.GetBlogsByUsername;
import com.BlogApplication.blogservice.blogquery.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/blog/query")
public class BlogController {
    private QueryGateway queryGateway;
    @Autowired
    private BlogRepository blogRepository;


    public BlogController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    @GetMapping
    public ResponseEntity<?> getallBlogs(){
        log.info("inside controller:get all blogs");
        GetAllBlogsQuery  getAllBlogsQuery=new GetAllBlogsQuery();
        log.info("loading query to query gateway");
        List<BlogModel> listBLog=queryGateway.query(getAllBlogsQuery, ResponseTypes.multipleInstancesOf(BlogModel.class)).join();
        return ResponseEntity.ok(listBLog);
            }
     @GetMapping("/category/{category}")
    public ResponseEntity<?>getBlogByCategory(@PathVariable("category") String category){
         log.info("inside controller:get all by category " +
                 "blogs");
         GetBlogsByCategory getBlogsByCategory=new GetBlogsByCategory(category);
         log.info("loading query to query gateway");
         List<BlogModel> blogs=queryGateway.query(getBlogsByCategory, ResponseTypes.multipleInstancesOf(BlogModel.class)).join();
         return ResponseEntity.ok(blogs);
     }
     @GetMapping("/user/{username}")
     public ResponseEntity<?>getBlogByUsername(@PathVariable("username") String username){
         log.info("inside controller:get all by username blogs");
         GetBlogsByUsername getBlogsByUsername = new GetBlogsByUsername(username);
         log.info("loading query to query gateway");
        List<BlogModel> blogs = queryGateway.query(getBlogsByUsername, ResponseTypes.multipleInstancesOf(BlogModel.class)).join();
         return ResponseEntity.ok(blogs);
     }
     @GetMapping("/date/{startDate}/{enddate}")
    public ResponseEntity<?> getBlogBetweenDate(@PathVariable("startDate")String startDate,@PathVariable("enddate") String endDate){
         log.info("inside controller:get all between dates  blogs");
         GetBlogsBetweenDates getBlogsBetweenDates= new GetBlogsBetweenDates(startDate,endDate);
         log.info("loading query to query gateway");
         List<BlogModel> blogs = queryGateway.query(getBlogsBetweenDates, ResponseTypes.multipleInstancesOf(BlogModel.class)).join();
         return ResponseEntity.ok(blogs);
     }

}
