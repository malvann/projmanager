package com.my.projmanager.repository;

import com.my.projmanager.model.impl.Employee;
import com.my.projmanager.model.impl.Message;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends CRUJpaRepository<Message, Long> {

    @Query(value = "select t.messages from Task t where t.id = :taskID")
    List<Message> findAllByTaskId(Long taskID);

    @Query(value = "select p.messages from Project p where p.id = :projectID")
    List<Message> findAllByProjectId(Long projectID);

    boolean existsByContentAndSender(String content, Employee sender);
}
