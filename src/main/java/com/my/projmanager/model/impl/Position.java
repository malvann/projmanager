package com.my.projmanager.model.impl;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "positions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, of = "title")
public class Position {
    @Id
    @Column(name = "position_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "position_title", nullable = false)
    private String title;

    @Column(name = "position_create_date")
    private Timestamp created;

    @Column(name = "position_update_date")
    private Timestamp updated;
}
