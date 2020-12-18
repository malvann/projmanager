package com.my.projmanager.model.impl;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.my.projmanager.model.Draft;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "proj_id"))
@AttributeOverride(name = "name", column = @Column(name = "proj_name"))
@AttributeOverride(name = "deskr", column = @Column(name = "proj_descr"))
@AttributeOverride(name = "created", column = @Column(name = "proj_create_date"))
@AttributeOverride(name = "updated", column = @Column(name = "proj_update_date"))
@AttributeOverride(name = "temporationPlan", column = @Column(name = "proj_termonation_plan_date"))
@AttributeOverride(name = "temporationFact", column = @Column(name = "proj_termonation_fact_date"))
@AttributeOverride(name = "closed", column = @Column(name = "project_closed"))
@AssociationOverride(
        name = "files",
        joinTable = @JoinTable(
                name = "message_proj_mapping",
                joinColumns = @JoinColumn(name = "mess_proj_proj_id"),
                inverseJoinColumns = @JoinColumn(name = "mess_proj_message_id")
        )
)
@AssociationOverride(
        name = "messages",
        joinTable = @JoinTable(
                name = "message_proj_mapping",
                joinColumns = @JoinColumn(name = "mess_proj_proj_id"),
                inverseJoinColumns = @JoinColumn(name = "mess_proj_message_id")
        )
)
@AssociationOverride(name = "tasks",
        joinTable = @JoinTable(
                name = "project_tasks_mapping",
                joinColumns = @JoinColumn(name = "project_tasks_project_id"),
                inverseJoinColumns = @JoinColumn(name = "project_tasks_task_id")
        )
)
@EqualsAndHashCode(callSuper = true)
public class Project extends Draft {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proj_customer", nullable = false)
    @JsonManagedReference
    private Customer customer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proj_director", referencedColumnName = "empl_id")
    @JsonManagedReference
    private Employee director;
}
