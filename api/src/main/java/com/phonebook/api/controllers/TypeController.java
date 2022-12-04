package com.phonebook.api.controllers;

import com.phonebook.api.models.Type;
import com.phonebook.api.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TypeController {
    @Autowired
    private final TypeRepository repository;

    TypeController(TypeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/types")
    List<Type> getAll() {
        return repository.findAll();
    }

    @GetMapping("/types/{id}")
    Type getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping("/types")
    Type post(@RequestBody Type newType) {
        return repository.save(newType);
    }

    @PutMapping("/types/{id}")
    Type put(@PathVariable Long id, @RequestBody Type newType) {
        return repository.findById(id).map((type -> {
            type.setName(newType.getName());
            return repository.save(type);
        })).orElseGet(()->{
            newType.setId(id);
            return repository.save(newType);
        });
    }

    @DeleteMapping("/types/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
