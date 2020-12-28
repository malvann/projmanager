package com.my.projmanager.repository;

import com.my.projmanager.model.impl.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCreatedBetweenAndClosed(Timestamp after, Timestamp before, boolean isClosed);

    List<Task> findByName(String name);

    @Query(value = "select t from Task t where t.temporationFact > t.temporationPlan and t.closed = :closed")
    List<Task> getOverdue(boolean closed);

    @Query(value = "with recursive t_id as (select ptm.project_tasks_task_id id from project_tasks_mapping ptm "
            + "where ptm.project_tasks_project_id in (select p.proj_id from projects p where p.proj_director = :directorID) "
            + "union "
            + "select tasks_mapping_task_id id from tasks_mapping tm join t_id on tm.tasks_mapping_maintask_id = t_id.id) "
            + "select t.* from tasks t left join t_id on t.task_id = t_id.id where t.task_closed = :closed",
            nativeQuery = true)
    List<Task> getAllByDirectorId(String directorID, boolean closed);

    @Query(value = "select * from tasks t left join task_employees_mapping tem on t.task_id = tem.task_employees_task_id " +
            "where tem.task_employees_executor_id = :employeeID and t.task_closed = :closed",
            nativeQuery = true)
    List<Task> getByEmployeeId(Long employeeID, boolean closed);

    List<Task> findByClosed(boolean closed);

    @Query(
            value =
                    "with recursive t_id as (select ptm.project_tasks_task_id id from project_tasks_mapping ptm " +
                            "where ptm.project_tasks_project_id = :projectID"
                            + "    union"
                            + "    select tasks_mapping_task_id id from tasks_mapping tm join t_id on tm.tasks_mapping_maintask_id = t_id.id"
                            + ") select t.* from tasks t left join t_id on t.task_id = t_id.id where t.task_closed = :closed",
            nativeQuery = true)
    List<Task> getByProjectId(Long projectID, boolean closed);

    boolean existsByName(String name);
}
