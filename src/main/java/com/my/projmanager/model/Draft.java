package com.my.projmanager.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.my.projmanager.model.impl.Attachment;
import com.my.projmanager.model.impl.Message;
import com.my.projmanager.model.impl.Task;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, of = {"name"})
public abstract class Draft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String deskr;

    private Timestamp created;

    private Timestamp updated;

    @Column(nullable = false)
    private Timestamp temporationPlan;

    private Timestamp temporationFact;

    @Column(nullable = false)
    private Boolean closed;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<Attachment> files;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<Message> messages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<Task> tasks;
}
