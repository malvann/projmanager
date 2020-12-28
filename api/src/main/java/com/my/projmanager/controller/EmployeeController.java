package com.my.projmanager.controller;

import com.my.projmanager.controller.request.EmployeeCreateRequest;
import com.my.projmanager.controller.request.EmployeeUpdateRequest;
import com.my.projmanager.exceptions.rest.DataFormatException;
import com.my.projmanager.exceptions.rest.ResourceNotFoundException;
import com.my.projmanager.model.impl.Employee;
import com.my.projmanager.repository.EmployeeRepository;
import com.my.projmanager.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/employees")
//@Transactional
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeRepository repository;
    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString())));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeCreateRequest request){
        if (repository.existsByMailAndPhone(request.getMail(), request.getPhone()))
            throw new DataFormatException("Entity already exist.");

        Employee employee = fillEmployeeByRequest(new Employee(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(employee));
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeUpdateRequest request){
        Employee employee = fillEmployeeByRequest(repository.findById(request.getId())
                .orElseThrow(() -> new DataFormatException("No such element")), request);
        employee.setFired(request.getFired());
        return ResponseEntity.ok(service.save(employee));
    }

    public static Employee fillEmployeeByRequest(Employee employee, EmployeeCreateRequest request){
        employee.setName(request.getName());
        employee.setLastname(request.getLastname());
        employee.setPassword(request.getPassword());
        employee.setMail(request.getMail());
        employee.setPosition(request.getPosition());
        employee.setPhone(request.getPhone());
        employee.setSkype(request.getSkype());
        employee.setTelegramm(request.getTelegramm());
        employee.setRole(request.getRole());
        return employee;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Long> deleteEmployee(@PathVariable Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        repository.delete(employee);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/search/{name},{lastname}")
    public ResponseEntity<List<Employee>> findByNameAndLastname(
            @PathVariable String name,
            @PathVariable String lastname){
        return ResponseEntity.ok(repository.findByNameAndLastname(name, lastname));
    }

    @GetMapping("search/{position}")
    public ResponseEntity<List<Employee>> findByPosition(@PathVariable String position){
        return ResponseEntity.ok(repository.findByPosition_Title(position));
    }

    @GetMapping("search/isFired={isFired}")
    public ResponseEntity<List<Employee>> findByFired(@PathVariable Boolean isFired){
        return ResponseEntity.ok(repository.findByFired(isFired));
    }

    @GetMapping("search/projectId={projectId}/employeeIsFired={employeeIsFired}/taskIsClosed={taskIsClosed}")
    public ResponseEntity<List<Employee>> getExecutorsAndSubExecutorsByProjectId(
            @PathVariable Long projectId,
            @PathVariable Boolean employeeIsFired,
            @PathVariable Boolean taskIsClosed){
        return ResponseEntity.ok(repository.getExecutorsAndSubExecutorsByProjectId(projectId, employeeIsFired, taskIsClosed));
    }

    @GetMapping("search/taskId={taskId}/employeeIsFired={employeeIsFired}/taskIsClosed={taskIsClosed}")
    public ResponseEntity<List<Employee>> getExecutorsAndSubExecutorsByTaskId(
            @PathVariable Long taskId,
            @PathVariable Boolean employeeIsFired,
            @PathVariable Boolean taskIsClosed){
        return ResponseEntity.ok(repository.getExecutorsAndSubExecutorsByTaskId(taskId, employeeIsFired, taskIsClosed));
    }
}
