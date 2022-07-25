package com.anshuman.todo.repository;

import com.anshuman.todo.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

  @Query("{userId:'?0'}")
  Page<Task> findByUserId(String userId, Pageable pageable);

}
