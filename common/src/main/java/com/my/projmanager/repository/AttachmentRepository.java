package com.my.projmanager.repository;

import com.my.projmanager.model.impl.Attachment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttachmentRepository extends CRUJpaRepository<Attachment, Long> {

    @Query(value = "select p.files from Project p where p.id = :projectId")
    List<Attachment> getAllAttachmentsByProjectId(Long projectId);

    @Query(value = "select t.files from Task t where t.id = :taskId")
    List<Attachment> getAllAttachmentsByTaskId(Long taskId);

    boolean existsAttachmentByUrl(String url);
}