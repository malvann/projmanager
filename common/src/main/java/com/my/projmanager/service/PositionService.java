package com.my.projmanager.service;

import com.my.projmanager.model.impl.Position;
import com.my.projmanager.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository repository;

    @Cacheable(cacheNames = "positions")
    public List<Position> findAll(){
        return repository.findAll();
    }

    @Cacheable(cacheNames = "positions", key = "#id")
    public Optional<Position> findById(Long id){
        return repository.findById(id);
    }
}
