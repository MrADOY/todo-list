package com.adoy.todo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adoy.todo.modeles.Todo;
import com.adoy.todo.repository.TodoRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {

	@Autowired
	TodoRepository repository;

	@GetMapping("/todos")
	public List<Todo> getAllTodos() {
		return repository.findAll().stream()
				.sorted((a, b) -> a.getDateCreation().compareTo(b.getDateCreation()))
				.collect(Collectors.toList());
	}
	
	@PostMapping("todos")
	public Todo createTodo(@RequestBody @Valid Todo todo) {
		todo.setTerminee(false); //Par defaut la todo n'est pas faite
		return repository.save(todo);
	}
	
	@GetMapping("todos/{id}")
	public ResponseEntity<Todo> findTodoById(@PathVariable("id") String id) {
		return repository.findById(id)
				.map(todo -> ResponseEntity.ok().body(todo))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id, @Valid @RequestBody Todo todo) {
		return repository.findById(id).map(todoData -> {
			todoData.setTitre(todo.getTitre());
			todoData.setCommentaire(todo.getCommentaire());
			todoData.setTerminee(todo.getTerminee());
			todoData.setDateFin(todo.getDateFin());
			Todo updatedTodo = repository.save(todoData);
			return ResponseEntity.ok().body(updatedTodo);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("todos/id")
	public ResponseEntity<?> deteleTodo(@PathVariable("id") String id) {
		return repository.findById(id).map(todo -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.badRequest().build());

	}
}

