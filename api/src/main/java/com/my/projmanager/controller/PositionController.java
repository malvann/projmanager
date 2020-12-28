package com.my.projmanager.controller;

import com.my.projmanager.controller.request.PositionCreateRequest;
import com.my.projmanager.controller.request.PositionUpdateRequest;
import com.my.projmanager.exceptions.rest.DataFormatException;
import com.my.projmanager.exceptions.rest.ResourceNotFoundException;
import com.my.projmanager.model.impl.Position;
import com.my.projmanager.repository.PositionRepository;
import com.my.projmanager.service.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/position")
@AllArgsConstructor
public class PositionController {
    private final PositionRepository repository;
    private final PositionService service;

    @GetMapping
    public ResponseEntity<List<Position>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> findPositionById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString())));
    }

    @PostMapping
    public ResponseEntity<Position> createPosition(@RequestBody PositionCreateRequest request){
        if (repository.existsByTitle(request.getTitle()))
            throw new DataFormatException("Entity already exist.");

        Position position = new Position();
        position.setTitle(request.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(position));
    }

    @PutMapping
    public ResponseEntity<Position> updatePosition(@RequestBody PositionUpdateRequest request){
        Position position = repository.findById(request.getId())
                .orElseThrow(() -> new DataFormatException("No such element."));

        position.setTitle(request.getTitle());
        return ResponseEntity.ok(repository.save(position));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Long> deletePosition(@PathVariable Long id) {
        Position position = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        repository.delete(position);
        return ResponseEntity.ok(id);
    }
}
