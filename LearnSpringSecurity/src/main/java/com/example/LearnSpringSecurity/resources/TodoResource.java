package com.example.LearnSpringSecurity.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TodoResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    static List<Todo> TODO_LIST = List.of(new Todo("Alex","Learn AWS"),
            new Todo("Alice","Learn GCP"),
            new Todo("Alex","Learn GCP"));

    @GetMapping("/todos")
    public List<Todo> retrieveAllTodos() {
        return TODO_LIST;
    }

    @GetMapping("/users/{username}/todos")
    public Stream<Todo> retrieveTodoForSpecificUser(@PathVariable String username) {
        return TODO_LIST.stream()
                .filter((todo) -> todo.userName().equals(username));
    }

    @PostMapping("/users/{username}/todos")
    public void createTodoForSpecificUser(@PathVariable String username, @RequestBody Todo todo) {
        logger.info("Create {} for {}", todo, username);
    }
}

record Todo (String userName, String description) {}
