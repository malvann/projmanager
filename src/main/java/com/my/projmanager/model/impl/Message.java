package com.my.projmanager.model.impl;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, of = {"id", "sender", "content"})
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_sender", nullable = false)
    @JsonManagedReference
    private Employee sender;

    @Column(name = "message_content", nullable = false)
    private String content;

    @Column(name = "message_create_date")
    private Timestamp created;

    @Column(name = "message_update_date")
    private Timestamp updated;
}
