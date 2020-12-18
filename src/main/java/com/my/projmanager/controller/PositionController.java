package com.my.projmanager.controller;

import com.my.projmanager.controller.request.PositionCreateRequest;
import com.my.projmanager.controller.request.PositionUpdateRequest;
import com.my.projmanager.model.impl.Position;
import com.my.projmanager.repository.PositionRepository;
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

    @GetMapping
    public ResponseEntity<List<Position>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> findPositionById(@PathVariable Long id){
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Position> createPosition(@RequestBody PositionCreateRequest request){
        Position position = new Position();
        position.setTitle(request.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(position));
    }

    @PutMapping
    public ResponseEntity<Position> updatePosition(@RequestBody PositionUpdateRequest request){
        Position position = repository.findById(request.getId()).get();
        position.setTitle(request.getTitle());
        return ResponseEntity.ok(repository.save(position));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Long> deletePosition(@PathVariable Long id) {
        Position position = repository.findById(id).get();
        repository.delete(position);
        return ResponseEntity.ok().body(id);
    }
}
