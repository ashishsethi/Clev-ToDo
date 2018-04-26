package com.application.clevtodo.repositories;

import com.application.clevtodo.models.ClevTodo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClevTodoRepo extends MongoRepository<ClevTodo, String> {

}