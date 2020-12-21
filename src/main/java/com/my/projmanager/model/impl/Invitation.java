package com.my.projmanager.model.impl;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "invitations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Invitation {

    @Id
    @Column(name = "token", nullable = false, unique = true)
    private String token;
}
