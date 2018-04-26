package com.application.clevtodo.controllers;

import javax.validation.Valid;

import com.application.clevtodo.models.ClevTodo;
import com.application.clevtodo.repositories.ClevTodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashish.sethi on 04/26/18.
 */

@RestController
@RequestMapping("/rest")
@CrossOrigin("*")
public class ClevTodoController {

    @Autowired
    ClevTodoRepo clevTodoRepo;

    @GetMapping("/clevtodos")
    public List<ClevTodo> getTodos() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return clevTodoRepo.findAll(sortByCreatedAtDesc);
    }

  @GetMapping("/clevtodos/completed")
  public List<ClevTodo> getCompletedTodos() {
    Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
    List<ClevTodo> completedClevTodo = new ArrayList<ClevTodo>();
    for(ClevTodo clevTodo : clevTodoRepo.findAll(sortByCreatedAtDesc)) {
      if(clevTodo.getCompleted()) {
        completedClevTodo.add(clevTodo);
      }
    }
    return completedClevTodo;
  }

  @GetMapping("/clevtodos/pending")
  public List<ClevTodo> getPendingTodos() {
    Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
    List<ClevTodo> pendingClevTodo = new ArrayList<ClevTodo>();
    for(ClevTodo clevTodo : clevTodoRepo.findAll(sortByCreatedAtDesc)) {
      if(!clevTodo.getCompleted()) {
        pendingClevTodo.add(clevTodo);
      }
    }
    return pendingClevTodo;
  }

  @DeleteMapping(value="/clevtodos/{id}")
  public ResponseEntity<?> deleteClevTodo(@PathVariable("id") String id) {
    return clevTodoRepo.findById(id)
        .map(todo -> {
          clevTodoRepo.deleteById(id);
          return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
  }

    @PostMapping("/clevtodos")
    public ClevTodo createTodo(@Valid @RequestBody ClevTodo clevTodo) {
        clevTodo.setCompleted(false);
        return clevTodoRepo.save(clevTodo);
    }

    @GetMapping(value="/clevtodos/{id}")
    public ResponseEntity<ClevTodo> getClevTodoById(@PathVariable("id") String id) {
        return clevTodoRepo.findById(id)
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/clevtodos/{id}")
    public ResponseEntity<ClevTodo> updateClevTodo(@PathVariable("id") String id,
                                           @Valid @RequestBody ClevTodo clevTodo) {
        return clevTodoRepo.findById(id)
                .map(todoData -> {
                    todoData.setTitle(clevTodo.getTitle());
                    todoData.setCompleted(clevTodo.getCompleted());
                    ClevTodo updatedClevTodo = clevTodoRepo.save(todoData);
                    return ResponseEntity.ok().body(updatedClevTodo);
                }).orElse(ResponseEntity.notFound().build());
    }

}