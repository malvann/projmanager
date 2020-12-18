package com.my.projmanager.repository;

import com.my.projmanager.model.impl.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByCreatedBetweenAndClosed(Timestamp after, Timestamp before, Boolean closed);

    List<Project> findByNameAndClosed(String name, Boolean closed);

    @Query(value = "select p from Project p where p.temporationFact > p.temporationPlan and p.closed = :closed")
    List<Project> getOverdue(Boolean closed);

    List<Project> findByCustomer_IdAndClosed(Long customerId, Boolean closed);

    List<Project> findByDirector_IdAndClosed(Long directorId, Boolean closed);

    List<Project> findByClosed(Boolean closed);
}
