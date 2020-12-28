package com.my.projmanager.controller;

import com.my.projmanager.controller.request.MessageCreateRequest;
import com.my.projmanager.controller.request.MessageUpdateRequest;
import com.my.projmanager.exceptions.rest.DataFormatException;
import com.my.projmanager.exceptions.rest.ResourceNotFoundException;
import com.my.projmanager.model.impl.Message;
import com.my.projmanager.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/messages")
@AllArgsConstructor
public class MessageController {
    private final MessageRepository repository;

    @GetMapping
    public ResponseEntity<List<Message>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findMessageById(@PathVariable Long id){
        return ResponseEntity.ok(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString())));
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody MessageCreateRequest request){
        if (repository.existsByContentAndSender(request.getContent(), request.getSender()))
            throw new DataFormatException("Entity already exist.");

        Message message = new Message();
        message.setSender(request.getSender());
        message.setContent(request.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(message));
    }

    @PutMapping
    public ResponseEntity<Message> updateMessage(@RequestBody MessageUpdateRequest request){
        Message message = repository.findById(request.getId())
                .orElseThrow(() -> new DataFormatException("No such element."));

        message.setSender(request.getSender());
        message.setContent(request.getContent());
        return ResponseEntity.ok(repository.save(message));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Long> deleteMessage(@PathVariable Long id) {
        Message message = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        repository.delete(message);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/search/taskId={taskId}")
    public ResponseEntity<List<Message>> findAllByTaskId(@PathVariable Long taskId){
        return ResponseEntity.ok(repository.findAllByTaskId(taskId));
    }

    @GetMapping("/search/projectId={projectId}")
    public ResponseEntity<List<Message>> findAllByProjectId(@PathVariable Long projectId){
        return ResponseEntity.ok(repository.findAllByProjectId(projectId));
    }
}
