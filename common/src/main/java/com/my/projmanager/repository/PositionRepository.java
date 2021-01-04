package com.my.projmanager.repository;

import com.my.projmanager.model.impl.Position;

public interface PositionRepository extends CRUJpaRepository<Position, Long> {
    boolean existsByTitle(String title);
}
