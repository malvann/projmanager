package com.my.projmanager.model.impl;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.my.projmanager.model.Draft;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "task_id"))
@AttributeOverride(name = "name", column = @Column(name = "task_name"))
@AttributeOverride(name = "deskr", column = @Column(name = "task_deskr"))
@AttributeOverride(name = "created", column = @Column(name = "task_create_date"))
@AttributeOverride(name = "updated", column = @Column(name = "task_update_date"))
@AttributeOverride(name = "temporationPlan", column = @Column(name = "task_temporation_plan_date"))
@AttributeOverride(name = "temporationFact", column = @Column(name = "task_temporation_fact_date"))
@AttributeOverride(name = "closed", column = @Column(name = "task_closed"))
@AssociationOverride(
        name = "files",
        joinTable = @JoinTable(
                name = "att_task_mapping",
                joinColumns = @JoinColumn(name = "att_task_task_id"),
                inverseJoinColumns = @JoinColumn(name = "att_task_att_id")
        )
)
@AssociationOverride(
        name = "messages",
        joinTable = @JoinTable(
                name = "message_task_mapping",
                joinColumns = @JoinColumn(name = "mess_task_task_id"),
                inverseJoinColumns = @JoinColumn(name = "mess_task_mess_id")
        )
)
@AssociationOverride(name = "tasks",
        joinTable = @JoinTable(
                name = "tasks_mapping",
                joinColumns = @JoinColumn(name = "tasks_mapping_maintask_id"),
                inverseJoinColumns = @JoinColumn(name = "tasks_mapping_task_id")
        )
)
@EqualsAndHashCode(callSuper = true)
public class Task extends Draft {
  @ManyToMany
  @JsonManagedReference
  @JoinTable(
          name = "task_employees_mapping",
          joinColumns = @JoinColumn(name = "task_employees_task_id"),
          inverseJoinColumns = @JoinColumn(name = "task_employees_executor_id")
  )
  Set<Employee> executors;
}