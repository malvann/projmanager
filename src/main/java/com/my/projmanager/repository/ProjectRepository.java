package com.my.projmanager.repository;

import com.my.projmanager.model.impl.Customer;
import com.my.projmanager.model.impl.Employee;
import com.my.projmanager.model.impl.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByCreatedBetweenAndClosed(Timestamp after, Timestamp before, boolean closed);

    List<Project> findByNameAndClosed(String name, boolean closed);

    @Query(value = "select p from Project p where p.temporationFact > p.temporationPlan and p.closed = :closed")
    List<Project> getOverdue(boolean closed);

    List<Project> findByCustomerAndClosed(Customer customerId, boolean closed);

    List<Project> findByDirectorAndClosed(Employee directorId, boolean closed);

    List<Project> findByClosed(boolean closed);
}
