package com.my.projmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.my.projmanager.model.impl.Attachment;
import com.my.projmanager.model.impl.Message;
import com.my.projmanager.model.impl.Task;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @JsonProperty("id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String deskr;

    @Column(updatable = false)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp created;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp updated;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp temporationPlan;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp temporationFact;

    @Column(nullable = false)
    private Boolean closed = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    Set<Attachment> files;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    Set<Message> messages;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    Set<Task> tasks;
}
