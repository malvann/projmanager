package com.my.projmanager.controller;

import com.my.projmanager.controller.request.AttachmentCreateRequest;
import com.my.projmanager.controller.request.AttachmentUpdateRequest;
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
    public Attachment findAttachmentById(@PathVariable Long id){
        return repository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Attachment createAttachment(@RequestBody AttachmentCreateRequest request){
        Attachment attachment = new Attachment();
        attachment.setUrl(request.getUrl());
        return repository.save(attachment);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Attachment updateAttachment(@RequestBody AttachmentUpdateRequest request){
        Attachment attachment = new Attachment();
        attachment.setUrl(request.getUrl());
        return repository.save(attachment);
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteAttachment(@PathVariable Long id){
        Attachment attachment = repository.findById(id).get();
        repository.delete(attachment);
        return id;
    }

    @GetMapping("/search/projectId={projectId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Attachment> getAllAttachmentsByProjectId(@PathVariable Long projectId){
        return repository.getAllAttachmentsByProjectId(projectId);
    }

    @GetMapping("/search/taskId={taskId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Attachment> getAllAttachmentsByTaskId(@PathVariable Long taskId){
        return repository.getAllAttachmentsByTaskId(taskId);
    }
}
