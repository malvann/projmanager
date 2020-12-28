package com.my.projmanager.service;

import com.my.projmanager.model.impl.Employee;
import com.my.projmanager.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Employee save(Employee employee){
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return repository.save(employee);
    }

    @Cacheable(cacheNames = "employees")
    public List<Employee> findAll(){
        return repository.findAll();
    }

    @Cacheable(cacheNames = "employees", key = "#id")
    public Optional<Employee> findById(Long id){
        return repository.findById(id);
    }
}
