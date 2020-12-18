package com.my.projmanager.controller;

import com.my.projmanager.controller.request.TaskCreateRequest;
import com.my.projmanager.controller.request.TaskUpdateRequest;
import com.my.projmanager.model.impl.Task;
import com.my.projmanager.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/app/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskRepository repository;

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable Long id){
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskCreateRequest request){
        Task task = fillTaskByRequest(new Task(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(task));
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody TaskUpdateRequest request){
        Task task = fillTaskByRequest(repository.findById(request.getId()).get(), request);
        return ResponseEntity.ok(repository.save(task));
    }

    private static Task fillTaskByRequest(Task task, TaskCreateRequest request){
        task.setName(request.getName());
        task.setDeskr(request.getDeskription());
        task.setTemporationPlan(request.getTermonationPlanDate());
        return task;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable Long id) {
        Task task = repository.findById(id).get();
        repository.delete(task);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/search/{after},{before}/closed={closed}")
    public ResponseEntity<List<Task>> findByCreatedBetweenAndClosed(
            @PathVariable Timestamp after,
            @PathVariable Timestamp before,
            @PathVariable Boolean closed) {
        return ResponseEntity.ok(repository.findByCreatedBetweenAndClosed(after, before, closed));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Task>> findByName(@PathVariable String name){
        return ResponseEntity.ok(repository.findByName(name));
    }

    @GetMapping("/search/overdue")
    public ResponseEntity<List<Task>> getOverdue(@RequestBody Boolean closed) {
        return ResponseEntity.ok(repository.getOverdue(closed));
    }

    @GetMapping("/search/director{directorId}/closed={closed}")
    public ResponseEntity<List<Task>> getAllByDirectorId(
            @PathVariable String directorId,
            @PathVariable boolean closed){
        return ResponseEntity.ok().body(repository.getAllByDirectorId(directorId, closed));
    }

    @GetMapping("/search/employee{employeeID}/closed={closed}")
    public ResponseEntity<List<Task>> getByEmployeeId(Long employeeID, boolean closed){
        return ResponseEntity.ok().body(repository.getByEmployeeId(employeeID, closed));
    }

    @GetMapping("/search/closed={closed}")
    public ResponseEntity<List<Task>> findByClosed(@RequestBody Boolean closed) {
        return ResponseEntity.ok(repository.findByClosed(closed));
    }

    @GetMapping("/search/project{projectID}/closed={closed}")
    public ResponseEntity<List<Task>> getByProjectId(Long projectID, boolean closed){
        return ResponseEntity.ok(repository.getByProjectId(projectID, closed));
    }
}
