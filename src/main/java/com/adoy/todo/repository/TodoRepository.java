package com.adoy.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.adoy.todo.modeles.Todo;

@Repository
public interface TodoRepository extends MongoRepository<Todo,String> {

	
}
