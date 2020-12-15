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
                joinColumns = @JoinColumn(name = "mess_proj_projID"),
                inverseJoinColumns = @JoinColumn(name = "mess_proj_messageID")
        )
)
@AssociationOverride(
        name = "messages",
        joinTable = @JoinTable(
                name = "message_proj_mapping",
                joinColumns = @JoinColumn(name = "mess_proj_projID"),
                inverseJoinColumns = @JoinColumn(name = "mess_proj_messageID")
        )
)
@AssociationOverride(name = "tasks",
        joinTable = @JoinTable(
                name = "project_tasks_mapping",
                joinColumns = @JoinColumn(name = "project_tasks_projectID"),
                inverseJoinColumns = @JoinColumn(name = "project_tasks_taskID")
        )
)
@EqualsAndHashCode(callSuper = true)
public class Project extends Draft {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proj_customer", nullable = false)
    @JsonManagedReference
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proj_director", referencedColumnName = "empl_id")
    @JsonManagedReference
    private Employee director;
}
