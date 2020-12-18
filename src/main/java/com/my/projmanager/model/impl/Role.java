package com.my.projmanager.model.impl;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true, of = "name")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;

    @NotNull
    @Column(name = "role_name", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private SystemRole name;
}
