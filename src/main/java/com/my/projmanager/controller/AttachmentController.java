package com.my.projmanager.controller;

import com.my.projmanager.model.impl.Attachment;
import com.my.projmanager.repository.AttachmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/attachments")
@AllArgsConstructor
public class AttachmentController {
    private final AttachmentRepository repository;

    @GetMapping
    public ResponseEntity<List<Attachment>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Attachment findEmployeeById(@PathVariable Long id){
        return repository.findById(id).get();
    }


}
