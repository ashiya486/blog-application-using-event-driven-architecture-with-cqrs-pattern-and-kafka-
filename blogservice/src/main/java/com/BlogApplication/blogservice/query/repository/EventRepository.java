package com.BlogApplication.blogService.query.repository;

import com.BlogApplication.blogService.core.events.BlogEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EventRepository extends MongoRepository<BlogEvent, UUID> {
}
