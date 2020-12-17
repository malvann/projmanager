package com.my.projmanager.controller;

import com.my.projmanager.controller.request.EmployeeCreateRequest;
import com.my.projmanager.controller.request.EmployeeUpdateRequest;
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
    @ResponseStatus(HttpStatus.OK)
    public Employee findEmployeeById(@PathVariable Long id){
        return repository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody EmployeeCreateRequest request){
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setLastname(request.getLastname());
        employee.setPassword(request.getPassword());
        employee.setMail(request.getMail());
        employee.setPosition(request.getPosition());
        employee.setPhone(request.getPhone());
        employee.setSkype(request.getSkype());
        employee.setTelegramm(request.getTelegramm());
        employee.setRole(request.getRole());
        return service.save(employee);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@RequestBody EmployeeUpdateRequest request){
        Employee employee = repository.findById(request.getId()).get();
        employee.setName(request.getName());
        employee.setLastname(request.getLastname());
        employee.setPassword(request.getPassword());
        employee.setMail(request.getMail());
        employee.setPosition(request.getPosition());
        employee.setPhone(request.getPhone());
        employee.setSkype(request.getSkype());
        employee.setTelegramm(request.getTelegramm());
        employee.setRole(request.getRole());
        employee.setFired(request.getFired());
        return service.save(employee);
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteUser(@PathVariable Long id) {
        Employee employee = repository.findById(id).get();
        repository.delete(employee);
        return id;
    }

    @GetMapping("/search/{name},{lastname}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> findByNameAndLastname(
            @PathVariable String name,
            @PathVariable String lastname){
        return repository.findByNameAndLastname(name, lastname);
    }

    @GetMapping("search/{position}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> findByPosition(@PathVariable String position){
        return repository.findByPosition_Title(position);
    }

    @GetMapping("search/isFired={isFired}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> findByFired(@PathVariable Boolean isFired){
        return repository.findByFired(isFired);
    }

    @GetMapping("search/projectId={projectId}/employeeIsFired={employeeIsFired}/taskIsClosed={taskIsClosed}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getExecutorsAndSubExecutorsByProjectId(
            @PathVariable Long projectId,
            @PathVariable Boolean employeeIsFired,
            @PathVariable Boolean taskIsClosed){
        return repository.getExecutorsAndSubExecutorsByProjectId(projectId, employeeIsFired, taskIsClosed);
    }

    @GetMapping("search/taskId={taskId}/employeeIsFired={employeeIsFired}/taskIsClosed={taskIsClosed}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getExecutorsAndSubExecutorsByTaskId(
            @PathVariable Long taskId,
            @PathVariable Boolean employeeIsFired,
            @PathVariable Boolean taskIsClosed){
        return repository.getExecutorsAndSubExecutorsByTaskId(taskId, employeeIsFired, taskIsClosed);
    }
}
