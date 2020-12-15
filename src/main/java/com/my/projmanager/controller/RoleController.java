package com.my.projmanager.controller;

import com.my.projmanager.model.impl.Role;
import com.my.projmanager.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController{
    private final RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntity.ok(roleRepository.findAll());
    }
}
