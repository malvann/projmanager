package com.my.projmanager.service;

import com.my.projmanager.model.impl.Task;
import com.my.projmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    @Cacheable(cacheNames = "tasks")
    public List<Task> findAll(){
        return repository.findAll();
    }

    @Cacheable(cacheNames = "tasks", key = "#id")
    public Optional<Task> findById(Long id){
        return repository.findById(id);
    }
}
