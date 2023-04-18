package com.BlogApplication.userService.userCommand.repository;

import com.BlogApplication.userService.userCommand.aggregate.UserAggregate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AggregateRepository extends MongoRepository<UserAggregate, UUID> {
}
