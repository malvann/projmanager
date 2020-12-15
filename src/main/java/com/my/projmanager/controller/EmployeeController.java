package com.my.projmanager.controller;

import com.my.projmanager.model.impl.Employee;
import com.my.projmanager.repository.EmployeeRepository;
import com.my.projmanager.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/app/employees")
//@Transactional
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }

//    @GetMapping("/id")
//    @ResponseStatus(HttpStatus.OK)
//    public Employee findEmployeeById(@PathVariable Long id){
//        return employeeRepository.findById(id);
//    }
}
