package com.my.projmanager.repository;

import com.my.projmanager.model.impl.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CRUJpaRepository<Employee, Long> {

    Optional<Employee> findByMail(String email);

    List<Employee> findByNameAndLastname(String name, String lastname);

    List<Employee> findByPosition_Title(String position);

    List<Employee> findByFired(boolean fired);

    @Query(
            value =
                    "with recursive t_id as "
                            + "(select ptm.project_tasks_task_id id from project_tasks_mapping ptm "
                            + "where ptm.project_tasks_project_id = :projectId "
                            + "union "
                            + "select tasks_mapping_task_id id from tasks_mapping tm "
                            + "join t_id on tm.tasks_mapping_maintask_id = t_id.id) "
                            + "select distinct * from employees where empl_fired = :fired and empl_id in "
                            + "(select distinct task_employees_executor_id from task_employees_mapping "
                            + "where task_employees_task_id in (select t.task_id from tasks t "
                            + "join t_id on t.task_id = t_id.id where t.task_closed = :closed))",
            nativeQuery = true)
    List<Employee> getExecutorsAndSubExecutorsByProjectId(
            Long projectId,
            @Param("fired") boolean employeeIsFired,
            @Param("closed") boolean taskIsClosed);

    @Query(
            value =
                    "with recursive t_id as (select task_id id from tasks where task_id = :taskId "
                            + "union "
                            + "select tasks_mapping_task_id id from tasks_mapping where tasks_mapping_maintask_id = :taskId "
                            + "union "
                            + "select tasks_mapping_task_id id from tasks_mapping tm "
                            + "join t_id on tm.tasks_mapping_maintask_id = t_id.id) "
                            + "select distinct * from employees where empl_fired = :fired and empl_id in "
                            + "(select distinct task_employees_executor_id from \"task_employees_mapping\" where task_employees_task_id in "
                            + "(select t.task_id from tasks t join t_id on t.task_id = t_id.id where t.task_closed = :closed))",
            nativeQuery = true)
    List<Employee> getExecutorsAndSubExecutorsByTaskId(
            Long taskId,
            @Param("fired") boolean employeeIsFired,
            @Param("closed") boolean taskIsClosed);

    boolean existsByMailAndPhone(String mail, String phone);
}
