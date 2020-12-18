package com.my.projmanager.repository;

import com.my.projmanager.model.impl.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "select t.messages from Task t where t.id = :taskID")
    List<Message> findAllByTaskId(Long taskID);

    @Query(value = "select p.messages from Project p where p.id = :projectID")
    List<Message> findAllByProjectId(Long projectID);
}
