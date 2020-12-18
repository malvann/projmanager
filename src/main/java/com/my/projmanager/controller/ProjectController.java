package com.my.projmanager.controller;

import com.my.projmanager.controller.request.ProjectCreateRequest;
import com.my.projmanager.controller.request.ProjectUpdareRequest;
import com.my.projmanager.model.impl.Project;
import com.my.projmanager.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/app/projects")
@AllArgsConstructor
public class ProjectController {
    private final ProjectRepository repository;

    @GetMapping
    public ResponseEntity<List<Project>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> findProjectById(@PathVariable Long id){
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectCreateRequest request){
        Project project = fillProjectByRequest(new Project(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(project));
    }

    @PutMapping()
    public ResponseEntity<Project> updateProject(@RequestBody ProjectUpdareRequest request){
        Project project = fillProjectByRequest(repository.findById(request.getId()).get(), request);
        return ResponseEntity.ok(repository.save(project));
    }

    private static Project fillProjectByRequest(Project project, ProjectCreateRequest request){
        project.setName(request.getName());
        project.setCustomer(request.getCustomer());
        project.setDirector(request.getDirector());
        project.setDeskr(request.getDescr());
        project.setTemporationPlan(request.getTermonationPlanDate());
        return project;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Long> deleteProject(@PathVariable Long id) {
        Project project = repository.findById(id).get();
        repository.delete(project);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/search/{after},{before}/closed={closed}")
    public ResponseEntity<List<Project>> findByCreatedBetweenAndClosed(
            @PathVariable Timestamp after,
            @PathVariable Timestamp before,
            @PathVariable Boolean closed) {
        return ResponseEntity.ok(repository.findByCreatedBetweenAndClosed(after,before,closed));
    }

    @GetMapping("/search/{name}/closed={closed}")
    public ResponseEntity<List<Project>> findByNameAndClosed(
            @PathVariable String name,
            @PathVariable Boolean closed) {
        return ResponseEntity.ok(repository.findByNameAndClosed(name,closed));
    }

    @GetMapping("/search/overdue")
    public ResponseEntity<List<Project>> getOverdue(
            @RequestBody Boolean closed) {
        return ResponseEntity.ok(repository.getOverdue(closed));
    }

    @GetMapping("/search/customer={customerId}/closed={closed}")
    public ResponseEntity<List<Project>> findByCustomerIdAndClosed(
            @PathVariable Long customerId,
            @PathVariable Boolean closed) {
        return ResponseEntity.ok(repository.findByCustomer_IdAndClosed(customerId, closed));
    }

  @GetMapping("/search/director={directorId}/closed={closed}")
  public ResponseEntity<List<Project>> findByDirectorIdAndClosed(
      @PathVariable Long directorId, @PathVariable Boolean closed) {
    return ResponseEntity.ok(repository.findByDirector_IdAndClosed(directorId, closed));
    }

    @GetMapping("/search/closed={closed}")
    public ResponseEntity<List<Project>> findByClosed(
            @RequestBody Boolean closed) {
        return ResponseEntity.ok(repository.findByClosed(closed));
    }
}
