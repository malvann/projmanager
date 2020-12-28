package com.my.projmanager.controller;

import com.my.projmanager.controller.request.AttachmentCreateRequest;
import com.my.projmanager.controller.request.AttachmentUpdateRequest;
import com.my.projmanager.exceptions.rest.DataFormatException;
import com.my.projmanager.exceptions.rest.ResourceNotFoundException;
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
    public ResponseEntity<Attachment> findAttachmentById(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString())));
    }

    @PostMapping
    public ResponseEntity<Attachment> createAttachment(@RequestBody AttachmentCreateRequest request){
        if (repository.existsAttachmentByUrl(request.getUrl()))
            throw new DataFormatException("Entity already exist.");

        Attachment attachment = new Attachment();
        attachment.setUrl(request.getUrl());
      return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(attachment));
    }

    @PutMapping
    public ResponseEntity<Attachment> updateAttachment(@RequestBody AttachmentUpdateRequest request){
        Attachment attachment = repository.findById(request.getId())
                .orElseThrow(() -> new DataFormatException("No such element"));

        attachment.setUrl(request.getUrl());
        return ResponseEntity.ok(repository.save(attachment));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Long> deleteAttachment(@PathVariable Long id) {
        Attachment attachment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        repository.delete(attachment);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/search/projectId={projectId}")
    public ResponseEntity<List<Attachment>> getAllAttachmentsByProjectId(@PathVariable Long projectId){
        return ResponseEntity.ok(repository.getAllAttachmentsByProjectId(projectId));
    }

    @GetMapping("/search/taskId={taskId}")
    public ResponseEntity<List<Attachment>> getAllAttachmentsByTaskId(@PathVariable Long taskId){
        return ResponseEntity.ok(repository.getAllAttachmentsByTaskId(taskId));
    }
}
