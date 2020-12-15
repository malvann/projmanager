package com.my.projmanager.service;

import com.my.projmanager.model.impl.Employee;
import com.my.projmanager.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public Employee save(Employee employee){
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee){
    employee.setPassword(passwordEncoder.encode(employee.getPassword()));
    return employeeRepository.save(employee);
    }
}
