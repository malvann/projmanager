package com.my.projmanager.repository;

import com.my.projmanager.model.impl.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
