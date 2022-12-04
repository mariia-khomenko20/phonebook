package com.phonebook.api.controllers;

import com.phonebook.api.models.Group;
import com.phonebook.api.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {
    @Autowired
    private final GroupRepository repository;

    GroupController(GroupRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/groups")
    List<Group> getAll() {
        return repository.findAll();
    }

    @GetMapping("/groups/{id}")
    Group getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping("/groups")
    Group post(@RequestBody Group newGroup) {
        return repository.save(newGroup);
    }

    @PutMapping("/groups/{id}")
    Group put(@PathVariable Long id, @RequestBody Group newGroup)
    {
        return repository.findById(id).map((group -> {
            group.setName(newGroup.getName());
            return repository.save(group);
        })).orElseGet(() -> {
            newGroup.setId(id);
            return repository.save(newGroup);
        });
    }

    @DeleteMapping("/groups/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
